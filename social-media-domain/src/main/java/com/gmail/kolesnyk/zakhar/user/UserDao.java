package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.BaseDao;

import java.util.List;

/**
 * interface contains additional methods that required for ORM relations with {@link User} class
 */
public interface UserDao extends BaseDao<User, Integer> {

    /**
     * return user by phone
     */
    User selectByPhone(String phone);

    /**
     * return user by email
     */
    User selectByEmail(String email);

    /**
     * return user by login
     */
    User selectByLogin(String login);

    /**
     * return list of friends by ID user
     */
    List<User> friendList(Integer idUser);
}
