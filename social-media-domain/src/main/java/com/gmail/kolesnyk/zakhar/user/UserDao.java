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

    User byHashedEmail(String hashedEmail);

    /**
     * return user by login
     */
    User selectByLogin(String login);

    /**
     * return list of friends by ID user
     */
    List<User> friendList(Integer idUser);

    /**
     * return amount of friends by ID user
     */
    int amountFriends(Integer idUser);

    /**
     * used to get range-list of friends by ID user
     *
     * @param offset - offset by list (exclude)
     * @param amount - amount of result list
     * @return List of Users
     */
    List<User> friendListByRange(Integer idUser, int offset, int amount);

    void saveHashedEmail(String hashedEmail, Integer idUser);

    void removeUserByHashedEmail(String hashedEmail);


    void removeHashedEmail(String hashedEmail);
}
