package com.gmail.kolesnyk.zakhar.authorization.servlet;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import com.gmail.kolesnyk.zakhar.userService.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userService.getUserByLoginOrEmailAndPassword(req.getParameter("loginOrEmail"), req.getParameter("password"));
            resp.addCookie(new Cookie("auth", req.getSession().getId()));
            req.getSession().setAttribute("user", user);
            resp.setContentType("text/html");
            req.getRequestDispatcher("/index").forward(req, resp);
        } catch (IllegalAccessException e) {
            resp.sendRedirect("/login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/errorPages/500.jsp");
        }
    }
}
