package com.gmail.kolesnyk.zakhar.controller.authorization.servlet;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login"})
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
