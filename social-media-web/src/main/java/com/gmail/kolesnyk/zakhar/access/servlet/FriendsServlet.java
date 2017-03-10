package com.gmail.kolesnyk.zakhar.access.servlet;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.userService.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index/friends", "/index/friend"})
public class FriendsServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        goToIndex(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        goToFriend(req, resp);

    }

    private void goToFriend(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            int idFriend = Integer.parseInt(req.getParameter("idFriend"));
            User friend = userService.getUserById(idFriend);
            req.setAttribute("friend", friend);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/errorPages/400.jsp");
        }
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/friend_info_ajax.jsp").forward(req, resp);
    }

    private void goToIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = ((User) req.getSession().getAttribute("user")).getIdUser();
        int pageNumber = 0;
        if (req.getParameter("pageNumber") == null || req.getParameter("pageNumber").equals("0")) {
            pageNumber = 1;
        } else {
            try {
                pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.sendRedirect("/errorPages/400.jsp");
            }
        }
        try {
            int[] maxPage = new int[1];
            req.setAttribute("friends", userService.friendsSublist(idUser, pageNumber, maxPage));
            req.setAttribute("maxPage", maxPage[0]);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            resp.sendRedirect("/errorPages/400.jsp");
        }
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/friends_ajax.jsp").forward(req, resp);
    }
}
