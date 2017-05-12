package com.gmail.kolesnyk.zakhar.postService;


import com.gmail.kolesnyk.zakhar.AbstractService;
import com.gmail.kolesnyk.zakhar.post.MEDIA_TYPE;
import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.post.PostDao;
import com.gmail.kolesnyk.zakhar.postService.postPages.PostPage;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl extends AbstractService implements PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    public PostServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    @Transactional(readOnly = true)
    public PostPage sublistPostsByUser(int idUser, int pageNumber) {
        int amountPosts = postDao.amountPostsByIdUser(idUser);
        if (amountPosts == 0) {
            return new PostPage(Collections.emptyList(), 0, userDao.selectById(idUser));
        }
        int amountPages = amountPosts / AMOUNT_POSTS_ON_ONE_PAGE;
        int remainder = amountPosts % AMOUNT_POSTS_ON_ONE_PAGE;
        if (remainder != 0) {
            amountPages++;
        }
        if (pageNumber > amountPages || pageNumber < 0) {
            throw new IllegalArgumentException("wrong number of posts page");
        }
        List<Post> resultList = postDao.postsSublistByIdUser(idUser, pageNumber * AMOUNT_POSTS_ON_ONE_PAGE - AMOUNT_POSTS_ON_ONE_PAGE, AMOUNT_POSTS_ON_ONE_PAGE);
        return new PostPage(resultList, amountPages, userDao.selectById(idUser));
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
            if (!file.isEmpty()) {
                String fileName = storeMedia(file);
                imageNames.put(fileName, MEDIA_TYPE.getType(FilenameUtils.getExtension(fileName)));
            }
        }
        post.setMediaFiles(imageNames);
        savePost(post);
    }

    @Override
    @Transactional
    public void deletePostById(int idPost) {
        Post post = postDao.selectById(idPost);
        post.getMediaFiles().keySet().forEach(m -> {
            try {
                deleteMedia(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        postDao.remove(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostPage sublistBookmarksByUser(int idUser, int pageNumber) {
        int amountPosts = postDao.amountBookmarksByIdUser(idUser);
        if (amountPosts == 0) {
            return new PostPage(Collections.emptyList(), 0);
        }
        int amountPages = amountPosts / AMOUNT_POSTS_ON_ONE_PAGE;
        int remainder = amountPosts % AMOUNT_POSTS_ON_ONE_PAGE;
        if (remainder != 0) {
            amountPages++;
        }
        if (pageNumber > amountPages || pageNumber < 0) {
            throw new IllegalArgumentException("wrong number of bookmarks page");
        }
        List<Post> resultList = postDao.sublistBookmarksByUser(idUser, pageNumber * AMOUNT_POSTS_ON_ONE_PAGE - AMOUNT_POSTS_ON_ONE_PAGE, AMOUNT_POSTS_ON_ONE_PAGE);
        return new PostPage(resultList, amountPages);
    }

    @Override
    @Transactional(readOnly = true)
    public PostPage sublistNews(int idUser, int pageNumber) {
        int amountPosts = postDao.amountNewsByIdUser(idUser);
        if (amountPosts == 0) {
            return new PostPage(Collections.emptyList(), 0);
        }
        int amountPages = amountPosts / AMOUNT_POSTS_ON_ONE_PAGE;
        int remainder = amountPosts % AMOUNT_POSTS_ON_ONE_PAGE;
        if (remainder != 0) {
            amountPages++;
        }
        if (pageNumber > amountPages || pageNumber < 0) {
            throw new IllegalArgumentException("wrong number of bookmarks page");
        }
        List<Post> resultList = postDao.sublistNews(idUser, pageNumber * AMOUNT_POSTS_ON_ONE_PAGE - AMOUNT_POSTS_ON_ONE_PAGE, AMOUNT_POSTS_ON_ONE_PAGE);
        return new PostPage(resultList, amountPages);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasPrivateBlog(int idUser) {
        return userDao.hasPrivateBlog(idUser);
    }
}
