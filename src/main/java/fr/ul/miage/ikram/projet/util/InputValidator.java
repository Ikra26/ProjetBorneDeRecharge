package fr.ul.miage.ikram.projet.util;

import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d+");
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).find();
    }

    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}

