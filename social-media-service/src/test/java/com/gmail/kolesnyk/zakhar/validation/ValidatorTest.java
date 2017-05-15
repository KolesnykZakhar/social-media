package com.gmail.kolesnyk.zakhar.validation;

import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ValidatorTest {

    private User initCorrectUser() {
        User user = new User();
        user.setFirstName("Jim");
        user.setLastName("Kim");
        user.setBirthDate("1988-12-24");
        user.setEmail("asad@mail.co");
        user.setLogin("jiMi123");
        user.setPhone("+380501235623");
        user.setGender(GENDER.MALE);
        return user;
    }

    private String initCorrectPassword() {
        return "dddfff111";
    }

    @Test
    public void validateUserFirstName1() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("as"); //min length lastName (2 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserFirstName2() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsj"); //max length first name (32 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserFirstName3() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asdsdzxcx"); //random first name
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName4() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("a"); //when first name less than 2 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName5() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsjd"); //when first name more than 32 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName6() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asds4dzx"); //when first name contains digits
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName7() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asds)dzx"); //when first name contains special symbols
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName8() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asds dzx"); //when first name contains space
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test
    public void validateUserLastName1() throws Exception {
        User user = initCorrectUser();
        user.setLastName("as"); //min length last name (2 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserLastName2() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsj"); //max length last name (32 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserLastName3() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asdsdzxcx"); //random last name
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName4() throws Exception {
        User user = initCorrectUser();
        user.setLastName("a"); //when last name less than 2 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName5() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsjd"); //when last name more than 32 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName6() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asds4dzx"); //when last name contains digits
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName7() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asds)dzx"); //when last name contains special symbols
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName8() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asds dzx"); //when last name contains space
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }
}