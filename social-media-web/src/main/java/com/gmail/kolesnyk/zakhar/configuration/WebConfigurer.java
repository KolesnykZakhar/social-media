package com.gmail.kolesnyk.zakhar.configuration;


import com.gmail.kolesnyk.zakhar.controller.handlers.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private HttpSession httpSession;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestHandler()).addPathPatterns("/user/*");
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public RequestHandler requestHandler() {
        return new RequestHandler();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor() {
            @Override
            protected Locale parseLocaleValue(String locale) {
                Locale loc = super.parseLocaleValue(locale);
                httpSession.setAttribute("locale", loc);
                return loc;
            }

            {
                setParamName("lang");
            }
        };
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver() {{
            setDefaultLocale(Locale.ENGLISH);
        }};
    }
}
