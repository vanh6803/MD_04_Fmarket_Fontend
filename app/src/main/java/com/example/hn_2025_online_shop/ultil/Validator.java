package com.example.hn_2025_online_shop.ultil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_NUMBER_PATTERN =
            "^(\\+[0-9]+[\\- \\.]*)?([0-9][\\- \\.]*){6,14}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static final Pattern patternPhone = Pattern.compile(PHONE_NUMBER_PATTERN);

    public static boolean isValidPhone(String phoneNumber) {
        Matcher matcher = patternPhone.matcher(phoneNumber);
        return matcher.matches();
    }
}
