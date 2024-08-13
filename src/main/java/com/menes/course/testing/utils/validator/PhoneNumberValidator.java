package com.menes.course.testing.utils.validator;

import java.util.function.Predicate;

public class PhoneNumberValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        if (isNullOrEmpty(s)) {
            return false;
        }
        return isStartWithNumber(s) && s.length() == 11;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private boolean isStartWithNumber(String s) {
        return s.matches("^0[0-9]{10}$");
    }
}
