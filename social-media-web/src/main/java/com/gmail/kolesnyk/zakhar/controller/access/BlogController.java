package com.gmail.kolesnyk.zakhar.controller.access;


import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.postService.PostService;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.gmail.kolesnyk.zakhar.controller.access.BlogController.TYPE_BLOG_MENU.FULL;
import static com.gmail.kolesnyk.zakhar.controller.access.BlogController.TYPE_BLOG_MENU.SHORT;

@Controller
public class BlogController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user/blog_menu_short")
    public ModelAndView openShortBlogMenu() {
        return getBlogMenuModel(SHORT);
    }

    @RequestMapping(value = "/user/add_new_post_short")
    public ModelAndView addNewPostShortBlogMenu(@RequestParam("newPostText") String newPostText) {
        createAndSavePost(newPostText);
        return getBlogMenuModel(SHORT);
    }
    @RequestMapping(value = "/user/blog_menu_full")
    public ModelAndView openFullBlogMenu() {
        return getBlogMenuModel(FULL);
    }

    @RequestMapping(value = "/user/add_new_post_full")
    public ModelAndView addNewPostFullBlogMenu(@RequestParam("newPostText") String newPostText) {
        createAndSavePost(newPostText);
        return getBlogMenuModel(FULL);
    }

    private void createAndSavePost(String textPost){
        Post post = new Post();
        post.setUser(currentUser());
        post.setTextPost(textPost);
        postService.savePost(post);
    }

    private static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private ModelAndView getBlogMenuModel(TYPE_BLOG_MENU typeBlogMenu) {
        ModelAndView modelAndView;
        List<Post> posts;
        switch (typeBlogMenu) {
            case SHORT: {
                modelAndView = new ModelAndView("blog_menu_short");
                posts = postService.shortListPostsByUser(currentUser().getIdUser());
                break;
            }
            case FULL: {
                modelAndView = new ModelAndView("blog_menu_full");
                posts = postService.fullListPostsByUser(currentUser().getIdUser());
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown type blog menu");
            }
        }
        modelAndView.addObject("user", currentUser());
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    enum TYPE_BLOG_MENU {
        SHORT, FULL
    }
}
