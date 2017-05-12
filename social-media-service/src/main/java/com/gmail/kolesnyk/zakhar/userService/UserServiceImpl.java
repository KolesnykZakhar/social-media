package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.AbstractService;
import com.gmail.kolesnyk.zakhar.email.SendMail;
import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.VISIBILITY;
import com.gmail.kolesnyk.zakhar.userService.friendsPage.UsersPage;
import com.gmail.kolesnyk.zakhar.userService.userActivityMap.UserActivityMap;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static com.gmail.kolesnyk.zakhar.user.STATE.AVAILABLE;
import static com.gmail.kolesnyk.zakhar.user.STATE.WAITING_CONFIRM;
import static com.gmail.kolesnyk.zakhar.user.VISIBILITY.PUBLIC;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserActivityMap userActivityMap;

    public UserServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLoginOrEmailAndPassword(String loginOrEmail) throws IllegalAccessException {
        if (loginOrEmail.contains("@")) {
            return userDao.selectByEmail(loginOrEmail);
        } else {
            return userDao.selectByLogin(loginOrEmail);
        }
    }

    @Override
    @Transactional
    public void registrationUser(String firstName, String lastName, String birthDate, String login, String pass, String confirmPass, String email, String phone, GENDER gender) throws IllegalAccessException {
        if (!pass.trim().equals(confirmPass.trim())) {
            throw new IllegalArgumentException("passwords not match");
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setLogin(login);
        user.setPass(passwordEncoder().encode(pass.trim()));
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setState(WAITING_CONFIRM);
        user.setVisibility(PUBLIC);
        user.setAuthority(new HashSet<String>() {{
            add("ROLE_USER");
        }});
        String hashForEmail = passwordEncoder().encode(user.getEmail());
        userDao.save(user);
        userDao.saveHashForEmail(hashForEmail, user.getIdUser());
        try {
            String msg = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("congratulations_with_registration.html"))
                    .replace("?hash?", hashForEmail).replace("?name?", user.getFirstName() + " " + user.getLastName());
            SendMail.send(SERVICE_EMAIL, PASSWORD_EMAIL, user.getEmail(), "Confirming email", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UsersPage friendsSublist(int idUser, int pageNumber) {
        int amountFriends = userDao.amountFriends(idUser);
        if (amountFriends == 0) {
            throw new ArrayStoreException("friends list empty");
        }
        int amountPages = amountFriends / AMOUNT_USERS_ON_ONE_PAGE;
        int remainder = amountFriends % AMOUNT_USERS_ON_ONE_PAGE;
        if (remainder != 0) {
            amountPages++;
        }
        if (pageNumber > amountPages || pageNumber < 0) {
            throw new IllegalArgumentException("wrong number of friends page");
        }
        List<User> resultList = userDao.friendSublist(idUser, pageNumber * AMOUNT_USERS_ON_ONE_PAGE - AMOUNT_USERS_ON_ONE_PAGE, AMOUNT_USERS_ON_ONE_PAGE);
        resultList.forEach(user -> {
            if (userActivityMap.isOnline(user.getIdUser())) {
                user.setOnline(true);
            }
        });
        return new UsersPage(resultList, amountPages);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int idUser) {
        return userDao.selectById(idUser);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            private final Md5PasswordEncoder md5 = new Md5PasswordEncoder();

            @Override
            public String encode(CharSequence rawPassword) {
                return md5.encodePassword(rawPassword.toString(), 1);
            }

            @Override
            @SuppressWarnings("PMD")
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return (md5.encodePassword(rawPassword.toString(), 1)).equals(encodedPassword);
            }
        };
    }

    @Override
    @Transactional
    public void confirmEmail(String hashLink) {
        User user = userDao.byHashForEmail(hashLink);
        user.setState(AVAILABLE);
        userDao.save(user);
        if (!userDao.removeHashForEmail(hashLink)) {
            throw new UnsupportedOperationException("query not updated database");
        }
    }

    @Override
    @Transactional
    public void discardRegistration(String hashLink) {
        if (!userDao.removeUserByHashForEmail(hashLink)) {
            throw new UnsupportedOperationException("query not updated database");
        }
    }

    @Override
    @Transactional
    public void createRestorePassword(String email) {
        User user = userDao.selectByEmail(email);
        String hashForPassword = passwordEncoder().encode(user.getLogin() + user.getPass());
        if (!userDao.saveHashForPassword(hashForPassword, user.getIdUser())) {
            throw new UnsupportedOperationException("query not updated database");
        }
        try {
            String msg = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("restore_password.html"))
                    .replace("?hash?", hashForPassword).replace("?name?", user.getFirstName() + " " + user.getLastName());
            SendMail.send(SERVICE_EMAIL, PASSWORD_EMAIL, user.getEmail(), "Restore password", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void removeRestorePassword(String hash) {
        if (!userDao.removeRestorePassword(hash)) {
            throw new UnsupportedOperationException("restore password not removed");
        }
    }

    @Override
    @Transactional
    public void createNewPassword(String loginOrEmail, String password, String confirmPassword) {
        if (!password.trim().equals(confirmPassword.trim())) {
            throw new IllegalArgumentException("passwords not match");
        }
        User user;
        if (loginOrEmail.contains("@")) {
            user = userDao.selectByEmail(loginOrEmail);
        } else {
            user = userDao.selectByLogin(loginOrEmail);
        }
        user.setPass(passwordEncoder().encode(password.trim()));
        userDao.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> searchByName(String search) {
        List<User> resultList = userDao.searchByName(search);
        resultList.forEach(user -> {
            if (userActivityMap.isOnline(user.getIdUser())) {
                user.setOnline(true);
            }
        });
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isFriends(int idCurrentUser, int idUser) {
        return userDao.isFriends(idCurrentUser, idUser);
    }

    @Override
    @Transactional
    public void inviteForFriendship(int idCurrentUser, int idUser) {
        userDao.inviteForFriendship(idCurrentUser, idUser);
    }

    @Override
    @Transactional
    public void applyFriendship(int idCurrentUser, int idUser) {
        userDao.addFriend(idCurrentUser, idUser);
    }

    @Override
    @Transactional
    public void removeFromFriends(int idCurrentUser, int idUser) {
        userDao.removeFriendship(idCurrentUser, idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isInvitedForFriendship(int idCurrentUser, int idUser) {
        return userDao.isInvitedForFriendship(idCurrentUser, idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer amountOfInvitations(int idUser) {
        return userDao.amountOfInvitations(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listInvitationsForFriendship(int idUser) {
        List<User> resultList = userDao.listInvitationsForFriendship(idUser);
        resultList.forEach(user -> {
            if (userActivityMap.isOnline(user.getIdUser())) {
                user.setOnline(true);
            }
        });
        return resultList;
    }

    @Override
    @Transactional
    public void addFriendship(int idCurrentUser, int idUser) {
        userDao.addFriend(idCurrentUser, idUser);
    }

    @Override
    @Transactional
    public void declineInvitationForFriendship(int idCurrentUser, int idUser) {
        userDao.removeInvitationForFriendship(idCurrentUser, idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UsersPage foundUsersSublist(String search, int pageNumber) {
        int amountUsers = userDao.amountFoundUsers(search);
        if (amountUsers == 0) {
            throw new ArrayStoreException("users not found");
        }
        int amountPages = amountUsers / AMOUNT_USERS_ON_ONE_PAGE;
        int remainder = amountUsers % AMOUNT_USERS_ON_ONE_PAGE;
        if (remainder != 0) {
            amountPages++;
        }
        if (pageNumber > amountPages || pageNumber < 0) {
            throw new IllegalArgumentException("wrong number of users page");
        }
        List<User> resultList = userDao.searchByNameSublist(search, pageNumber * AMOUNT_USERS_ON_ONE_PAGE - AMOUNT_USERS_ON_ONE_PAGE, AMOUNT_USERS_ON_ONE_PAGE);
        resultList.forEach(user -> {
            if (userActivityMap.isOnline(user.getIdUser())) {
                user.setOnline(true);
            }
        });
        return new UsersPage(resultList, amountPages, search);
    }
}
