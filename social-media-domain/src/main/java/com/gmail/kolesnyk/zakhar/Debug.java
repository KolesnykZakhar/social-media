package com.gmail.kolesnyk.zakhar;

import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Debug {
    public static void main(String[] args) {
        System.out.println(Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime().toLocalDate());
//UserDaoImpl userDao=new UserDaoImpl();
//        userDao.friendListByRange(1, 2,2).forEach(a-> System.out.println(a.getIdUser()));

//        Configuration configuration=new Configuration();
//        System.out.println(configuration.getResourceBundle().getString("first"));
//        configuration.getProperties().keySet().forEach(a ->
//                System.out.println(a+" = "+configuration.getProperties().get(a)));

//        configuration.getProperties().setProperty("html", "<html><p>safdkjasbfvhCFHASF </p></html>");

//        configuration.saveProperty();




//        configuration.getProperties().keySet().forEach(a ->
//                System.out.println(a+" = "+configuration.getProperties().get(a)));
//        UserDao userDao= new UserDaoImpl();
//        User user=userDao.selectById(1);
//        user.setLastName("Trump");
//        userDao.update(user);

//        new UserDaoImpl().friendList(1).forEach(a-> System.out.println(a.getFirstName()));
//        new PostDaoImpl().listSearchString("st").forEach(a-> System.out.println(a.getComment()));
//        new PostDaoImpl().listByUser(user).forEach(a-> System.out.println(a.getComment()));
    }
}
