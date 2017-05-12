package com.gmail.kolesnyk.zakhar.postService;


import com.gmail.kolesnyk.zakhar.AbstractService;
import com.gmail.kolesnyk.zakhar.post.MEDIA_TYPE;
import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.post.PostDao;
import com.gmail.kolesnyk.zakhar.postService.postsPage.BlogPage;
import com.gmail.kolesnyk.zakhar.user.User;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl extends AbstractService implements PostService {
    @Autowired
    private PostDao postDao;

    public PostServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    @Transactional(readOnly = true)
    public BlogPage sublistPostsByUser(User user, int pageNumber) {
        int amountPosts = postDao.amountPostsByIdUser(user.getIdUser());
        if (amountPosts == 0) {
            throw new ArrayStoreException("post list empty");
        }
        int amountPages = amountPosts / AMOUNT_POSTS_ON_ONE_PAGE;
        int remainder = amountPosts % AMOUNT_POSTS_ON_ONE_PAGE;
        if (remainder != 0) {
            amountPages++;
        }
        if (pageNumber > amountPages || pageNumber < 0) {
            throw new IllegalArgumentException("wrong number of friends page");
        }
        List<Post> resultList = postDao.postsSublistByIdUser(user.getIdUser(), pageNumber * AMOUNT_POSTS_ON_ONE_PAGE - AMOUNT_POSTS_ON_ONE_PAGE, AMOUNT_POSTS_ON_ONE_PAGE);
        return new BlogPage(resultList, amountPages, user);
    }

    @Override
    @Transactional
    public void savePost(Post post) {
        postDao.save(post);
    }

    @Override
    public void createAndSavePost(User user, String textPost, MultipartFile... files) throws IOException {
        Post post = new Post();
        post.setUser(user);
        post.setTextPost(textPost);
        Map<String, MEDIA_TYPE> imageNames = new HashMap<>();
        for (MultipartFile file : files) {
            String fileName = storeMedia(file);
            imageNames.put(fileName, MEDIA_TYPE.getType(FilenameUtils.getExtension(fileName)));
        }
        post.setMediaFiles(imageNames);
        savePost(post);
    }
}
