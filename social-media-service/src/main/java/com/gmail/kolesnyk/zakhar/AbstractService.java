package com.gmail.kolesnyk.zakhar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public abstract class AbstractService {

    final protected int AMOUNT_USERS_ON_ONE_PAGE;
    final protected String PATH_STORING_MEDIA;
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
        PATH_STORING_MEDIA = environment.getProperty("pathStoringMedia");
        DEFAULT_AVATAR_URL = environment.getProperty("defaultAvatarUrl");
        ROOT_AVATAR_URL = environment.getProperty("rootAvatarUrl");
        AVATAR_EXTENDS = environment.getProperty("avatarExtends");
        SERVICE_EMAIL = environment.getProperty("serviceEmail");
        PASSWORD_EMAIL = environment.getProperty("passwordEmail");
        SIZE_OF_SHORT_CHAT = Integer.parseInt(environment.getProperty("sizeOfShortChat"));
    }

    protected String storeMedia(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = System.currentTimeMillis() + "." + ext;
        String fileFullName = PATH_STORING_MEDIA + fileName;
        File serverFile = new File(fileFullName);
        FileUtils.writeByteArrayToFile(serverFile, bytes);
        return fileName;
    }

    protected void deleteMedia(String nameMedia) throws IOException {
        FileUtils.forceDelete(new File(PATH_STORING_MEDIA + nameMedia));
    }
}
