package com.gmail.kolesnyk.zakhar.controller.access.servlet;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.userService.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@WebServlet(urlPatterns = {"/index/friends"/*, "/index/friend"*/})
public class FriendsController/* extends HttpServlet */{
    private UserService userService = new UserServiceImpl();

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        goToIndex(req, resp);
//    }

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        goToFriend(req, resp);
//
//    }
    @RequestMapping(value = "/index/friend", method = RequestMethod.GET)
    private ModelAndView goToFriend(HttpServletRequest req/*, HttpServletResponse resp*/) throws IOException, ServletException {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("friend_info_ajax");
            int idFriend = Integer.parseInt(req.getParameter("idFriend"));
            User friend = userService.getUserById(idFriend);
//            req.setAttribute("friend", friend);
            modelAndView.addObject("friend", friend);

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("../errorPages/400");
//            resp.sendRedirect("/errorPages/400.jsp");
        }
        return modelAndView;
//        resp.setContentType("text/html");
//        req.getRequestDispatcher("/WEB-INF/friend_info_ajax.jsp").forward(req, resp);
    }

    @RequestMapping(value = {"/index/friends/{pageNumber}"}, method = RequestMethod.POST)
    private ModelAndView goToIndex(@PathVariable("pageNumber") Integer pageNumber, @SessionAttribute("user") User user/*HttpServletRequest req*//*, HttpServletResponse resp*/) throws ServletException, IOException {
        ModelAndView modelAndView;
//        int idUser = ((User) req.getSession().getAttribute("user")).getIdUser();
        int idUser = user.getIdUser();
//        int pageNumber = index;
//        if (req.getParameter("pageNumber") == null || req.getParameter("pageNumber").equals("0")) {
//            pageNumber = 1;
//        } else {
//            try {
//                pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                modelAndView = new ModelAndView("../errorPages/400");
////                resp.sendRedirect("/errorPages/400.jsp");
//            }
//        }
        try {
            modelAndView = new ModelAndView("friends_ajax");
            /* maxPage[0] value set inside method userService.friendsSublist()*/
            int[] maxPage = new int[1];
//            req.setAttribute("friends", userService.friendsSublist(idUser, pageNumber, maxPage));
            modelAndView.addObject("friends", userService.friendsSublist(idUser, pageNumber, maxPage));
//            req.setAttribute("maxPage", maxPage[0]);
            modelAndView.addObject("maxPage", maxPage[0]);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("../errorPages/400");
//            resp.sendRedirect("/errorPages/400.jsp");
        }
//        resp.setContentType("text/html");
//        req.getRequestDispatcher("/WEB-INF/friends_ajax.jsp").forward(req, resp);


        return modelAndView;
    }
}
