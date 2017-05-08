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
import java.util.List;

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

    @RequestMapping(value = "/user/search_user/{searchUser}", method = RequestMethod.POST)
    private ModelAndView searchUsers(@PathVariable(value = "searchUser") String search) {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("found_users");
            List<User> foundUsers = userService.searchByName(search);
            modelAndView.addObject("foundUsers", foundUsers);
        } catch (Exception e) {
            modelAndView = new ModelAndView("not_found_users");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/user/{idUser}", method = RequestMethod.POST)
    private ModelAndView goToUser(@PathVariable(value = "idUser") Integer idUser) throws IOException, ServletException {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("user_info_ajax");
            int idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
            User user = userService.getUserById(idUser);
            boolean isFriends = userService.isFriends(idCurrentUser, user.getIdUser());
            modelAndView.addObject("isFriend", isFriends);
            if (!isFriends) {
                modelAndView.addObject("isInvited", userService.isInvitedForFriendship(idCurrentUser, user.getIdUser()));
            }
            if (userActivityMap.isOnline(user.getIdUser())) {
                user.setOnline(true);
            }
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("errorPages/400");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/user/add_to_friends/{idUser}"})
    public String addToFriends(@PathVariable("idUser") Integer idUser) throws ServletException, IOException {
        int idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        userService.inviteForFriendship(idCurrentUser, idUser);
        return "ok_ajax";
    }

    @RequestMapping(value = {"/user/remove_from_friends/{idUser}"})
    public String removeFromFriends(@PathVariable("idUser") Integer idUser) throws ServletException, IOException {
        try {
            int idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
            userService.removeFromFriends(idCurrentUser, idUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/400";
        }
        return "ok_ajax";
    }

    @RequestMapping(value = {"/user/invitations_for_friendship"})
    public ModelAndView invitationsForFriendship() throws ServletException, IOException {
        ModelAndView modelAndView;
        int idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        List<User> listInvitations = userService.listInvitationsForFriendship(idUser);
        if (listInvitations != null && listInvitations.size() > 0) {
            modelAndView = new ModelAndView("invitations_for_friendship_ajax");
            modelAndView.addObject("listInvitations", listInvitations);
        } else {
            modelAndView = new ModelAndView("list_invitations_for_friendship_empty");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/user/accept_invitation/{idUser}"})
    public String acceptInvitationForFriendship(@PathVariable("idUser") Integer idUser) throws ServletException, IOException {
        try {
            int idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
            userService.addFriendship(idCurrentUser, idUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/400";
        }
        return "ok_ajax";
    }

    @RequestMapping(value = {"/user/decline_invitation/{idUser}"})
    public String declineInvitationForFriendship(@PathVariable("idUser") Integer idUser) throws ServletException, IOException {
        try {
            int idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
            userService.declineInvitationForFriendship(idCurrentUser, idUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/400";
        }
        return "ok_ajax";
    }

    @RequestMapping(value = "/user/view_invitation/{idUser}", method = RequestMethod.POST)
    private ModelAndView goToInvitation(@PathVariable(value = "idUser") Integer idUser) throws IOException, ServletException {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("invitations_info_ajax");
            User user = userService.getUserById(idUser);
            if (userActivityMap.isOnline(user.getIdUser())) {
                user.setOnline(true);
            }
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("errorPages/400");
        }
        return modelAndView;
    }
}
