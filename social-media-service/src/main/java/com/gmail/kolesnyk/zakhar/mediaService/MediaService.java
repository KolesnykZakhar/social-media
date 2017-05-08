package com.gmail.kolesnyk.zakhar.mediaService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface MediaService {
    void storeAvatar(MultipartFile file, int idUser) throws IOException;

    byte[] getAvatarByUser(Integer idUser) throws IOException;

    Set<String> getListPhotoPath(int idUser);

    byte[] getPhotoByIdUserAndIdPhoto(Integer idUser, Integer idPhoto) throws IOException;

    void storePhoto(MultipartFile file, Integer idUser) throws IOException;
}