package com.gmail.kolesnyk.zakhar.util;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ViewUtil {

    @Autowired
    private UserService userService;

    public User getUserViewVersion() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userView = new User();
        userView.setIdUser(user.getIdUser());
        userView.setUsername(user.getUsername());
        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());
        userView.setLogin(user.getLogin());
        userView.setEmail(user.getEmail());
        userView.setPhone(user.getPhone());
        userView.setBirthDate(user.getBirthDate());
        userView.setGender(user.getGender());
        return userView;
    }

    public User updateUserDomainVersion(User user) {
        User userDomain = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDomain.setUsername(user.getUsername());
        userDomain.setFirstName(user.getFirstName());
        userDomain.setLastName(user.getLastName());
        userDomain.setLogin(user.getLogin());
        userDomain.setEmail(user.getEmail());
        userDomain.setPhone(user.getPhone());
        userDomain.setBirthDate(user.getBirthDate());
        userDomain.setGender(user.getGender());
        userService.update(userDomain);
        return userDomain;
    }
}
