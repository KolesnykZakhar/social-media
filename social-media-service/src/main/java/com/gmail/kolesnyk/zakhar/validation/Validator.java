package com.gmail.kolesnyk.zakhar.validation;


import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern FIRST_NAME_USER_PATTERN = Pattern.compile("[a-zA-Z]{2,32}");
    private static final Pattern LAST_NAME_USER_PATTERN = Pattern.compile("[a-zA-Z]{2,32}");
    private static final Pattern PHONE_USER_PATTERN = Pattern.compile("(\\+|^)[0-9]{13}");
    private static final Pattern LOGIN_USER_PATTERN = Pattern.compile("[a-zA-Z0-9]{6,32}");
    private static final Pattern PASSWORD_USER_PATTERN = Pattern.compile("[a-zA-Z0-9]{6,16}");
    private static final Pattern EMAIL_USER_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+");
    private final static int MAX_OLD_USER = 150;
    private final static int MIN_YOUNG_USER = 5;


    public static void validateUser(User user, String pass) {
        if (user == null) {
            throw new IllegalArgumentException("User equals null");
        }
        if (user.getFirstName() == null || !FIRST_NAME_USER_PATTERN.matcher(user.getFirstName()).matches()) {
            throw new RegistrationValidateException("First name should be contains only literals and be in range 2-32");
        }
        if (user.getLastName() == null || !LAST_NAME_USER_PATTERN.matcher(user.getLastName()).matches()) {
            throw new RegistrationValidateException("Last name should be contains: only literals and be in range 2-32");
        }
        if (user.getPhone() == null || !PHONE_USER_PATTERN.matcher(user.getPhone()).matches()) {
            throw new RegistrationValidateException("Phone can start from '+' and should contains 13 digits");
        }
        if (user.getLogin() == null || !LOGIN_USER_PATTERN.matcher(user.getLogin()).matches()) {
            throw new RegistrationValidateException("Login should be contains literals or digits and be in range 6-32");
        }
        if (pass == null || !PASSWORD_USER_PATTERN.matcher(pass).matches()) {
            throw new RegistrationValidateException("Password should be contains literals or digits and be in range 6-16");
        }
        if (user.getEmail() == null || !EMAIL_USER_PATTERN.matcher(user.getEmail()).matches()) {
            throw new RegistrationValidateException("Email should be correct");
        }
        if (user.getBirthDate() == null || !isCorrectBirthDate(user.getBirthDate())) {
            LocalDate maxOldDate = LocalDate.now().minusYears(MAX_OLD_USER);
            LocalDate maxYoungDate = LocalDate.now().minusYears(MIN_YOUNG_USER);
            throw new RegistrationValidateException("Birth date should be after " + maxOldDate + ", and before " + maxYoungDate);
        }
        if (user.getGender() == null || Arrays.stream(GENDER.values()).noneMatch(gender -> gender == user.getGender())) {
            throw new RegistrationValidateException("Birth date should be ");
        }

    }

    private static boolean isCorrectBirthDate(String birthDate) {
        LocalDate date;
        try {
            date = LocalDate.parse(birthDate);
        } catch (Exception e) {
            return false;
        }
        return !(LocalDate.now().minusYears(MAX_OLD_USER).compareTo(date) > 0 || LocalDate.now().minusYears(MIN_YOUNG_USER).compareTo(date) < 0);
    }
}
