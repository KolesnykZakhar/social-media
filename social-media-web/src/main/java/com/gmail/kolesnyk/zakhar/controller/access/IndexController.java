package com.gmail.kolesnyk.zakhar.controller.access;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping(value = {"/index"})
    public String goToIndex() throws ServletException, IOException {
        return "index";
    }
}
