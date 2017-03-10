package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.user.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    User getUserByLoginOrEmailAndPassword(String loginOrEmail, String password) throws IllegalAccessException;

    User registrationUser(String firstName, String lastName, Timestamp birthDate, String login, String pass, String confirmPass, String email, String phone) throws IllegalAccessException;

    int getAmountFriends(Integer idUser);

    List<User> friendsSublist(int idUser, int pageNumber, int[] maxPage);

    User getUserById(int idUser);
}
