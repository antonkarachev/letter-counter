package com.karachev.lettercounter.provider;

import java.util.Formatter;
import java.util.Map;

public class ViewProviderImpl implements ViewProvider {

    @Override
    public String provideView(Map<Character, Integer> lettersCountedInSentence) {
        StringBuilder view = new StringBuilder();
        Formatter formatter = new Formatter(view);

        lettersCountedInSentence.forEach((key, value) ->
                formatter.format("\"%c\" - %d%n", key, value));

        formatter.close();

        return view.toString();
    }

}
