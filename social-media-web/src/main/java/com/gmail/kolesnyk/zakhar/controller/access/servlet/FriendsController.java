package com.gmail.kolesnyk.zakhar.controller.access.servlet;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.userService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class FriendsController {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @RequestMapping(value = "/index/friend", method = RequestMethod.GET)
    private ModelAndView goToFriend(@RequestParam(value = "idFriend") Integer idFriend) throws IOException, ServletException {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("friend_info_ajax");
            User friend = userService.getUserById(idFriend);
            System.out.println("\n\n\n\"");
            System.out.println(idFriend);
            System.out.println(friend.getFirstName());
            System.out.println("\n\n\n\"");
            modelAndView.addObject("friend", friend);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("../errorPages/400");
        }
        return modelAndView;
    }

//    @Transactional(readOnly = true)
    @RequestMapping(value = {"/index/friends/{pageNumber}"}, method = RequestMethod.POST)
    private ModelAndView goToFriendsPage(@PathVariable("pageNumber") Integer pageNumber, @SessionAttribute("user") User user) throws ServletException, IOException {
        ModelAndView modelAndView;
        int idUser = user.getIdUser();
        try {
            modelAndView = new ModelAndView("friends_ajax");
            /* maxPage[0] value set inside method userService.friendsSublist()*/
            int[] maxPage = new int[1];
            modelAndView.addObject("friends", userService.friendsSublist(idUser, pageNumber, maxPage));
            modelAndView.addObject("maxPage", maxPage[0]);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("../errorPages/400");
        }
        return modelAndView;
    }
}
