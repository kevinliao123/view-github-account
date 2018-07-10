package kevinliao.com.viewgithub.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateUtilsTest {

    @Test
    public void isValidateEmail() {
        assertTrue(ValidateUtils.isValidateEmail("github@gmail.com"));
        assertFalse(ValidateUtils.isValidateEmail("githubgmail.com"));
        assertFalse(ValidateUtils.isValidateEmail("github@gmailcom"));
    }

    @Test
    public void isValidatePassword() {
        assertTrue(ValidateUtils.isValidatePassword("Github11234"));
        assertFalse(ValidateUtils.isValidatePassword("Github1"));
        assertFalse(ValidateUtils.isValidatePassword("Github"));
        assertFalse(ValidateUtils.isValidatePassword("github1"));
    }
}