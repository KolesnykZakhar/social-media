package com.gmail.kolesnyk.zakhar.controller.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public String processError(Exception e) {
        return "errorPages/unexpected";
    }
}
