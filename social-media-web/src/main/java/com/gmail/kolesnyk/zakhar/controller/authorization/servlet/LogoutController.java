package com.gmail.kolesnyk.zakhar.controller.authorization.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {
    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse resp) {
        resp.addCookie(new Cookie("auth", null));
        return "redirect: /static/login.jsp";
    }
}
