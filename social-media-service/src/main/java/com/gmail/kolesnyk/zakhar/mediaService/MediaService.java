package com.gmail.kolesnyk.zakhar.mediaService;

import com.gmail.kolesnyk.zakhar.image.Image;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {
    void storeAvatar(MultipartFile file, int idUser) throws IOException;

    byte[] getAvatarByUser(Integer idUser) throws IOException;

    List<Image> imagesByIdUser(int idUser);

    byte[] getImageByName(String nameImage) throws IOException;

    void storeImage(MultipartFile file, User user) throws IOException;
}
