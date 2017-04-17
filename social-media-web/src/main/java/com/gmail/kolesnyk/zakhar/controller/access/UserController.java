package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class UserController {

    @RequestMapping(value = {"/index"})
    public ModelAndView goToIndex() throws ServletException, IOException {
        ModelAndView modelAndView;
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView = new ModelAndView("index");
        modelAndView.addObject("isAdmin", userDetails.getAuthority().contains("ROLE_ADMIN"));
        return modelAndView;
    }
}
