package com.gmail.kolesnyk.zakhar;

import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;

public class Debug {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        userDao.list().forEach(a-> System.out.println(a.isEnabled()));
    }
}
