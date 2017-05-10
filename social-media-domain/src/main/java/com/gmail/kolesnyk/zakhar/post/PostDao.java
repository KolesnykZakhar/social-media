package com.gmail.kolesnyk.zakhar.post;

import com.gmail.kolesnyk.zakhar.BaseDao;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

/**
 * interface contains additional methods that required for ORM relations with {@link Post} class
 */
public interface PostDao extends BaseDao<Post, Integer> {

    /**
     * return list of posts by ID user
     */
    List<Post> fullListByIdUser(Integer idUser);

    @SuppressWarnings("unchecked")
    List<Post> shortListByIdUser(Integer idUser, int size);

    /**
     * return list of posts by user
     */
    List<Post> listByUser(User user);

    /**
     * return list of posts by full text search
     */
    List<Post> listSearchString(String search);
}
