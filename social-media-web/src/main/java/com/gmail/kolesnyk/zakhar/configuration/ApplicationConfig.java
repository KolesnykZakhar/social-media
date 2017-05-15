package com.gmail.kolesnyk.zakhar.configuration;

import com.gmail.kolesnyk.zakhar.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
//@PropertySource("classpath:/application.properties")
public class ApplicationConfig {
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(AbstractService.class);
    }
}
