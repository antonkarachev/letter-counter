package com.karachev.lettercounter;

import com.karachev.lettercounter.provider.CountingProvider;
import com.karachev.lettercounter.provider.ViewProvider;
import com.karachev.lettercounter.validator.Validator;

import java.util.Map;

import static com.karachev.lettercounter.domain.SentenceCache.cacheContainsSentence;
import static com.karachev.lettercounter.domain.SentenceCache.getLetterCounter;

public class LettersCounter {

    private final Validator validator;
    private final CountingProvider countingProvider;
    private final ViewProvider viewProvider;

    public LettersCounter(Validator validator, CountingProvider countingProvider,
                          ViewProvider viewProvider) {
        this.validator = validator;
        this.countingProvider = countingProvider;
        this.viewProvider = viewProvider;
    }

    public String countLetters(String sentence) {
        validator.validate(sentence);
        Map<Character, Integer> lettersCountedInSentence;

        if (cacheContainsSentence(sentence)) {
            lettersCountedInSentence = getLetterCounter(sentence);
        } else {
            lettersCountedInSentence = countingProvider.provideCounting(sentence);
        }

        return viewProvider.provideView(lettersCountedInSentence);
    }

}
