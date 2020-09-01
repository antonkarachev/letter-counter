package com.karachev.lettercounter.provider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingProviderImpl implements CountingProvider {

    @Override
    public Map<Character, Integer> countSymbols(String sentence) {
        return sentence.chars()
                .mapToObj(symbol -> (char) symbol)
                .collect(Collectors.toMap(Function.identity(),
                        counter -> 1,
                        Integer::sum,
                        LinkedHashMap::new));
    }

}
