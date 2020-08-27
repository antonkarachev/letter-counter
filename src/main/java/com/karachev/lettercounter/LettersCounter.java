package com.karachev.lettercounter;

import com.karachev.lettercounter.domain.CacheProvider;
import com.karachev.lettercounter.provider.CountingProvider;
import com.karachev.lettercounter.provider.ViewProvider;
import com.karachev.lettercounter.validator.Validator;

import java.util.Map;

public class LettersCounter {

    private final Validator validator;
    private final CountingProvider countingProvider;
    private final ViewProvider viewProvider;
    private final CacheProvider cacheProvider;

    public LettersCounter(Validator validator, CountingProvider countingProvider,
                          ViewProvider viewProvider, CacheProvider cacheProvider) {
        this.validator = validator;
        this.countingProvider = countingProvider;
        this.viewProvider = viewProvider;
        this.cacheProvider = cacheProvider;
    }

    public String countLetters(String sentence) {
        validator.validate(sentence);
        final Map<Character, Integer> lettersCountedInSentence;

        if (cacheProvider.isValueContains(sentence)) {
            lettersCountedInSentence = cacheProvider.getLetterCounter(sentence);
        } else {
            lettersCountedInSentence = countingProvider.provideCounting(sentence, cacheProvider);
        }

        return viewProvider.provideView(lettersCountedInSentence);
    }

}
