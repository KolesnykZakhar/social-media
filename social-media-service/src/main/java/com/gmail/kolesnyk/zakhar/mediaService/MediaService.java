package com.gmail.kolesnyk.zakhar.mediaService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {
    void storeAvatar(MultipartFile file, int idUser) throws IOException;

    byte[] getAvatarUrlByUser(Integer idUser) throws IOException;
}
