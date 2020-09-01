package com.karachev.lettercounter.domain;

import java.util.Map;

public interface CacheProvider {

    boolean isValueContains(String sentence);

    Map<Character, Integer> getLetterCounter(String sentence);

    void put(String sentence, Map<Character, Integer> letterCounter);

}
