package com.gmail.kolesnyk.zakhar.postService.postsPage;


import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

public class BlogPage {
    private List<Post> posts;
    private int amountPages;
    private User user;

    public BlogPage(List<Post> posts, int amountPages, User user) {
        this.posts = posts;
        this.amountPages = amountPages;
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public int getAmountPages() {
        return amountPages;
    }

    public User getUser() {
        return user;
    }
}
