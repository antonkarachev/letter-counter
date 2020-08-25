package com.karachev.lettercounter.provider;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.karachev.lettercounter.domain.SentenceCache.saveSentence;

public class CountingProviderImpl implements CountingProvider {

    @Override
    public Map<Character, Integer> provideCounting(String sentence) {

        Map<Character, Integer> lettersCountedInSentence = countLetter(sentence);
        saveSentence(sentence, lettersCountedInSentence);
        return lettersCountedInSentence;
    }

    private Map<Character, Integer> countLetter(String sentence) {
        Map<Character, Integer> lettersCountedInSentence = new LinkedHashMap<>();
        char[] sentenceInLetters = sentence.toCharArray();

        for (int i = 0; i < sentenceInLetters.length; i++) {
            int sentenceLengthIn = sentence.length();
            String countingLetter = String.valueOf(sentenceInLetters[i]);
            int sentenceLengthOut = sentence.replaceAll(countingLetter, "").length();
            int counter = sentenceLengthIn - sentenceLengthOut;
            lettersCountedInSentence.put(sentenceInLetters[i], counter);
        }

        return lettersCountedInSentence;
    }
}

