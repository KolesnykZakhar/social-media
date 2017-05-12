package com.gmail.kolesnyk.zakhar.postService.postsPage;


import com.gmail.kolesnyk.zakhar.post.MEDIA_TYPE;
import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;
import java.util.Set;

public class BlogPage {
    private List<Post> posts;
    private int amountPages;
    private User user;
    private Set<String> supportedFormats;

    public BlogPage(List<Post> posts, int amountPages, User user) {
        this.posts = posts;
        this.amountPages = amountPages;
        this.user = user;
        this.supportedFormats = MEDIA_TYPE.getAllExtends();
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

    public Set<String> getSupportedFormats() {
        return supportedFormats;
    }
}
