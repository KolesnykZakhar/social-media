package com.gmail.kolesnyk.zakhar.postService;

import com.gmail.kolesnyk.zakhar.post.Post;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {
    List<Post> fullListPostsByUser(int idUser);

    @Transactional(readOnly = true)
    List<Post> shortListPostsByUser(int idUser);

    void savePost(Post post);
}
