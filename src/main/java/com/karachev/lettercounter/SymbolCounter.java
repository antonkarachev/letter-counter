package com.karachev.lettercounter;

import com.karachev.lettercounter.domain.CacheProviderImpl;
import com.karachev.lettercounter.provider.CountingProvider;
import com.karachev.lettercounter.provider.ViewProvider;
import com.karachev.lettercounter.validator.Validator;

import java.util.Map;

public class SymbolCounter {

    private final Validator validator;
    private final CountingProvider countingProvider;
    private final ViewProvider viewProvider;
    private final CacheProviderImpl cacheProviderImpl;

    public SymbolCounter(Validator validator, CountingProvider countingProvider,
                         ViewProvider viewProvider, CacheProviderImpl cacheProviderImpl) {
        this.validator = validator;
        this.countingProvider = countingProvider;
        this.viewProvider = viewProvider;
        this.cacheProviderImpl = cacheProviderImpl;
    }

    public String countSymbols(String sentence) {
        validator.validate(sentence);
        final Map<Character, Integer> symbolToCounter;

        if (cacheProviderImpl.isValueContains(sentence)) {
            symbolToCounter = cacheProviderImpl.getLetterCounter(sentence);
        } else {
            symbolToCounter = countingProvider.provideCounting(sentence);
            cacheProviderImpl.put(sentence, symbolToCounter);
        }

        return viewProvider.provideView(symbolToCounter);
    }

}
