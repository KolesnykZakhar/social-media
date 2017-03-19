package com.gmail.kolesnyk.zakhar.controller.authorization.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
//@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet/* extends HttpServlet */ {
    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addCookie(new Cookie("auth", null));
//        resp.sendRedirect("/static/login.jsp");
//    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse resp) {
        resp.addCookie(new Cookie("auth", null));
        return "redirect: /static/login.jsp";
    }
}
