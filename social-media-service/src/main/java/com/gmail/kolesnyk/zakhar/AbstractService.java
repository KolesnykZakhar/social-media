package com.gmail.kolesnyk.zakhar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * The {@code AbstractService} service class contained main instruments that used in child Service classes
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.userService.UserService
 * @see com.gmail.kolesnyk.zakhar.postService.PostService
 * @see com.gmail.kolesnyk.zakhar.mediaService.MediaService
 * @see com.gmail.kolesnyk.zakhar.chatService.ChatService
 * @since JDK1.8
 */
@Service
public abstract class AbstractService {

    /**
     * amount user that contained on one {@link com.gmail.kolesnyk.zakhar.userService.friendsPage.UsersPage}
     */
    final protected int AMOUNT_USERS_ON_ONE_PAGE;

    /**
     * path in hard disk of server where will storing media files
     */
    final protected String PATH_STORING_MEDIA;

    /**
     * path in hard disk of server where stored image for avatars of Users
     */
    final protected String DEFAULT_AVATAR_URL;

    /**
     * path in hard disk of server where will storing images of avatars of Users
     */
    final protected String ROOT_AVATAR_URL;

    /**
     * extends what will used for creating and reading image file of avatars of Users
     */
    final protected String AVATAR_EXTENDS;

    /**
     * email address of site. That address will be used for sending emails to Users
     */
    final protected String SERVICE_EMAIL;

    /**
     * password to email address of site. That address will be used for sending emails to Users
     */
    final protected String PASSWORD_EMAIL;

    /**
     * amount posts one one page {@link com.gmail.kolesnyk.zakhar.postService.postPages.PostPage}
     */
    final protected int AMOUNT_POSTS_ON_ONE_PAGE;

    /**
     * amount last messages in chat between two Users {@link com.gmail.kolesnyk.zakhar.chatService.chat.Chat}
     */
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

    /**
     * method allow to store media file to hard disk of server
     *
     * @param file File that need to store
     * @return created after storing new name of stored File
     */
    protected String storeMedia(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = System.currentTimeMillis() + "." + ext;
        String fileFullName = PATH_STORING_MEDIA + fileName;
        File serverFile = new File(fileFullName);
        FileUtils.writeByteArrayToFile(serverFile, bytes);
        return fileName;
    }

    /**
     * method allow to remove media file from hard disk of server by it name
     *
     * @param nameMedia name of media file
     */
    protected void deleteMedia(String nameMedia) throws IOException {
        FileUtils.forceDelete(new File(PATH_STORING_MEDIA + nameMedia));
    }
}
