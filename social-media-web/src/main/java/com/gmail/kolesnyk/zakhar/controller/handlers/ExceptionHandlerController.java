package com.gmail.kolesnyk.zakhar.controller.handlers;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public String processError(Exception e) {
        return "errorPages/unexpected";
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public String processError(ConstraintViolationException e) {
//
//        e.printStackTrace();
//        System.out.println(e.getLocalizedMessage());
//        if (e.toString().contains("Duplicate entry") && e.toString().contains("addToFriends")){
//            return "friendship_already_invited";
//        }
//        return "errorPages/unexpected";
//    }
}
