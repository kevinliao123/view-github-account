package kevinliao.com.viewgithub.util;

import java.util.regex.Pattern;

import static android.support.v4.util.PatternsCompat.EMAIL_ADDRESS;

public class ValidateUtils {
    public static boolean isValidateEmail(String email) {
        return EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidatePassword(String password) {
        return Pattern.compile(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{9,}$"
        ).matcher(password).matches();
    }
}
