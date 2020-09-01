package com.karachev.lettercounter;

import com.karachev.lettercounter.domain.CacheProvider;
import com.karachev.lettercounter.provider.CountingProvider;
import com.karachev.lettercounter.provider.ViewProvider;
import com.karachev.lettercounter.validator.Validator;

import java.util.Map;

public class SymbolCounter {

    private final Validator validator;
    private final CountingProvider countingProvider;
    private final ViewProvider viewProvider;
    private final CacheProvider cacheProvider;

    public SymbolCounter(Validator validator, CountingProvider countingProvider,
                         ViewProvider viewProvider, CacheProvider cacheProvider) {
        this.validator = validator;
        this.countingProvider = countingProvider;
        this.viewProvider = viewProvider;
        this.cacheProvider = cacheProvider;
    }

    public String countSymbols(String sentence) {
        validator.validate(sentence);
        final Map<Character, Integer> symbolToCounter;

        if (cacheProvider.isValueContains(sentence)) {
            symbolToCounter = cacheProvider.getLetterCounter(sentence);
        } else {
            symbolToCounter = countingProvider.countSymbols(sentence);
            cacheProvider.put(sentence, symbolToCounter);
        }

        return viewProvider.provideView(symbolToCounter);
    }

}
