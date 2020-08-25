package com.karachev.lettercounter.provider;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ViewProviderImplTest {

    private final ViewProvider viewProvider = new ViewProviderImpl();

    @Test
    void ProvideViewShouldReturnStringWhenGetMapStringInteger() {
        Map<Character, Integer> lettersCountedInSentence = new LinkedHashMap<>();
        lettersCountedInSentence.put('t', 3);
        lettersCountedInSentence.put('o', 4);
        lettersCountedInSentence.put(' ', 5);
        lettersCountedInSentence.put('b', 2);
        lettersCountedInSentence.put('e', 2);
        lettersCountedInSentence.put('r', 1);
        lettersCountedInSentence.put('n', 1);

        String expected = "\"t\" - 3\n" +
                "\"o\" - 4\n" +
                "\" \" - 5\n" +
                "\"b\" - 2\n" +
                "\"e\" - 2\n" +
                "\"r\" - 1\n" +
                "\"n\" - 1\n";

        String actual = viewProvider.provideView(lettersCountedInSentence);

        assertThat(actual, is(expected));
    }

    @Test
    void ProvideViewShouldReturnStringWhenGetMapStringIntegerWithOnlyTabulationSymbols() {
        Map<Character, Integer> lettersCountedInSentence = new LinkedHashMap<>();
        lettersCountedInSentence.put(' ', 10);

        String expected = "\" \" - 10\n";

        String actual = viewProvider.provideView(lettersCountedInSentence);

        assertThat(actual, is(expected));
    }


}
