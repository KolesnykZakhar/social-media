package com.gmail.kolesnyk.zakhar;

import com.gmail.kolesnyk.zakhar.post.PostDaoImpl;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao= new UserDaoImpl();
        User user=userDao.selectById(1);
//        user.setLastName("Trump");
//        userDao.update(user);

//        new UserDaoImpl().friendList(1).forEach(a-> System.out.println(a.getFirstName()));
//        new PostDaoImpl().listSearchString("st").forEach(a-> System.out.println(a.getComment()));
        new PostDaoImpl().listByUser(user).forEach(a-> System.out.println(a.getComment()));
    }
}
