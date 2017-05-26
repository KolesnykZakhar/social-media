package com.gmail.kolesnyk.zakhar.controller.handlers;

import com.gmail.kolesnyk.zakhar.validation.RegistrationValidateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String processError(Exception e) {
        return "errorPages/unexpected";
    }

    @ExceptionHandler(RegistrationValidateException.class)
    @ResponseStatus(BAD_REQUEST)
    public ModelAndView registrationError(RegistrationValidateException e) {
        ModelAndView modelAndView=new ModelAndView("errorPages/registrationError");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
