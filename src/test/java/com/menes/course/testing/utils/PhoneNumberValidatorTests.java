package com.menes.course.testing.utils;

import com.menes.course.testing.utils.validator.PhoneNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneNumberValidatorTests {
    private PhoneNumberValidator phoneNumberValidator;

    @BeforeEach
    public void setUp() {
        phoneNumberValidator = new PhoneNumberValidator();
    }

    @Test
    void itShouldReturnFalseWhenInputIsNull() {
        // Given
        String phoneNumber = null;

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputIsEmpty() {
        // Given
        String phoneNumber = "";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputIsWhitespace() {
        // Given
        String phoneNumber = "   ";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputIsShorterThan11Characters() {
        // Given
        String phoneNumber = "0123456789";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputIsLongerThan11Characters() {
        // Given
        String phoneNumber = "012345678901";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputDoesNotStartWith0() {
        // Given
        String phoneNumber = "12345678901";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnTrueWhenInputIsValidPhoneNumber() {
        // Given
        String phoneNumber = "01234567890";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertTrue(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputContainsLettersOrSpecialCharacters() {
        // Given
        String phoneNumberWithLetter = "0123456789a";
        String phoneNumberWithHyphen = "01234-67890";
        String phoneNumberWithAtSymbol = "01234@67890";

        // When
        boolean isValidWithLetter = phoneNumberValidator.test(phoneNumberWithLetter);
        boolean isValidWithHyphen = phoneNumberValidator.test(phoneNumberWithHyphen);
        boolean isValidWithAtSymbol = phoneNumberValidator.test(phoneNumberWithAtSymbol);

        // Then
        assertFalse(isValidWithLetter);
        assertFalse(isValidWithHyphen);
        assertFalse(isValidWithAtSymbol);
    }

    @Test
    void itShouldReturnFalseWhenInputHasLeadingWhitespace() {
        // Given
        String phoneNumber = " 01234567890";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputHasTrailingWhitespace() {
        // Given
        String phoneNumber = "01234567890 ";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }

    @Test
    void itShouldReturnFalseWhenInputHasSpacesInBetween() {
        // Given
        String phoneNumber = "012 345 67890";

        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertFalse(isValid);
    }
}
