package com.gmail.kolesnyk.zakhar.mediaService;

import com.gmail.kolesnyk.zakhar.AbstractService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
        File serverFile = new File(dir.getAbsolutePath() + File.separator + idUser + ".png");
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
    }

    @Override
    public byte[] getAvatarUrlByUser(Integer idUser) throws IOException {
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
}
