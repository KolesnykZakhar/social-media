package com.gmail.kolesnyk.zakhar.authorization.servlet;

import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.userService.UserServiceImpl;
import com.gmail.kolesnyk.zakhar.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = userService.getUserByLoginOrEmailAndPassword(req.getParameter("loginOrEmail"), req.getParameter("password"));
            resp.addCookie(new Cookie("auth", req.getSession().getId()));
        } catch (IllegalAccessException e) {
            resp.sendRedirect("/login.jsp");
        }
        req.setAttribute("user", user);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/index").forward(req,resp);
    }
}
