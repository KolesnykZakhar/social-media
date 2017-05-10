package com.gmail.kolesnyk.zakhar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {

    private Environment environment;
    final protected int AMOUNT_FRIENDS_ON_ONE_PAGE;
    final protected String DEFAULT_AVATAR_URL;
    final protected String ROOT_AVATAR_URL;
    final protected String ROOT_PHOTO_URL;
    final protected String AVATAR_EXTENDS;
    final protected String PHOTO_EXTENDS;
    final protected String SERVICE_EMAIL;
    final protected String PASSWORD_EMAIL;
    final protected int AMOUNT_PHOTOS_ON_ONE_USER;
    final protected int SIZE_OF_SHORT_CHAT;
    final protected int SIZE_OF_SHORT_BLOG_MENU;

    public AbstractService(@Autowired Environment environment) {
        this.environment = environment;
        AMOUNT_FRIENDS_ON_ONE_PAGE = Integer.parseInt(environment.getProperty("amountFriendsOnOnePage"));
        AMOUNT_PHOTOS_ON_ONE_USER = Integer.parseInt(environment.getProperty("amountPhotosOnOneUser"));
        DEFAULT_AVATAR_URL = environment.getProperty("defaultAvatarUrl");
        ROOT_AVATAR_URL = environment.getProperty("rootAvatarUrl");
        ROOT_PHOTO_URL = environment.getProperty("rootPhotoUrl");
        AVATAR_EXTENDS = environment.getProperty("avatarExtends");
        SERVICE_EMAIL = environment.getProperty("serviceEmail");
        PASSWORD_EMAIL = environment.getProperty("passwordEmail");
        PHOTO_EXTENDS = environment.getProperty("photoExtends");
        SIZE_OF_SHORT_CHAT = Integer.parseInt(environment.getProperty("sizeOfShortChat"));
        SIZE_OF_SHORT_BLOG_MENU = Integer.parseInt(environment.getProperty("sizeOfShortBlogMenu"));
    }
}
