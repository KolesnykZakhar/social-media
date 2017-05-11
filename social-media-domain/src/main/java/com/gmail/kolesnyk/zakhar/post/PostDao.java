package com.gmail.kolesnyk.zakhar.post;

import com.gmail.kolesnyk.zakhar.BaseDao;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

/**
 * interface contains additional methods that required for ORM relations with {@link Post} class
 */
public interface PostDao extends BaseDao<Post, Integer> {

    @SuppressWarnings("unchecked")
    List<Post> postsSublistByIdUser(Integer idUser, int offset, int amount);

    /**
     * return list of posts by user
     */
    List<Post> listByUser(User user);

    /**
     * return list of posts by full text search
     */
    List<Post> listSearchString(String search);

    Integer amountPostsByIdUser(int idUser);
}
