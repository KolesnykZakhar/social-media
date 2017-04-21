package com.gmail.kolesnyk.zakhar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {

    private Environment environment;
    final protected int AMOUNT_FRIENDS_ON_ONE_PAGE;
    final protected String DEFAULT_AVATAR_URL;
    final protected String ROOT_AVATAR_URL;
    final protected String AVATAR_EXTENDS;
    final protected String SERVICE_EMAIL;
    final protected String PASSWORD_EMAIL;

    public AbstractService(@Autowired Environment environment) {
        this.environment = environment;
        AMOUNT_FRIENDS_ON_ONE_PAGE = Integer.parseInt(environment.getProperty("amountFriendsOnOnePage"));
        DEFAULT_AVATAR_URL = environment.getProperty("defaultAvatarUrl");
        ROOT_AVATAR_URL = environment.getProperty("rootAvatarUrl");
        AVATAR_EXTENDS = environment.getProperty("avatarExtends");
        SERVICE_EMAIL = environment.getProperty("serviceEmail");
        PASSWORD_EMAIL = environment.getProperty("passwordEmail");
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(AbstractService.class);
    }
}
