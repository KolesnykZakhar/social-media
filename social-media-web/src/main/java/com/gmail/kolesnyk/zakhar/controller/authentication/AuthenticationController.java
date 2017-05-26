package com.gmail.kolesnyk.zakhar.controller.authentication;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/login", "/"})
    public String toLoginPage(@AuthenticationPrincipal Object principal) {
        if (principal != null && principal instanceof User) {
            return "forward:/index";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String toRegistrationPage(@AuthenticationPrincipal Object principal) {
        if (principal != null && principal instanceof User) {
            return "forward:/index";
        } else {
            return "registration";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    protected String registration(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("birthDate") String birthDate,
                                  @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("confirm") String confirm,
                                  @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("gender") Integer gender) throws ServletException, IOException {
        userService.registrationUser(firstName, lastName, birthDate, login, password, confirm, email, phone, gender);
        return "redirect:/go_to_email_message";
    }

    @RequestMapping(value = "/go_to_email_message", method = RequestMethod.GET)
    protected String goToEmail() {
        return "go_to_email_message";
    }

    @RequestMapping(value = "/confirm_email/{hash}", method = RequestMethod.GET)
    protected String confirmEmail(@PathVariable("hash") String hashedEmail) {
        try {
            userService.confirmEmail(hashedEmail);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }

    @RequestMapping(value = "/discard_email/{hash}", method = RequestMethod.GET)
    protected String discardEmail(@PathVariable("hash") String hashedEmail) {
        try {
            userService.discardRegistration(hashedEmail);
            return "redirect:/user/ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }

    @RequestMapping(value = "/restore_password", method = RequestMethod.POST)
    protected String createRestorePassword(@RequestParam("email") String email) {
        try {
            userService.createRestorePassword(email);
            return "redirect:/go_to_email_message";
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
            return "redirect:/user/ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }

    }

    @RequestMapping(value = "/new_password", method = RequestMethod.POST)
    protected String newPassword(@RequestParam("loginOrEmail") String loginOrEmail, @RequestParam("password") String password, @RequestParam("confirm") String confirm) {
        try {
            userService.createNewPassword(loginOrEmail, password, confirm);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPages/404";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkPass", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    protected String checkPassword(@RequestParam("pass") String pass, Locale locale) {
        final int[] strength = {0};
        Arrays.asList(".{6,16}", "[a-z]+", "[0-9]+", "[A-Z]+").forEach(s -> {
            if (Pattern.compile(s).matcher(pass).find()) {
                strength[0]++;
            }
        });
        String colorText;
        String codeText;
        switch (strength[0]) {
            case 2: {
                colorText = "red";
                codeText = "weakStrengthMessage";
                break;
            }
            case 3: {
                colorText = "green";
                codeText = "mediumStrengthMessage";
                break;
            }
            case 4: {
                colorText = "blue";
                codeText = "goodStrengthMessage";
                break;
            }
            default: {
                colorText = "grey";
                codeText = "invalidStrengthMessage";
                break;
            }
        }
        return "{" +
                "\"code\": \"" + messageSource.getMessage(codeText, null, locale) + "\"," +
                "\"color\": \"" + colorText + "\"" +
                "}";
    }
}
