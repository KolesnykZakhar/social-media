//package com.gmail.kolesnyk.zakhar.controller.authorization.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/index/*"})
//public class LoginFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = ((HttpServletRequest) servletRequest);
//        Cookie[] cookies = req.getCookies();
//        for (Cookie c : cookies) {
//            HttpSession session = req.getSession();
//            if (c != null && c.getName().equals("auth") && c.getValue().equals(session.getId())) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
