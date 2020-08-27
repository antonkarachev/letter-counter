package com.karachev.lettercounter.provider;

import com.karachev.lettercounter.domain.CacheProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountingProviderImpl implements CountingProvider {

    @Override
    public Map<Character, Integer> provideCounting(String sentence, CacheProvider cacheProvider) {

        Map<Character, Integer> lettersCountedInSentence = countLetter(sentence);
        cacheProvider.put(sentence, lettersCountedInSentence);
        return lettersCountedInSentence;
    }

    private Map<Character, Integer> countLetter(String sentence) {
        Map<Character, Integer> lettersCountedInSentence = new LinkedHashMap<>();
        char[] sentenceInLetters = sentence.toCharArray();

        for (int i = 0; i < sentenceInLetters.length; i++) {
            lettersCountedInSentence.merge(sentenceInLetters[i],1, (a,b)->a+b);
        }

        return lettersCountedInSentence;
    }
}
