package com.gmail.kolesnyk.zakhar.validation;


import com.gmail.kolesnyk.zakhar.user.User;

import java.util.regex.Pattern;

public class Validator {
    private static Pattern nameUserPattern = Pattern.compile("[a-zA-Z]{2,32}");

    static void validateUserInfo(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User equals null");
        }
        if (user.getFirstName() == null || nameUserPattern.matcher(user.getFirstName()).matches()) {
        }
    }
}
