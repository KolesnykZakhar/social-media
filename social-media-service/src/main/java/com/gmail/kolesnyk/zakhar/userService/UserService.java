package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public interface UserService {

    int AMOUNT_FRIENDS_ON_ONE_PAGE = 3;

    String DEFAULT_IMAGE_URL = "D:/social-media/media/avatars/default_avatar.png";

    User getUserByLoginOrEmailAndPassword(String loginOrEmail) throws IllegalAccessException;

    void registrationUser(String firstName, String lastName, String birthDate, String login, String pass, String confirmPass, String email, String phone, GENDER gender) throws IllegalAccessException;

    int getAmountFriends(Integer idUser);

    List<User> friendsSublist(int idUser, int pageNumber, int[] maxPage);

    User getUserById(int idUser);

    void update(User user);

    void confirmEmail(String hasLink);

    void discardRegistration(String hasLink);

    void createRestorePassword(String email);

    void removeRestorePassword(String hash);

    void createNewPassword(String loginOrEmail, String password, String confirmPassword);

    InputStream getAvatarUrlByUser(Integer idUser) throws FileNotFoundException;
}
