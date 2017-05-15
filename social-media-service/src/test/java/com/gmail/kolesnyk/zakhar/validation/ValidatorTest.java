package com.gmail.kolesnyk.zakhar.validation;

import com.gmail.kolesnyk.zakhar.user.GENDER;
import com.gmail.kolesnyk.zakhar.user.User;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class ValidatorTest {

    private User initCorrectUser() {
        User user = new User();
        user.setFirstName("Jim");
        user.setLastName("Kim");
        user.setBirthDate("1988-12-24");
        user.setEmail("asad@mail.co");
        user.setLogin("jiMi123");
        user.setPhone("+1234567890123");
        user.setGender(GENDER.MALE);
        return user;
    }

    private String initCorrectPassword() {
        return "dddfff111";
    }

    @Test
    public void validateUserFirstName1() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("as"); //valid: min length lastName (2 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserFirstName2() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsj"); //valid: max length first name (32 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserFirstName3() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("Zsdasdas"); //valid: contains lower and upper register symbols
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserFirstName4() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("XXXXXZ"); //valid: contains only upper register symbols
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName5() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("a"); //invalid: first name less than 2 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName6() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsjd"); //invalid: first name more than 32 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName7() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asds4dzx"); //invalid: first name contains digits
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName8() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asds)dzx"); //invalid: first name contains special symbols
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserFirstName9() throws Exception {
        User user = initCorrectUser();
        user.setFirstName("asds dzx"); //invalid: first name contains space
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test
    public void validateUserLastName1() throws Exception {
        User user = initCorrectUser();
        user.setLastName("as"); //valid: min length last name (2 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserLastName2() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsj"); //valid: max length last name (32 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserLastName3() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asdsdzxcx"); //valid: random last name
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName4() throws Exception {
        User user = initCorrectUser();
        user.setLastName("a"); //invalid: last name less than 2 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName5() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asdsdzxcxsduhfjhdhjsnncjjmmmsjsjd"); //invalid: last name more than 32 chars
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName6() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asds4dzx"); //invalid: last name contains digits
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName7() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asds)dzx"); //invalid: last name contains special symbols
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLastName8() throws Exception {
        User user = initCorrectUser();
        user.setLastName("asds dzx"); //invalid: last name contains space
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test
    public void validateUserPhone1() throws Exception {
        User user = initCorrectUser();
        user.setPhone("+1234567890123"); //valid: length = 13, with '+'
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserPhone2() throws Exception {
        User user = initCorrectUser();
        user.setPhone("1234567890123"); //valid: length = 13, without '+'
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserPhone3() throws Exception {
        User user = initCorrectUser();
        user.setPhone("+123456789012"); //invalid: length = 12, with '+'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserPhone4() throws Exception {
        User user = initCorrectUser();
        user.setPhone("123456789012"); //invalid: length = 12, without '+'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }


    @Test(expected = RegistrationValidateException.class)
    public void validateUserPhone5() throws Exception {
        User user = initCorrectUser();
        user.setPhone("+12345678901234"); //invalid: length = 14, with '+'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserPhone6() throws Exception {
        User user = initCorrectUser();
        user.setPhone("12345678901234"); //invalid: length = 14, without '+'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserPhone7() throws Exception {
        User user = initCorrectUser();
        user.setPhone("123456789012("); //invalid: contains special symbols
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserPhone8() throws Exception {
        User user = initCorrectUser();
        user.setPhone("1234567890asd"); //invalid: contains literals
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test
    public void validateUserLogin1() throws Exception {
        User user = initCorrectUser();
        user.setLogin("abcdef"); //valid: length = min (6 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserLogin2() throws Exception {
        User user = initCorrectUser();
        user.setLogin("abcdefghijklmnopqastuvwxyzabcdef"); //valid: length = max (32 chars)
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (RegistrationValidateException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLogin4() throws Exception {
        User user = initCorrectUser();
        user.setLogin("abcde"); //invalid: length < min (5 < 6 chars)
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLogin5() throws Exception {
        User user = initCorrectUser();
        user.setLogin("abcdefghijklmnopqastuvwxyzabcdefi"); //invalid: length > max (33 > 32 chars)
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test
    public void validateUserLogin6() throws Exception {
        User user = initCorrectUser();
        user.setLogin("abcdefghijk213"); //valid: contains digits
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validateUserLogin7() throws Exception {
        User user = initCorrectUser();
        user.setLogin("abCDefghijk213"); //valid: contains digits and upper case symbols
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserLogin8() throws Exception {
        User user = initCorrectUser();
        user.setLogin("abcdefghijklm("); //invalid: contains special symbols
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test
    public void validatePassword1() throws Exception {
        User user = initCorrectUser();
        String password = "123456";//valid: length = min (6)
        try {
            Validator.validateUser(user, password);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validatePassword2() throws Exception {
        User user = initCorrectUser();
        String password = "1234567890123456";//valid: length = max (16)
        try {
            Validator.validateUser(user, password);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validatePassword3() throws Exception {
        User user = initCorrectUser();
        String password = "12345";//invalid: length < min (5 < 6)
        Validator.validateUser(user, password);
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validatePassword4() throws Exception {
        User user = initCorrectUser();
        String password = "12345678901234567";//invalid: length > max (17 > 16)
        Validator.validateUser(user, password);
        fail();
    }

    @Test
    public void validatePassword5() throws Exception {
        User user = initCorrectUser();
        String password = "123456as";//valid: contains lower case symbols
        try {
            Validator.validateUser(user, password);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validatePassword6() throws Exception {
        User user = initCorrectUser();
        String password = "123456asAS";//valid: contains upper case symbols
        try {
            Validator.validateUser(user, password);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validatePassword7() throws Exception {
        User user = initCorrectUser();
        String password = "1234567890123456(";//invalid: contains special symbols
        Validator.validateUser(user, password);
        fail();
    }

    @Test
    public void validateUserEmail1() throws Exception {
        User user = initCorrectUser();
        //valid: contains digits, upper and lower case symbols, '.', '_', '%', '+', '-' before '@'.
        //contains contains digits, upper and lower case symbols, '-', '.' between '@' and last '.'
        //contains after last '.' only upper and lower case symbols
        user.setEmail("aA0._s%a45d+a3d-@asaADA214.dasd-.sadSA");
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserEmail2() throws Exception {
        User user = initCorrectUser();
        user.setEmail("abcsef(@abcdef.abs"); //invalid: contains '(' before '@'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserEmail3() throws Exception {
        User user = initCorrectUser();
        user.setEmail("abcsef@ab%cdef.abs"); //invalid: contains symbol allowed before '@' and forbidden after '@', after '@'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserEmail4() throws Exception {
        User user = initCorrectUser();
        user.setEmail("abcsef@abcdef.a9bs"); //invalid: contains digits after last '.'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateUserEmail5() throws Exception {
        User user = initCorrectUser();
        user.setEmail("abcsef@abcdef.a)bs"); //invalid: contains special symbols after last '.'
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test
    public void validateBirthDate1() throws Exception {
        User user = initCorrectUser();
        user.setBirthDate("2000-12-31"); //valid
        try {
            Validator.validateUser(user, initCorrectPassword());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateBirthDate2() throws Exception {
        User user = initCorrectUser();
        user.setBirthDate("2000-31-12"); //invalid: date and month is swapped
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateBirthDate3() throws Exception {
        User user = initCorrectUser();
        user.setBirthDate("12-31-2000"); //invalid: year in
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateBirthDate4() throws Exception {
        User user = initCorrectUser();
        user.setBirthDate(LocalDate.now().minusYears(150).minusDays(1).toString()); //invalid: date is older than 150 years
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }

    @Test(expected = RegistrationValidateException.class)
    public void validateBirthDate5() throws Exception {
        User user = initCorrectUser();
        user.setBirthDate(LocalDate.now().minusYears(5).plusDays(1).toString()); //invalid: date is younger than 5 years
        Validator.validateUser(user, initCorrectPassword());
        fail();
    }
}