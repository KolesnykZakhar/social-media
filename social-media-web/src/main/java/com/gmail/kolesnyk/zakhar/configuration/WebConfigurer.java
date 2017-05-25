package com.gmail.kolesnyk.zakhar.configuration;


import com.gmail.kolesnyk.zakhar.controller.handlers.RequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfigurer extends WebMvcConfigurerAdapter {

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
        return new LocaleChangeInterceptor() {{
            setParamName("lang");
        }};
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver() {{
            setDefaultLocale(Locale.ENGLISH);
        }};
    }
}
