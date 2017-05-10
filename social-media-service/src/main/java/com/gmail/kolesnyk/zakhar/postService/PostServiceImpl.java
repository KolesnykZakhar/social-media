package com.gmail.kolesnyk.zakhar.postService;


import com.gmail.kolesnyk.zakhar.AbstractService;
import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.post.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl extends AbstractService implements PostService {
    @Autowired
    private PostDao postDao;

    public PostServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> fullListPostsByUser(int idUser) {
        return postDao.fullListByIdUser(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> shortListPostsByUser(int idUser) {
        return postDao.shortListByIdUser(idUser, SIZE_OF_SHORT_BLOG_MENU);
    }

    @Override
    @Transactional
    public void savePost(Post post) {
        postDao.save(post);
    }
}
