package com.karachev.lettercounter;

import com.karachev.lettercounter.domain.CacheProvider;
import com.karachev.lettercounter.provider.CountingProvider;
import com.karachev.lettercounter.provider.ViewProvider;
import com.karachev.lettercounter.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SymbolCounterTest {

    @Mock
    private Validator mockedValidator;

    @Mock
    private CountingProvider mockedCountingProvider;

    @Mock
    private ViewProvider mockedViewProvider;

    @Mock
    private CacheProvider mockedCacheProvider;

    @InjectMocks
    private SymbolCounter symbolCounter;

    @Test
    void сountLettersShouldReturnStringOfASpecificFormatWhenCacheDoesNotHaveSentence() {
        String sentence = "to be or not to be";

        Map<Character, Integer> lettersCountedInSentence = new LinkedHashMap<>();
        lettersCountedInSentence.put('t', 3);
        lettersCountedInSentence.put('o', 4);
        lettersCountedInSentence.put(' ', 5);
        lettersCountedInSentence.put('b', 2);
        lettersCountedInSentence.put('e', 2);
        lettersCountedInSentence.put('r', 1);
        lettersCountedInSentence.put('n', 1);

        String view = "\"t\" - 3\r\n" +
                "\"o\" - 4\r\n" +
                "\" \" - 5\r\n" +
                "\"b\" - 2\r\n" +
                "\"e\" - 2\r\n" +
                "\"r\" - 1\r\n" +
                "\"n\" - 1\r\n";

        doNothing().when(mockedValidator).validate(anyString());
        when(mockedCacheProvider.isValueContains(anyString())).thenReturn(false);
        when(mockedCountingProvider.countSymbols(anyString()))
                .thenReturn(lettersCountedInSentence);
        when(mockedViewProvider.provideView(anyMap())).thenReturn(view);

        symbolCounter.countSymbols(sentence);

        verify(mockedValidator).validate(anyString());
        verify(mockedCacheProvider).isValueContains(anyString());
        verify(mockedCountingProvider).countSymbols(anyString());
        verify(mockedViewProvider).provideView(anyMap());

    }

    @Test
    void сountLettersShouldNotUseCountingProviderPackageWhenGettingSameSentenceItShouldReadFromSentenceCache() {
        String sentence = "to be or not to be";

        Map<Character, Integer> lettersCountedInSentence = new LinkedHashMap<>();
        lettersCountedInSentence.put('t', 3);
        lettersCountedInSentence.put('o', 4);
        lettersCountedInSentence.put(' ', 5);
        lettersCountedInSentence.put('b', 2);
        lettersCountedInSentence.put('e', 2);
        lettersCountedInSentence.put('r', 1);
        lettersCountedInSentence.put('n', 1);

        String view = "\"t\" - 3\r\n" +
                "\"o\" - 4\r\n" +
                "\" \" - 5\r\n" +
                "\"b\" - 2\r\n" +
                "\"e\" - 2\r\n" +
                "\"r\" - 1\r\n" +
                "\"n\" - 1\r\n";

        doNothing().when(mockedValidator).validate(anyString());
        when(mockedCacheProvider.isValueContains(anyString())).thenReturn(true);
        when(mockedCacheProvider.getLetterCounter(anyString())).thenReturn(lettersCountedInSentence);
        when(mockedViewProvider.provideView(anyMap())).thenReturn(view);

        symbolCounter.countSymbols(sentence);

        verify(mockedValidator).validate(anyString());
        verify(mockedCacheProvider).isValueContains(anyString());
        verify(mockedCacheProvider).getLetterCounter(anyString());
        verifyZeroInteractions(mockedCountingProvider);
        verify(mockedViewProvider).provideView(anyMap());
    }

    @Test
    void сountLettersShouldNotRunsWhenValidatorThrowsException() {
        doThrow(new IllegalArgumentException()).when(mockedValidator).validate(anyString());
        assertThrows(IllegalArgumentException.class, () -> symbolCounter.countSymbols(""));
        verifyNoMoreInteractions(mockedCacheProvider, mockedCountingProvider,
                mockedCountingProvider, mockedViewProvider);
    }

}
