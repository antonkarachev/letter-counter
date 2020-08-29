package com.karachev.lettercounter.domain;

import java.util.HashMap;
import java.util.Map;

public interface CacheProvider {
    HashMap<String, Map<Character, Integer>> cache = new HashMap<>();

    boolean isValueContains(String sentence);

    Map<Character, Integer> getLetterCounter(String sentence);

    void put(String sentence, Map<Character, Integer> letterCounter);
}
