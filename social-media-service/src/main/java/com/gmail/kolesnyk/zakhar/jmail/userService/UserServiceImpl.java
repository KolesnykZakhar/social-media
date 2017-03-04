package com.gmail.kolesnyk.zakhar.jmail.userService;

import com.gmail.kolesnyk.zakhar.jmail.validation.encrypt.Encryptor;
import com.gmail.kolesnyk.zakhar.jmail.validation.encrypt.EncryptorMD5;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private Encryptor encryptor;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        encryptor = new EncryptorMD5();
    }

    @Override
    public User getUserByLoginOrEmailAndPassword(String loginOrEmail, String password) throws IllegalAccessException {
        User user;
        if (loginOrEmail.contains("@")) {
            user = userDao.selectByEmail(loginOrEmail);
            System.out.println(user.getFirstName());
        } else {
            user = userDao.selectByLogin(loginOrEmail);
            System.out.println(user.getFirstName());
        }

        if (!user.getPass().equals(encryptor.encryptPassword(password))) {
            throw new IllegalAccessException("wrong login or password");
        }
        return user;
    }
}
