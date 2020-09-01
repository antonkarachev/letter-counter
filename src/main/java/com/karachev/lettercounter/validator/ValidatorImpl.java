package com.karachev.lettercounter.validator;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Sentence is null");
        }

        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("Sentence is empty");
        }
    }

}
