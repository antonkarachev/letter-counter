package com.karachev.lettercounter.provider;

import java.util.Map;

public interface ViewProvider {
    String provideView(Map<Character, Integer> lettersCountedInSentence);
}
