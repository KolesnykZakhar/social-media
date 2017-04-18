package com.gmail.kolesnyk.zakhar.controller.authentication;

import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping(value = {"/index_role"})
    public String goToIndex() throws ServletException, IOException {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getAuthority().contains("ROLE_ADMIN")) {
            return "admin/index";
        } else {
            return "index";
        }
    }
}
