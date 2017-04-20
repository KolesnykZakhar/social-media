package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class MediaController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/avatar/{idUser}")
    @ResponseBody
    public byte[] getAvatar(@PathVariable Integer idUser) {
        try {
            String imageUrl = userService.getImageUrlByUser(idUser);
            return IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream(imageUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
