package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.util.ViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ViewUtil viewUtil;

    @RequestMapping(value = {"/user/index"})
    public ModelAndView goToIndex() throws ServletException, IOException {
        ModelAndView modelAndView;
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView = new ModelAndView("index");
        modelAndView.addObject("isAdmin", userDetails.getAuthority().contains("ROLE_ADMIN"));
        return modelAndView;
    }

    @RequestMapping(value = {"/user/update_user_info"})
    public String updateUserInfo(@RequestParam("birthDate") String birthDate, @RequestParam("gender") Integer gender,
                                 @RequestParam("firstName") String firstName, @RequestParam("lastName")  String lastName, @RequestParam("phone") String phone) throws ServletException, IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setGender(GENDER.values()[gender]);
        user.setPhone(phone);
        viewUtil.updateUserDomainVersion(user);
        return "ok_ajax";
    }

    @RequestMapping(value = {"/user/settings_profile"})
    public ModelAndView goToSettingsProfile() throws ServletException, IOException {
        ModelAndView modelAndView;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView = new ModelAndView("settings_profile");
        modelAndView.addObject("isAdmin", user.getAuthority().contains("ROLE_ADMIN"));
        modelAndView.addObject("user", viewUtil.getUserViewVersion());
        return modelAndView;
    }
}
