package com.gmail.kolesnyk.zakhar.controller.access;


import com.gmail.kolesnyk.zakhar.postService.PostService;
import com.gmail.kolesnyk.zakhar.postService.postPages.PostPage;
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

    @RequestMapping(value = "/user/blog_menu/{idUser}/{pageNumber}")
    public ModelAndView openBlogMenu(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("idUser") Integer idUser) {
        return getBlogMenuModel(pageNumber, idUser);
    }

    @RequestMapping(value = "/user/bookmarks/{pageNumber}")
    public ModelAndView openBookmarks(@PathVariable("pageNumber") Integer pageNumber) {
        ModelAndView modelAndView = new ModelAndView("bookmarks_menu");
        PostPage bookmarksPage = postService.sublistBookmarksByUser(currentUser().getIdUser(), pageNumber);
        modelAndView.addObject("bookmarksPage", bookmarksPage);
        return modelAndView;
    }

    @RequestMapping(value = "/user/news/{pageNumber}")
    public ModelAndView openNews(@PathVariable("pageNumber") Integer pageNumber) {
        ModelAndView modelAndView = new ModelAndView("news_menu");
        PostPage newsPage = postService.sublistNews(currentUser().getIdUser(), pageNumber);
        modelAndView.addObject("newsPage", newsPage);
        return modelAndView;
    }

    @RequestMapping(value = "/user/add_new_post", method = RequestMethod.POST)
    public ModelAndView addNewPost(@RequestParam("newPostText") String newPostText, @RequestParam("files") MultipartFile[] files) throws IOException {
        postService.createAndSavePost(currentUser(), newPostText, files);
        return getBlogMenuModel(1, currentUser().getIdUser());
    }

    @RequestMapping(value = "/user/delete_post/{idPost}", method = RequestMethod.POST)
    public ModelAndView deletePost(@PathVariable("idPost") Integer idPost) throws IOException {
        postService.deletePostById(idPost);
        return getBlogMenuModel(1, currentUser().getIdUser());
    }

    @RequestMapping(value = "/user/remove_bookmark/{idPost}", method = RequestMethod.POST)
    public void removeBookmark(@PathVariable("idPost") Integer idPost) throws IOException {
        postService.removeBookmark(currentUser().getIdUser(), idPost);
    }

    @RequestMapping(value = "/user/add_bookmark/{idPost}", method = RequestMethod.POST)
    public void addBookmark(@PathVariable("idPost") Integer idPost) throws IOException {
        postService.addBookmark(currentUser().getIdUser(), idPost);
    }

    private ModelAndView getBlogMenuModel(int pageNumber, int idUser) {
        ModelAndView modelAndView;
        if (currentUser().getIdUser().equals(idUser) || !postService.hasPrivateBlog(idUser)) {
            modelAndView = new ModelAndView("blog_menu");
            PostPage blogPage;
            blogPage = postService.sublistPostsByUser(idUser, pageNumber, currentUser().getIdUser());
            modelAndView.addObject("blogPage", blogPage);
            modelAndView.addObject("canModify", currentUser().getIdUser().intValue() == blogPage.getUser().getIdUser());
        } else {
            modelAndView = new ModelAndView("access_denied_to_blog");
        }
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
