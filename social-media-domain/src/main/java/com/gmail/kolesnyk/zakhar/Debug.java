package com.gmail.kolesnyk.zakhar;

import com.gmail.kolesnyk.zakhar.user.STATE;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.user.UserDaoImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Debug {
    public static void main(String[] args) {

        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());



        System.out.println(timestamp.toLocalDateTime().getHour());
    }
}
