package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;
import com.gmail.kolesnyk.zakhar.validation.encrypt.Encryptor;
import com.gmail.kolesnyk.zakhar.validation.encrypt.EncryptorMD5;

import java.sql.Timestamp;

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
        } else {
            user = userDao.selectByLogin(loginOrEmail);
        }
        if (!user.getPass().equals(encryptor.encryptPassword(password))) {
            throw new IllegalAccessException("wrong login or password");
        }
        return user;
    }

    @Override
    public User registrationUser(String firstName, String lastName, Timestamp birthDate, String login, String pass, String confirmPass, String email, String phone) throws IllegalAccessException {
        if (!pass.trim().equals(confirmPass.trim())) {
            throw new IllegalArgumentException("passwords not match");
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setLogin(login);
        user.setPass(encryptor.encryptPassword(pass));
        user.setEmail(email);
        user.setPhone(phone);
        userDao.save(user);
        return user;
    }
}
