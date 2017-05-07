package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.userService.friendsPage.FriendsPage;
import com.gmail.kolesnyk.zakhar.userService.userActivityMap.UserActivityMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class FriendsController {

    @Autowired
    private UserActivityMap userActivityMap;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/friend/{idFriend}", method = RequestMethod.POST)
    private ModelAndView goToFriend(@PathVariable(value = "idFriend") Integer idFriend) throws IOException, ServletException {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("friend_info_ajax");
            User friend = userService.getUserById(idFriend);
            if (userActivityMap.isOnline(friend.getIdUser())) {
                friend.setOnline(true);
            }
            modelAndView.addObject("friend", friend);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("errorPages/400");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/user/friends/{pageNumber}"}, method = RequestMethod.POST)
    private ModelAndView goToFriendsPage(@PathVariable("pageNumber") Integer pageNumber) throws ServletException, IOException {
        ModelAndView modelAndView;
        int idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        try {
            modelAndView = new ModelAndView("friends_ajax");
            FriendsPage friendsPage = userService.friendsSublist(idUser, pageNumber);
            modelAndView.addObject("friends", friendsPage.getPage());
            modelAndView.addObject("maxPage", friendsPage.getAmountPages());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("errorPages/400");
        } catch (ArrayStoreException e) {
            modelAndView = new ModelAndView("friends_list_empty");
        }
        return modelAndView;
    }
}
