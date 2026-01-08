package com.dominik.util;

public final class ValidationUtil {

    private ValidationUtil() {}

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static Integer parseNullablePositiveInt(String text, String errorMessage) {
        if (isBlank(text)) return null;
        try {
            int v = Integer.parseInt(text.trim());
            if (v <= 0) throw new IllegalArgumentException(errorMessage);
            return v;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(errorMessage, ex);
        }
    }
}
