package com.karachev.lettercounter.domain;

import java.util.HashMap;
import java.util.Map;

public class CacheProvider {
    private HashMap<String, Map<Character, Integer>> cacheOfSentences = new HashMap<>();

    public boolean isValueContains(String sentence) {
       return cacheOfSentences.containsKey(sentence);
    }

    public Map<Character, Integer> getLetterCounter(String sentence) {
        return cacheOfSentences.get(sentence);
    }

    public void put(String sentence, Map<Character, Integer> letterCounter) {
        cacheOfSentences.put(sentence, letterCounter);
    }

}
