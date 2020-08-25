package com.karachev.lettercounter;

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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LettersCounterTest {

    @Mock
    private Validator mockedValidator;
    @Mock
    private CountingProvider mockedCountingProvider;
    @Mock
    private ViewProvider mockedViewProvider;
    @InjectMocks
    private LettersCounter lettersCounter;

    @Test
    void CountLettersShouldNotUseProviderPackageWhenGettingSameSentenceItShouldReadFromSentenceCache() {
        String sentence1 = "to be or not to be";
        String sentence2 = "to be or not to be";

        Map<Character, Integer> lettersCountedInSentence = new LinkedHashMap<>();
        lettersCountedInSentence.put('t', 3);
        lettersCountedInSentence.put('o', 4);
        lettersCountedInSentence.put(' ', 5);
        lettersCountedInSentence.put('b', 2);
        lettersCountedInSentence.put('e', 2);
        lettersCountedInSentence.put('r', 1);
        lettersCountedInSentence.put('n', 1);

        String view = "\"t\" - 3\n" +
                "\"o\" - 4\n" +
                "\" \" - 5\n" +
                "\"b\" - 2\n" +
                "\"e\" - 2\n" +
                "\"r\" - 1\n" +
                "\"n\" - 1\n";

        doNothing().when(mockedValidator).validate(anyString());
        when(mockedCountingProvider.provideCounting(anyString()))
                .thenReturn(lettersCountedInSentence);
        when(mockedViewProvider.provideView(anyMap())).thenReturn(view);

        lettersCounter.countLetters(sentence1);
        lettersCounter.countLetters(sentence2);

        verify(mockedCountingProvider, atLeast(1)).provideCounting(anyString());
    }
}
