package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.friendsPage.UsersPage;

import java.util.List;

public interface UserService {

    User getUserByLoginOrEmailAndPassword(String loginOrEmail) throws IllegalAccessException;

    void registrationUser(String firstName, String lastName, String birthDate, String login, String pass, String confirmPass, String email, String phone, Integer gender);

    UsersPage friendsSublist(int idUser, int pageNumber);

    User getUserById(int idUser);

    void update(User user);

    void confirmEmail(String hasLink);

    void discardRegistration(String hasLink);

    void createRestorePassword(String email);

    void removeRestorePassword(String hash);

    void createNewPassword(String loginOrEmail, String password, String confirmPassword);

    List<User> searchByName(String search);

    boolean isFriends(int idUser, int idCurrentUser);

    void inviteForFriendship(int idCurrentUser, int idUser);

    void applyFriendship(int idCurrentUser, int idUser);

    void removeFromFriends(int idCurrentUser, int idUser);

    boolean isInvitedForFriendship(int idCurrentUser, int idUser);

    Integer amountOfInvitations(int idUser);

    List<User> listInvitationsForFriendship(int idUser);

    void addFriendship(int idCurrentUser, int idUser);

    void declineInvitationForFriendship(int idCurrentUser, int idUser);

    UsersPage foundUsersSublist(String search, int pageNumber);
}
