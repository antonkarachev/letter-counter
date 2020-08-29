package com.karachev.lettercounter.domain;

import java.util.HashMap;
import java.util.Map;

public class CacheProviderImpl implements CacheProvider {
    private HashMap<String, Map<Character, Integer>> cache = new HashMap<>();

    @Override
    public boolean isValueContains(String sentence) {
        return cache.containsKey(sentence);
    }

    @Override
    public Map<Character, Integer> getLetterCounter(String sentence) {
        return cache.get(sentence);
    }

    @Override
    public void put(String sentence, Map<Character, Integer> letterCounter) {
        cache.put(sentence, letterCounter);
    }

}
