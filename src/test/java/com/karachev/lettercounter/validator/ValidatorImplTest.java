package com.karachev.lettercounter.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorImplTest {

    private final Validator validator = new ValidatorImpl();

    @Test
    void validatorShouldThrowExceptionIfSentenceIsNull() {
        String sentence = null;
        assertThrows(IllegalArgumentException.class, () -> validator.validate(sentence),
                "Sentence is null");
    }

    @Test
    void validatorShouldThrowExceptionIfSentenceIsEmpty() {
        String sentence = "";
        assertThrows(IllegalArgumentException.class, () -> validator.validate(sentence),
                "Sentence is empty");
    }

    @Test
    void validatorShouldNotThrowExceptionIfDividendBelowZero() {
        assertDoesNotThrow(() -> validator.validate("hello"));
    }
}
