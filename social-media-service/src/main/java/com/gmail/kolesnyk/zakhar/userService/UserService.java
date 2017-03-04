package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.user.User;

public interface UserService {
    User getUserByLoginOrEmailAndPassword(String loginOrEmail, String password) throws IllegalAccessException;
}
