package com.gmail.kolesnyk.zakhar.postService;

import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.postService.postsPage.BlogPage;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    BlogPage sublistPostsByUser(User user, int pageNumber);

    void savePost(Post post);

    @Transactional
    void createAndSavePost(User user, String textPost, MultipartFile... files) throws IOException;
}
