package com.menes.course.testing.utils;

public class DataUtils {
    private DataUtils(){}

    public static boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
