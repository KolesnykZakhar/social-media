package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.chatService.ChatService;
import com.gmail.kolesnyk.zakhar.image.Image;
import com.gmail.kolesnyk.zakhar.mediaService.MediaService;
import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.VISIBILITY;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    private static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = {"/user/index"})
    public ModelAndView goToIndex() throws ServletException, IOException {
        ModelAndView modelAndView;
        List<Image> images = mediaService.imagesByIdUser(currentUser().getIdUser());
        modelAndView = new ModelAndView("index");
        modelAndView.addObject("isAdmin", currentUser().getAuthority().contains("ROLE_ADMIN"));
        modelAndView.addObject("images", images);
        modelAndView.addObject("idUser", currentUser().getIdUser());
        Integer amountOfInvitations = userService.amountOfInvitations(currentUser().getIdUser());
        if (amountOfInvitations != null && amountOfInvitations > 0) {
            modelAndView.addObject("amountOfInvitations", amountOfInvitations);
        }
        Integer amountUnreadMessages = chatService.amountUnreadMessages(currentUser().getIdUser());
        if (amountUnreadMessages != null && amountUnreadMessages > 0) {
            modelAndView.addObject("amountUnreadMessages", amountUnreadMessages);
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/user/update_user_info"})
    public String updateUserInfo(@RequestParam("birthDate") String birthDate, @RequestParam("gender") Integer gender, @RequestParam("visibility") Integer visibility,
                                 @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("phone") String phone) throws ServletException, IOException {
        User user = currentUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setGender(GENDER.values()[gender]);
        user.setVisibility(VISIBILITY.values()[visibility]);
        user.setPhone(phone);
        userService.update(user);
        return "ok_ajax";
    }

    @RequestMapping(value = {"/user/settings_profile"})
    public ModelAndView goToSettingsProfile() throws ServletException, IOException {
        ModelAndView modelAndView;
        User user = currentUser();
        modelAndView = new ModelAndView("settings_profile");
        modelAndView.addObject("isAdmin", user.getAuthority().contains("ROLE_ADMIN"));
        modelAndView.addObject("user", currentUser());
        return modelAndView;
    }

    @RequestMapping(value = "/user/ok", method = RequestMethod.GET)
    public String okPage(){
        return "ok";
    }
}
