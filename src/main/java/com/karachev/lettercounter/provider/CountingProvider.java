package com.karachev.lettercounter.provider;

import com.karachev.lettercounter.domain.CacheProvider;

import java.util.Map;

public interface CountingProvider {
    Map<Character, Integer> provideCounting(String sentence, CacheProvider cacheProvider);

}
