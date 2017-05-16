package com.gmail.kolesnyk.zakhar.mediaService;

import com.gmail.kolesnyk.zakhar.AbstractService;

import com.gmail.kolesnyk.zakhar.image.Image;
import com.gmail.kolesnyk.zakhar.image.ImageDao;
import com.gmail.kolesnyk.zakhar.user.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Service
public class MediaServiceImpl extends AbstractService implements MediaService {

    private final static int VIEW_MAX_SIZE_AVATAR = 150;

    public MediaServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Autowired
    private ImageDao imageDao;

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
        FileUtils.writeByteArrayToFile(serverFile, imageInByte);
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
    @Transactional(readOnly = true)
    public List<Image> imagesByIdUser(int idUser) {
        return imageDao.listByIdUser(idUser);
    }

    @Override
    public byte[] getMediaByName(String nameMedia) throws IOException {
        File serverFile = new File(PATH_STORING_MEDIA + nameMedia);
        InputStream is;
        if (serverFile.exists()) {
            is = new FileInputStream(serverFile);
        } else {
            throw new IllegalArgumentException("Image not found: " + serverFile.getAbsolutePath());
        }
        byte[] arr = IOUtils.toByteArray(is);
        is.close();
        return arr;
    }

    @Override
    public File getFileByName(String nameMedia) {
        return new File(PATH_STORING_MEDIA + nameMedia);
    }

    @Override
    @Transactional
    public void storeImage(MultipartFile file, User user) throws IOException {
        String fileName = storeMedia(file);
        Image image = new Image();
        image.setUser(user);
        image.setNameImage(fileName);
        imageDao.save(image);
    }

    @Override
    @Transactional
    public void deleteFileMedia(String nameMedia, int idMedia) throws IOException {
        Image image = new Image();
        image.setIdImage(idMedia);
        imageDao.remove(image);
        FileUtils.forceDelete(new File(PATH_STORING_MEDIA + nameMedia));
        Image imageToRemove = new Image();
        imageToRemove.setIdImage(idMedia);
    }
}
