package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.email.SendMail;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;
import com.gmail.kolesnyk.zakhar.validation.encrypt.Encryptor;
import com.gmail.kolesnyk.zakhar.validation.encrypt.EncryptorMD5;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final int amountFriendsOnOnePage = 3;
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

        try {
            String msg = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("congratulations_with_registration.html"))
                    .replace("?link?", "http://localhost:8080/login.jsp").replace("?name?", user.getFirstName() + " " + user.getLastName());
            SendMail.send("socialmediantk@gmail.com", "socialNetwork", user.getEmail(), "Congratulations", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int getAmountFriends(Integer idUser) {
        return userDao.amountFriends(idUser);
    }

    @Override
    public List<User> friendsSublist(int idUser, int pageNumber, int[] maxPage) {
        int amountFriends = getAmountFriends(idUser);
        int amountPages = amountFriends / amountFriendsOnOnePage;
        maxPage[0] = amountPages;
        int remainder = amountFriends % amountFriendsOnOnePage;
        if (remainder != 0) {
            amountPages++;
        }
        if (pageNumber > amountPages || pageNumber < 0) {
            throw new IllegalArgumentException("wrong number of friends page");
        }

        return userDao.friendListByRange(idUser, pageNumber * amountFriendsOnOnePage - amountFriendsOnOnePage, amountFriendsOnOnePage);
    }

    @Override
    public User getUserById(int idUser) {
        return userDao.selectById(idUser);
    }
}
