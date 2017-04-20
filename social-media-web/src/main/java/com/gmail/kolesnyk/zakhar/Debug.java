package com.gmail.kolesnyk.zakhar;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Arrays;

public class Debug {
    public static void main(String[] args) {
        new Debug().is();
//        System.out.println(LocalDate.parse("2013-12-31"));
//        System.out.println(LocalDateTime.MIN);
//        System.out.println(LocalDateTime.MAX);
//        Timestamp.valueOf(LocalDateTime.MAX);
//        Encryptor encryptor=new EncryptorMD5();
//        System.out.println(encryptor.encryptPassword("1111"));
//        UserDao userDao=new UserDaoImpl();
//        User user = userDao.selectByLogin("john");
//        System.out.println(user.getFirstName());
//        try {
//            User user = new UserServiceImpl().getUserByLoginOrEmailAndPassword("john", "1111");
//            System.out.println(user.getFirstName());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
    private void is(){
        try {
            byte[] a = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("media/avatars/1.png"));
            System.out.println(Arrays.toString(a));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        File image = new File(getClass().getClassLoader().getResource("/media/avatars/1.png").getPath());

    }
}
