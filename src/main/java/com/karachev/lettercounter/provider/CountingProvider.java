package com.karachev.lettercounter.provider;

import java.util.Map;

public interface CountingProvider {
    Map<Character, Integer> provideCounting(String sentence);

}
