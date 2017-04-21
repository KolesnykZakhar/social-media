package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.*;

@Controller
public class MediaController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/avatar/{idUser}")
    @ResponseBody
    public byte[] getAvatar(@PathVariable Integer idUser) {
        try {
            InputStream is = userService.getAvatarUrlByUser(idUser);
            byte[] arr = IOUtils.toByteArray(userService.getAvatarUrlByUser(idUser));
            is.close();
            return arr;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/upload_avatar_by_file", method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("uploadedAvatar") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                String rootPath = "D:/social-media/media/avatars/";
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                File dir = new File(rootPath);
                if (!dir.exists()) {
                    return "dir not exist -> " + rootPath;
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + user.getIdUser() + ".png");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return "ok";
            } catch (Exception e) {
                e.printStackTrace();
                return "errorPages/500";
            }
        } else {
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
