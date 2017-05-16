package com.gmail.kolesnyk.zakhar.post;

import com.gmail.kolesnyk.zakhar.BaseDao;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

/**
 * The {@code MessageDao} class DAO for entity {@link Post}
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.AbstractDao
 * @see com.gmail.kolesnyk.zakhar.post.PostDaoImpl
 * @since JDK1.8
 */
public interface PostDao extends BaseDao<Post, Integer> {

    /**
     * method allowed to get sublist of posts by user {@link List<Post>}
     * by ID of User, offset and amount of returned posts
     *
     * @param idUser ID of User
     * @param offset offset
     * @return {@link List<Post>} list of posts
     */
    @SuppressWarnings("unchecked")
    List<Post> postsSublistByIdUser(Integer idUser, int offset, int amount);

    /**
     * method allowed to get all posts by user {@link List<Post>}
     * by example of User
     *
     * @param user User
     * @return {@link List<Post>} list of posts
     */
    List<Post> listByUser(User user);

    /**
     * method allowed to search post from all posts {@link List<Post>}
     * by text that contained in Post
     *
     * @param search text for searching
     * @return {@link List<Post>} list of posts
     */
    List<Post> listSearchString(String search);

    /**
     * method allowed to get amount posts {@link List<Post>} of User
     * by ID User
     *
     * @param idUser ID User
     * @return {@link Integer} amount posts
     */
    Integer amountPostsByIdUser(int idUser);

    /**
     * method allowed to get sublist posts {@link List<Post>} of User
     * that User added to it bookmarks
     * by ID User, offset and amount
     *
     * @param idUser ID User
     * @param offset offset
     * @param amount amount
     * @return {@link List<Post>} list posts
     */
    List<Post> sublistBookmarksByUser(int idUser, int offset, int amount);

    /**
     * method allowed to get amount posts {@link List<Post>} of User
     * that User added to it bookmarks
     * by ID User
     *
     * @param idUser ID User
     * @return {@link Integer} amount bookmarks
     */
    Integer amountBookmarksByIdUser(int idUser);

    /**
     * method allowed to sublist of all posts {@link List<Post>} that User can to see by ID User,
     * ordered by publication date started from younger date
     * Means: posts of current User, post Friends of current User and posts of other Users what have public blog
     *
     * @param idUser ID User
     * @param offset offset
     * @param amount amount
     * @return {@link List<Post>} list Posts
     */
    List<Post> sublistNews(int idUser, int offset, int amount);

    /**
     * method allowed to get amount of all posts {@link List<Post>} that User can to see by ID User,
     * ordered by publication date started from younger date
     * Means: posts of current User, post Friends of current User and posts of other Users what have public blog
     *
     * @param idUser ID User
     * @return {@link Integer} amount of posts
     */
    Integer amountNewsByIdUser(int idUser);

    /**
     * method allowed to add bookmark by ID User and ID Post
     *
     * @param idUser ID User
     * @param idPost ID Post
     */
    void addBookmark(int idUser, int idPost);

    /**
     * method allowed to remove bookmark by ID User and ID Post
     *
     * @param idUser ID User
     * @param idPost ID Post
     */
    void deleteBookmark(int idUser, int idPost);

    /**
     * method allowed to get knows about existing of concrete bookmark by ID User and ID Post
     *
     * @param idUser ID User
     * @param idPost ID Post
     */
    Boolean isExistBookmark(int idUser, int idPost);
}
