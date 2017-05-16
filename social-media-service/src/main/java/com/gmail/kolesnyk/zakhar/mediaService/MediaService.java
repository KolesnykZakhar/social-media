package com.gmail.kolesnyk.zakhar.mediaService;

import com.gmail.kolesnyk.zakhar.image.Image;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The {@code MediaService} service class for operations associated with media files
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.Chat
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatHeader
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu
 * @since JDK1.8
 */
public interface MediaService {

    /**
     * method allowed to store image of avatar in system by file and ID User
     *
     * @param file   file to storing
     * @param idUser ID User
     */
    void storeAvatar(MultipartFile file, int idUser) throws IOException;

    /**
     * method allowed to get avatar from system by ID User in byte array form
     *
     * @param idUser ID User
     * @return array that contains bytes of avatar image
     */
    byte[] getAvatarByUser(Integer idUser) throws IOException;

    /**
     * method allowed to get list of images {@link List<Image>} by ID User
     *
     * @param idUser ID User
     * @return {@link List<Image>} list of images
     */
    List<Image> imagesByIdUser(int idUser);

    /**
     * method allowed to get media file from system by it name in byte array form
     *
     * @param nameMedia name of media
     * @return array that contains bytes of media file
     */
    byte[] getMediaByName(String nameMedia) throws IOException;

    /**
     * method allowed to save media file to system by file and example of User
     *
     * @param file media file
     * @param user example of User
     */
    void storeImage(MultipartFile file, User user) throws IOException;

    /**
     * method allowed to delete media file from system by it name and ID
     *
     * @param nameMedia media file name
     * @param idMedia   ID media
     */
    void deleteFileMedia(String nameMedia, int idMedia) throws IOException;

    /**
     * method allowed to get media file from system by it name
     *
     * @param nameMedia media file name
     * @return {@link File} example of media file
     */
    File getFileByName(String nameMedia);
}
