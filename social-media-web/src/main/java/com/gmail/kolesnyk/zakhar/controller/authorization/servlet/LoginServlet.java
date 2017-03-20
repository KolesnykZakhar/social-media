package com.gmail.kolesnyk.zakhar.controller.authorization.servlet;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.userService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = {"/login"})
@Controller
public class LoginServlet/* extends HttpServlet */{
    @Autowired
    private UserService userService;
//    private UserService userService = new UserServiceImpl();

//    @Override
//    @Bean
    @RequestMapping(value = {"/login"})
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userService.getUserByLoginOrEmailAndPassword(req.getParameter("loginOrEmail"), req.getParameter("password"));
            resp.addCookie(new Cookie("auth", req.getSession().getId()));
            req.getSession().setAttribute("user", user);
            resp.setContentType("text/html");
            req.getRequestDispatcher("/index").forward(req, resp);
        } catch (IllegalAccessException e) {
            resp.sendRedirect("/static/login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/errorPages/500.jsp");
        }
    }
}
