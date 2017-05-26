package com.gmail.kolesnyk.zakhar.controller.authentication;

import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping(value = {"/index"})
    public String goToIndex(@AuthenticationPrincipal Object principal) throws ServletException, IOException {
        User user = (User) principal;
        if (user.getAuthority().contains("ROLE_ADMIN")) {
            return "forward:/admin/index";
        } else {
            return "forward:/user/index";
        }
    }
}
