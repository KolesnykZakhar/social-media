package com.gmail.kolesnyk.zakhar.controller.authentication;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String toLoginPage(@AuthenticationPrincipal Object principal) {
        if (principal != null && principal instanceof User) {
            return "redirect: /index";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect: /login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String toRegistrationPage(@AuthenticationPrincipal Object principal) {
        if (principal != null && principal instanceof User) {
            return "redirect: /index";
        } else {
            return "registration";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    protected String registration(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("birthDate") String birthDate,
                                  @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("confirm") String confirm,
                                  @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("gender") Integer gender) throws ServletException, IOException {
        userService.registrationUser(firstName, lastName, birthDate, login, password, confirm, email, phone, gender);
        return "go_to_email_message";
    }

    @RequestMapping(value = "/confirm_email/{hash}", method = RequestMethod.GET)
    protected String confirmEmail(@PathVariable("hash") String hashedEmail) {
        try {
            userService.confirmEmail(hashedEmail);
            return "/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }

    @RequestMapping(value = "/discard_email/{hash}", method = RequestMethod.GET)
    protected String discardEmail(@PathVariable("hash") String hashedEmail) {
        try {
            userService.discardRegistration(hashedEmail);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }

    @RequestMapping(value = "/restore_password", method = RequestMethod.POST)
    protected String createRestorePassword(@RequestParam("email") String email) {
        try {
            userService.createRestorePassword(email);
            return "go_to_email_message";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }

    @RequestMapping(value = "/restore_password", method = RequestMethod.GET)
    protected String toRestorePasswordPage() {
        return "restore_password";
    }

    @RequestMapping(value = "/restore_password/{hash}", method = RequestMethod.GET)
    protected String restorePassword(@PathVariable("hash") String hash) {
        try {
            userService.removeRestorePassword(hash);
            return "new_password";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }

    @RequestMapping(value = "/discard_restoring_password/{hash}", method = RequestMethod.GET)
    protected String discardRestoringPassword(@PathVariable("hash") String hash) {
        try {
            userService.removeRestorePassword(hash);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }

    }

    @RequestMapping(value = "/new_password", method = RequestMethod.POST)
    protected String newPassword(@RequestParam("loginOrEmail") String loginOrEmail, @RequestParam("password") String password, @RequestParam("confirm") String confirm) {
        try {
            userService.createNewPassword(loginOrEmail, password, confirm);
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }
}
