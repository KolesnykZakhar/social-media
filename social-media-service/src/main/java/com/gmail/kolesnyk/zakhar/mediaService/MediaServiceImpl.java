package com.gmail.kolesnyk.zakhar.mediaService;

import com.gmail.kolesnyk.zakhar.AbstractService;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.Set;

@Service
public class MediaServiceImpl extends AbstractService implements MediaService {

    private final static int VIEW_MAX_SIZE_AVATAR = 150;

    public MediaServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    public void storeAvatar(MultipartFile file, int idUser) throws IOException {
        InputStream is = file.getInputStream();
        BufferedImage after = Scalr.resize(ImageIO.read(is), Scalr.Method.BALANCED, VIEW_MAX_SIZE_AVATAR, VIEW_MAX_SIZE_AVATAR);
        is.close();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(after, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        File dir = new File(ROOT_AVATAR_URL);
        if (!dir.exists()) {
            throw new IllegalArgumentException("dir not exist -> " + ROOT_AVATAR_URL);
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + idUser + AVATAR_EXTENDS);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(imageInByte);
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
    public Set<String> getListPhotoPath(int idUser) {
        Set<String> list = new HashSet<>();
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
        File userPath = new File(dir.getAbsolutePath() + File.separator + idUser + File.separator);
        if (!userPath.exists()) {
            userPath.mkdirs();
        }
        File serverFile;
        for (int i = 0; i < AMOUNT_PHOTOS_ON_ONE_USER; i++) {
            serverFile = new File(userPath.getAbsolutePath() + i + PHOTO_EXTENDS);
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
