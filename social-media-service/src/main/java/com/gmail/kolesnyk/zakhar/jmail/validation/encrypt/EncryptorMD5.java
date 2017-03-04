package com.gmail.kolesnyk.zakhar.jmail.validation.encrypt;


import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptorMD5 implements Encryptor {
    private MessageDigest md5;

    public EncryptorMD5() {
        try {
            md5 = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encryptPassword(String password) {
        if (password == null || password.equals("") || password.length() > 20) {
            throw new IllegalArgumentException("wrong password");
        }
        return (new HexBinaryAdapter()).marshal(md5.digest(password.getBytes()));
    }
}
