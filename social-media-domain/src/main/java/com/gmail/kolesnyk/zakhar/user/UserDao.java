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

    User byHashForEmail(String hashForEmail);

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
    List<User> friendSublist(Integer idUser, int offset, int amount);

    void saveHashForEmail(String hashForEmail, Integer idUser);

    Boolean removeUserByHashForEmail(String hashForEmail);


    Boolean removeHashForEmail(String hashForEmail);

    Boolean saveHashForPassword(String hashForPassword, Integer idUser);

    Boolean removeRestorePassword(String hash);

    List<User> searchByName(String search);

    boolean isFriends(int idCurrentUser, int idUser);

    void addFriend(int idCurrentUser, int idUser);

    void removeFriendship(int idCurrentUser, int idUser);

    boolean isInvitedForFriendship(int idCurrentUser, int idUser);

    void inviteForFriendship(int idCurrentUser, int idUser);

    Integer amountOfInvitations(int idUser);

    List<User> listInvitationsForFriendship(int idUser);

    void removeInvitationForFriendship(int idCurrentUser, int idUser);

    Integer amountFoundUsers(String search);

    List<User> searchByNameSublist(String search, int offset, int amount);

    Boolean hasPrivateBlog(int idUser);
}
