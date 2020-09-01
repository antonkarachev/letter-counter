package com.karachev.lettercounter.provider;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CountingProviderImplTest {

    private final CountingProvider countingProvider = new CountingProviderImpl();

    @Test
    void provideCountingShouldReturnMapWhenGettingSentenceWithTwoWords() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 1);

        Map<Character, Integer> actual =
                countingProvider.countSymbols("hello world!");

        assertThat(actual, is(expected));
    }

    @Test
    void provideCountingShouldReturnMapWhenGettingSentenceWithOneWordWithOneRepeatedLetter() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 10);

        Map<Character, Integer> actual =
                countingProvider.countSymbols("hhhhhhhhhh");

        assertThat(actual, is(expected));
    }

    @Test
    void provideCountingShouldReturnMapWhenGettingSentenceWithTwoWordWithOneRepeatedLetter() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 20);
        expected.put(' ', 1);

        Map<Character, Integer> actual =
                countingProvider.countSymbols("hhhhhhhhhh hhhhhhhhhh");

        assertThat(actual, is(expected));
    }

}
