package com.gmail.kolesnyk.zakhar.controller.handlers;

import com.gmail.kolesnyk.zakhar.validation.RegistrationValidateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public String processError(Exception e) {
        return "errorPages/unexpected";
    }

    @ExceptionHandler(RegistrationValidateException.class)
    public ModelAndView registrationError(RegistrationValidateException e) {
        ModelAndView modelAndView=new ModelAndView("errorPages/registrationError");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
