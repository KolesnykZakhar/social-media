package com.gmail.kolesnyk.zakhar.postService;

import com.gmail.kolesnyk.zakhar.post.Post;
import com.gmail.kolesnyk.zakhar.postService.postPages.PostPage;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostService {
    PostPage sublistPostsByUser(int idUser, int pageNumber, int idCurrentUser);

    void savePost(Post post);

    void createAndSavePost(User user, String textPost, MultipartFile... files) throws IOException;

    void deletePostById(int idPost);

    PostPage sublistBookmarksByUser(int idUser, int pageNumber);

    boolean hasPrivateBlog(int idUser);

    PostPage sublistNews(int idUser, int pageNumber);

    void addBookmark(int idUser, int idBookmark);

    void removeBookmark(int idUser, int idBookmark);
}
