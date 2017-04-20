package com.gmail.kolesnyk.zakhar.controller.access;

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
    public String updateUserInfo(@RequestParam("birthDate") String birthDate) throws ServletException, IOException {
//        ModelAndView modelAndView;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        modelAndView = new ModelAndView("settings_profile");
//        modelAndView.addObject("isAdmin", user.getAuthority().contains("ROLE_ADMIN"));
//        modelAndView.addObject("user", getUserViewVersion());
        System.out.println(birthDate);
//        Timestamp timestamp=Timestamp.valueOf(birthDate + " 00:00:00");
        user.setBirthDate(birthDate);
        viewUtil.updateUserDomainVersion(user);

//        System.out.println(timestamp);
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
