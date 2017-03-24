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
//        registry.addResourceHandler("/static/css/**")
//                .addResourceLocations("/static/css/");
//        registry.addResourceHandler("/static/errorPages/**")
//                .addResourceLocations("/static/errorPages/");
//        registry.addResourceHandler("/static/images/**")
//                .addResourceLocations("/static/images/");
//        registry.addResourceHandler("/static/js/**")
//                .addResourceLocations("/static/js/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
//        registry.addResourceHandler("/static/pages/**")
//                .addResourceLocations("/static/pages/");
    }
}
