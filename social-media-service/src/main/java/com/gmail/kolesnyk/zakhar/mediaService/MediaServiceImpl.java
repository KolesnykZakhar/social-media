package com.gmail.kolesnyk.zakhar.mediaService;

import com.gmail.kolesnyk.zakhar.AbstractService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl extends AbstractService implements MediaService {

    public MediaServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    public void storeAvatar(MultipartFile file, int idUser) throws IOException {
        byte[] bytes = file.getBytes();
        File dir = new File(ROOT_AVATAR_URL);
        if (!dir.exists()) {
            throw new IllegalArgumentException("dir not exist -> " + ROOT_AVATAR_URL);
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + idUser + AVATAR_EXTENDS);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
    }

    @Override
    public byte[] getAvatarByUser(Integer idUser) throws IOException {
        File serverFile = new File(ROOT_AVATAR_URL + idUser + AVATAR_EXTENDS);
        InputStream is;
        if (serverFile.exists()) {
            is = new FileInputStream(serverFile);
        } else {
            is = new FileInputStream(DEFAULT_AVATAR_URL);
        }
        byte[] arr = IOUtils.toByteArray(is);
        is.close();
        return arr;
    }

    @Override
    public List<String> getListPhotoPath(int idUser) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < AMOUNT_PHOTOS_ON_ONE_USER; i++) {
            File serverFile = new File(ROOT_PHOTO_URL + File.separator + idUser + File.separator + i + PHOTO_EXTENDS);
            if (serverFile.exists()) {
                list.add("/user/photos/" + idUser + "/" + i);
            }
        }
        return list;
    }

    @Override
    public byte[] getPhotoByIdUserAndIdPhoto(Integer idUser, Integer idPhoto) throws IOException {
        File serverFile = new File(ROOT_PHOTO_URL + File.separator + idUser + File.separator + idPhoto + PHOTO_EXTENDS);
        InputStream is;
        if (serverFile.exists()) {
            is = new FileInputStream(serverFile);
        } else {
            throw new IllegalArgumentException("photo by ID user: " + idUser + " and ID photo: " + idPhoto + "not found");
        }
        byte[] arr = IOUtils.toByteArray(is);
        is.close();
        return arr;
    }

    @Override
    public void storePhoto(MultipartFile file, Integer idUser) throws IOException {
        byte[] bytes = file.getBytes();
        File dir = new File(ROOT_PHOTO_URL);
        if (!dir.exists()) {
            throw new IllegalArgumentException("dir not exist -> " + ROOT_PHOTO_URL);
        }
        File serverFile;
        for (int i = 0; i < AMOUNT_PHOTOS_ON_ONE_USER; i++) {
            serverFile = new File(dir.getAbsolutePath() + File.separator + idUser + File.separator + i + PHOTO_EXTENDS);
            if (!serverFile.exists()) {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return;
            }
        }
        throw new FileAlreadyExistsException("Exceeded limit of Users Photo");
    }
}
