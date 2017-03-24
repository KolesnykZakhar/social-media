package com.gmail.kolesnyk.zakhar.controller.authentication;

import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login"})
    public String toLoginPage() {
        return "../static/login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect: /static/login.jsp";
    }

    @RequestMapping(value = {"/registration"})
    protected String registration(HttpServletRequest req) throws ServletException, IOException {
        try {
            userService.registrationUser(req.getParameter("firstName"), req.getParameter("lastName"), Timestamp.valueOf(req.getParameter("birthDate") + " 00:00:00"),
                    req.getParameter("login"), req.getParameter("password"), req.getParameter("confirm"), req.getParameter("email"), req.getParameter("phone"));
            return "redirect: /static/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect: /errorPages/500.jsp";
        }
    }
}
