package com.gmail.kolesnyk.zakhar.controller.access.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class AdminController {
    @RequestMapping(value = {"/admin/index"})
    public String goToIndex() throws ServletException, IOException {
        return "admin/index";
    }
}
