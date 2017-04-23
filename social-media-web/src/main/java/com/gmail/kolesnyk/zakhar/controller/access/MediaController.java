package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.mediaService.MediaService;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;

@Controller
public class MediaController {

    @Autowired
    private UserService userService;

    @Autowired
    private MediaService mediaService;

    @RequestMapping(value = "/user/photo_slider")
    public String getPhotoSlider() {
        return "photo_slider";
    }

    @ResponseBody
    @RequestMapping(value = "/user/avatar/{idUser}")
    public byte[] getAvatar(@PathVariable Integer idUser) {
        try {
            return mediaService.getAvatarUrlByUser(idUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/user/upload_avatar_by_file", method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("uploadedAvatar") MultipartFile file) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            mediaService.storeAvatar(file, user.getIdUser());
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/500";
        }
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
}
