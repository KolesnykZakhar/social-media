package com.gmail.kolesnyk.zakhar.servlet;

import com.gmail.kolesnyk.zakhar.jmail.userService.UserService;
import com.gmail.kolesnyk.zakhar.jmail.userService.UserServiceImpl;
import com.gmail.kolesnyk.zakhar.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ENTER DO GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Enter Servlet");
        UserService userService = new UserServiceImpl();
        resp.setContentType("text/html");
        User user;
        try {
            user = userService.getUserByLoginOrEmailAndPassword(req.getParameter("loginname"), req.getParameter("password"));
        } catch (IllegalAccessException e) {
            resp.sendRedirect("/l.jsp");
        }
        System.out.println("SUCCESS");

    }
}
