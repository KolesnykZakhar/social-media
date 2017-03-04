package com.gmail.kolesnyk.zakhar.jmail.userService;

import com.gmail.kolesnyk.zakhar.user.User;

public interface UserService {
    User getUserByLoginOrEmailAndPassword(String loginOrEmail, String password) throws IllegalAccessException;
}
