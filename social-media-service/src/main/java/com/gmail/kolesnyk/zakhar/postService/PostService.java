package com.gmail.kolesnyk.zakhar.postService;

import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.postService.postsPage.BlogPage;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {
    BlogPage sublistPostsByUser(User user, int pageNumber);

    void savePost(Post post);
}
