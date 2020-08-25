package com.karachev.lettercounter.domain;

import java.util.HashMap;
import java.util.Map;

public class SentenceCache {
    private static HashMap<String, Map<Character, Integer>> cacheOfSentences = new HashMap<>();

    public static boolean cacheContainsSentence(String sentence) {
        if (cacheOfSentences.isEmpty()) {
            return false;
        }
        return cacheOfSentences.containsKey(sentence);
    }

    public static Map<Character, Integer> getLetterCounter(String sentence) {
        return cacheOfSentences.get(sentence);
    }

    public static void saveSentence(String sentence, Map<Character, Integer> letterCounter) {
        cacheOfSentences.put(sentence, letterCounter);
    }

}
