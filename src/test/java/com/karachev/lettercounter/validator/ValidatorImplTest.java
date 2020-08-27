package com.karachev.lettercounter.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorImplTest {

    private final Validator validator = new ValidatorImpl();

    @Test
    void ValidatorShouldThrowExceptionIfSentenceIsNull() {
        String sentence = null;
        assertThrows(IllegalArgumentException.class, () -> validator.validate(sentence),
                "Sentence is null");
    }

    @Test
    void ValidatorShouldThrowExceptionIfSentenceIsEmpty() {
        String sentence = "";
        assertThrows(IllegalArgumentException.class, () -> validator.validate(sentence),
                "Sentence is empty");
    }

    @Test
    void validatorShouldNotThrowExceptionIfDividendBelowZero() {
        assertDoesNotThrow(() -> (validator).validate("hello"));
    }
}
