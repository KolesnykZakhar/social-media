package com.gmail.kolesnyk.zakhar;

import com.gmail.kolesnyk.zakhar.user.STATE;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;

public class Debug {
    public static void main(String[] args) {
//        STATE.values()[0];/
        System.out.println(STATE.BANNED.ordinal());
        User user=new User();
        user.setState(STATE.valueOf("BANNED"));

//        UserDao userDao = new UserDaoImpl();
//        userDao.list().forEach(a-> System.out.println(a.isEnabled()));
    }
}
