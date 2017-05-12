package com.gmail.kolesnyk.zakhar.controller.access;

import com.gmail.kolesnyk.zakhar.image.Image;
import com.gmail.kolesnyk.zakhar.mediaService.MediaService;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.util.MultipartFileSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

@Controller
public class MediaController {

    @Autowired
    private MediaService mediaService;

    private static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = "/user/image_slider")
    public ModelAndView getPhotoSlider() {
        ModelAndView modelAndView;
        List<Image> images = mediaService.imagesByIdUser(currentUser().getIdUser());
        modelAndView = new ModelAndView("image_slider");
        modelAndView.addObject("images", images);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/user/media/{nameMedia}")
    public void getMedia(@PathVariable("nameMedia") String nameMedia, HttpServletRequest req, HttpServletResponse res) throws Exception {
        MultipartFileSender.fromFile(mediaService.getFileByName(nameMedia))
                .with(req)
                .with(res)
                .serveResource();
    }

    @Bean
    public MultipartFileSender multipartFileSender() {
        return new MultipartFileSender();
    }

    @ResponseBody
    @RequestMapping(value = "/user/avatar/{idUser}")
    public byte[] getAvatar(@PathVariable Integer idUser) {
        try {
            return mediaService.getAvatarByUser(idUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/user/upload_avatar_by_file", method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("uploadedAvatar") MultipartFile file) {
        try {
            mediaService.storeAvatar(file, currentUser().getIdUser());
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/500";
        }
    }

    @RequestMapping(value = "/user/upload_image_by_file", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("uploadedPhoto") MultipartFile file) {
        try {
            mediaService.storeImage(file, currentUser());
            return "ok";
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
            return "errorPages/403_limit_photos";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/500";
        }
    }

    @RequestMapping(value = "/user/delete_media/{nameMedia}/{idMedia}")
    public String deleteMedia(@PathVariable("nameMedia") String nameMedia, @PathVariable("idMedia") Integer idMedia) throws IOException {
        mediaService.deleteFileMedia(nameMedia, idMedia);
        return "ok";
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
}
