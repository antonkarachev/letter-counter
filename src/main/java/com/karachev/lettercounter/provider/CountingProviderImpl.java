package com.karachev.lettercounter.provider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingProviderImpl implements CountingProvider {

    @Override
    public Map<Character, Integer> provideCounting(String sentence) {
        return countSymbols(sentence);
    }

    private Map<Character, Integer> countSymbols(String sentence) {
        return sentence.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(Function.identity(),
                        c -> 1,
                        Integer::sum,
                        LinkedHashMap::new));
    }
}
