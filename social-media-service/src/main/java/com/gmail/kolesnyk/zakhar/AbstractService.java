package com.gmail.kolesnyk.zakhar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {

    final protected int AMOUNT_USERS_ON_ONE_PAGE;
    final protected String PATH_STORING_IMAGES;
    final protected String DEFAULT_AVATAR_URL;
    final protected String ROOT_AVATAR_URL;
    final protected String AVATAR_EXTENDS;
    final protected String SERVICE_EMAIL;
    final protected String PASSWORD_EMAIL;
    final protected int AMOUNT_POSTS_ON_ONE_PAGE;
    final protected int SIZE_OF_SHORT_CHAT;

    public AbstractService(@Autowired Environment environment) {
        AMOUNT_USERS_ON_ONE_PAGE = Integer.parseInt(environment.getProperty("amountUsersOnOnePage"));
        AMOUNT_POSTS_ON_ONE_PAGE = Integer.parseInt(environment.getProperty("amountPostsOnOnePage"));
        PATH_STORING_IMAGES = environment.getProperty("pathStoringImages");
        DEFAULT_AVATAR_URL = environment.getProperty("defaultAvatarUrl");
        ROOT_AVATAR_URL = environment.getProperty("rootAvatarUrl");
        AVATAR_EXTENDS = environment.getProperty("avatarExtends");
        SERVICE_EMAIL = environment.getProperty("serviceEmail");
        PASSWORD_EMAIL = environment.getProperty("passwordEmail");
        SIZE_OF_SHORT_CHAT = Integer.parseInt(environment.getProperty("sizeOfShortChat"));
    }
}
