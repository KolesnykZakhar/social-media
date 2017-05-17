package com.gmail.kolesnyk.zakhar.validation;

/**
 * The {@code RegistrationValidateException} exception class used for informing of cause
 * that not happens successful registration of new User
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.validation.Validator
 * @since JDK1.8
 */
public class RegistrationValidateException extends RuntimeException {

    public RegistrationValidateException(String message) {
        super(message);
    }
}
