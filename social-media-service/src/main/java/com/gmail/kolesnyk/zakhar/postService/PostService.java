package com.gmail.kolesnyk.zakhar.postService;

import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.postService.postPages.PostPage;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The {@code PostService} service class for operations associated with Posts Users
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.Chat
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatHeader
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu
 * @since JDK1.8
 */
public interface PostService {

    /**
     * method allowed to get sublist of Posts by ID User
     *
     * @param idUser         ID User-author of Posts
     * @param pageNumber number of page
     * @param idCurrentUser ID User that will view Posts
     * @return {@link PostPage} example of page with Posts
     */
    PostPage sublistPostsByUser(int idUser, int pageNumber, int idCurrentUser);

    /**
     * method allowed to create and save Post to system with it media files
     *
     * @param user example of User-author of Post
     * @param textPost text that contains in Post
     * @param files varargs of files that associated with Post
     */
    void createAndSavePost(User user, String textPost, MultipartFile... files) throws IOException;

    /**
     * method allowed to delete Post from system with it media files
     *
     * @param idPost ID Post
     */
    void deletePostById(int idPost);

    /**
     * method allowed to get Page of Bookmarks by ID User and number of Page
     *
     * @param idUser ID User
     * @param pageNumber number of page that need to get
     * @return {@link PostPage} example page of Bookmarks
     */
    PostPage sublistBookmarksByUser(int idUser, int pageNumber);

    /**
     * method allowed to get know that User has private blog
     *
     * @param idUser ID User
     * @return true if User has private blog, and false if it has public blog
     */
    boolean hasPrivateBlog(int idUser);

    /**
     * method allowed to get Page of News by ID User and number of Page
     *
     * @param idUser ID User
     * @param pageNumber number of page that need to get
     * @return {@link PostPage} example page of News
     */
    PostPage sublistNews(int idUser, int pageNumber);

    /**
     * method allowed to create Bookmark in system by ID User and ID Post
     *
     * @param idUser ID User
     * @param idPost ID Post
     */
    void addBookmark(int idUser, int idPost);

    /**
     * method allowed to delete Bookmark from system by ID User and ID Post
     *
     * @param idUser ID User
     * @param idPost ID Post
     */
    void removeBookmark(int idUser, int idPost);
}
