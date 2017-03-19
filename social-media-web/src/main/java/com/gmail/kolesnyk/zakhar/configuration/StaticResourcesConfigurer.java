package com.gmail.kolesnyk.zakhar.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class StaticResourcesConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");
        registry.addResourceHandler("/errorPages/**")
                .addResourceLocations("/errorPages/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }
}
