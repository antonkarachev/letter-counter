package com.karachev.lettercounter.domain;

import java.util.LinkedHashMap;
import java.util.Map;


public class CacheProviderImpl implements CacheProvider {
    private final int capacity;
    private final Map<String, Map<Character, Integer>> cache =
            new LinkedHashMap<String, Map<Character, Integer>>() {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String,
                        Map<Character, Integer>> eldest) {
                    return size() > capacity;
                }
            };

    public CacheProviderImpl(int capacity) {
        this.capacity = capacity;
    }

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
