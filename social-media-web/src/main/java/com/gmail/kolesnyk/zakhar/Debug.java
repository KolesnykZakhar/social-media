package com.gmail.kolesnyk.zakhar;


import com.gmail.kolesnyk.zakhar.userService.UserServiceImpl;
import com.gmail.kolesnyk.zakhar.user.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Debug {
    public static void main(String[] args) {
        System.out.println(LocalDate.parse("2013-12-31"));
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
}
