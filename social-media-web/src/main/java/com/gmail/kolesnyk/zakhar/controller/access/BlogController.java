package com.gmail.kolesnyk.zakhar.controller.access;


import com.gmail.kolesnyk.zakhar.postService.PostService;
import com.gmail.kolesnyk.zakhar.postService.postsPage.BlogPage;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BlogController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user/blog_menu/{pageNumber}")
    public ModelAndView openBlogMenu(@PathVariable("pageNumber") Integer pageNumber) {
        return getBlogMenuModel(pageNumber);
    }

    @RequestMapping(value = "/user/add_new_post", method = RequestMethod.POST)
    public ModelAndView addNewPost(@RequestParam("newPostText") String newPostText, @RequestParam("files") MultipartFile[] files) throws IOException {
        postService.createAndSavePost(currentUser(), newPostText, files);
        return getBlogMenuModel(1);
    }

    private ModelAndView getBlogMenuModel(int pageNumber) {
        ModelAndView modelAndView = new ModelAndView("blog_menu");
        BlogPage blogPage = postService.sublistPostsByUser(currentUser(), pageNumber);
        modelAndView.addObject("blogPage", blogPage);
        return modelAndView;
    }

    private static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
}
