package com.gmail.kolesnyk.zakhar.controller.access;


import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.postService.PostService;
import com.gmail.kolesnyk.zakhar.postService.postsPage.BlogPage;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user/blog_menu/{pageNumber}")
    public ModelAndView openBlogMenu(@PathVariable("pageNumber") Integer pageNumber) {
        return getBlogMenuModel(pageNumber);
    }

    @RequestMapping(value = "/user/add_new_post")
    public ModelAndView addNewPost(@RequestParam("newPostText") String newPostText) {
        createAndSavePost(newPostText);
        return getBlogMenuModel(1);
    }

//    @RequestMapping(value = "/user/blog_menu_full")
//    public ModelAndView openFullBlogMenu() {
//        return getBlogMenuModel(FULL);
//    }
//
//    @RequestMapping(value = "/user/add_new_post_full")
//    public ModelAndView addNewPostFullBlogMenu(@RequestParam("newPostText") String newPostText) {
//        createAndSavePost(newPostText);
//        return getBlogMenuModel(FULL);
//    }

    private void createAndSavePost(String textPost) {
        Post post = new Post();
        post.setUser(currentUser());
        post.setTextPost(textPost);
        postService.savePost(post);
    }

    private ModelAndView getBlogMenuModel(int pageNumber) {
        ModelAndView modelAndView = new ModelAndView("blog_menu");
        BlogPage blogPage = postService.sublistPostsByUser(currentUser(), pageNumber);
//        modelAndView.addObject("user", currentUser());
        modelAndView.addObject("blogPage", blogPage);
        return modelAndView;
    }

    private static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
