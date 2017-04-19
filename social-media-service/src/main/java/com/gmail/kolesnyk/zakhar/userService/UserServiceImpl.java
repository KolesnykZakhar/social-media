package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.email.SendMail;
import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static com.gmail.kolesnyk.zakhar.user.STATE.AVAILABLE;
import static com.gmail.kolesnyk.zakhar.user.STATE.WAITING_CONFIRM;

@Component
@Service
public class UserServiceImpl implements UserService {
    private static final int amountFriendsOnOnePage = 3;

    @Autowired
    private UserDao userDao;

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
        user.setAuthority(new HashSet<String>() {{
            add("ROLE_USER");
        }});
        String hashedEmail = passwordEncoder().encode(user.getEmail());
        userDao.save(user);
        System.out.println("idUser" + user.getIdUser());
        userDao.saveHashedEmail(hashedEmail, user.getIdUser());
//        userDao.saveHashedEmail(hashedEmail, 1);

        try {
            String msg = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("congratulations_with_registration.html"))
                    .replace("?hash?", hashedEmail).replace("?name?", user.getFirstName() + " " + user.getLastName());
            SendMail.send("socialmediantk@gmail.com", "socialNetwork", user.getEmail(), "Confirming email", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public int getAmountFriends(Integer idUser) {
        return userDao.amountFriends(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> friendsSublist(int idUser, int pageNumber, int[] maxPage) {
        int amountFriends = getAmountFriends(idUser);
        if (amountFriends == 0) {
            throw new ArrayStoreException("friends list empty");
        }
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
        User user = userDao.byHashedEmail(hashLink);
        user.setState(AVAILABLE);
        userDao.save(user);
        userDao.removeHashedEmail(hashLink);
    }

    @Override
    @Transactional
    public void discardRegistration(String hashLink) {
//        User user = userDao.byHashedEmail(hashLink);
//        userDao.remove(user);
        userDao.removeUserByHashedEmail(hashLink);
    }
}
