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
import java.sql.Timestamp;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = userService.registrationUser(req.getParameter("firstName"), req.getParameter("lastName"), Timestamp.valueOf(req.getParameter("birthDate") + " 00:00:00"),
                    req.getParameter("login"), req.getParameter("password"), req.getParameter("confirm"), req.getParameter("email"), req.getParameter("phone"));
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
