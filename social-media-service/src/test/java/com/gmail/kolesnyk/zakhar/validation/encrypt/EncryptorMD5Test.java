package com.gmail.kolesnyk.zakhar.validation.encrypt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class EncryptorMD5Test {
    private EncryptorMD5 encryptorMD5;

    @Before
    public void init() {
        encryptorMD5 = new EncryptorMD5();
    }

    @Test
    public void encryptPassword12345() throws Exception {
        Assert.assertEquals("827ccb0eea8a706c4c34a16891f84e7b".toUpperCase(), encryptorMD5.encryptPassword("12345"));
    }

    @Test
    public void encryptPassword1234567890() throws Exception {
        Assert.assertEquals("e807f1fcf82d132f9bb018ca6738a19f".toUpperCase(), encryptorMD5.encryptPassword("1234567890"));
    }

    @Test
    public void encryptPasswordABCDEF() throws Exception {
        Assert.assertEquals("8827a41122a5028b9808c7bf84b9fcf6".toUpperCase(), encryptorMD5.encryptPassword("ABCDEF"));
    }

    @Test
    public void encryptPasswordabcdef() throws Exception {
        Assert.assertEquals("e80b5017098950fc58aad83c8c14978e".toUpperCase(), encryptorMD5.encryptPassword("abcdef"));
    }

    @Test
    public void encryptPassword12345abcdef() throws Exception {
        Assert.assertEquals("4880c0340c65d142838ea33ace9b850a".toUpperCase(), encryptorMD5.encryptPassword("12345abcdef"));
    }

    @Test
    public void encryptPassword1234567890abcdefghij() throws Exception {
        Assert.assertEquals("f5faba5601bc6c316858409e5fadaed9".toUpperCase(), encryptorMD5.encryptPassword("1234567890abcdefghij"));
    }

    @Test
    public void invalidEncryptPasswordNull() {
        try {
            encryptorMD5.encryptPassword(null);
            fail();
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    public void invalidEncryptPasswordLength0() {
        try {
            encryptorMD5.encryptPassword("");
            fail();
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    public void invalidEncryptPasswordLengthMore20() {
        try {
            encryptorMD5.encryptPassword("123456789012345678901");
            fail();
        } catch (IllegalArgumentException ignore) {
        }
    }

    @After
    public void destroy() {
        encryptorMD5 = null;
    }
}