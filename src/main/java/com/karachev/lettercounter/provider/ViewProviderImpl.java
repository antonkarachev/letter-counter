package com.karachev.lettercounter.provider;

import java.util.Map;

public class ViewProviderImpl implements ViewProvider {

    private static final String VIEW_FORMAT = "\"%c\" - %d%n";

    @Override
    public String provideView(Map<Character, Integer> symbolsCountedInSentence) {
        return createView(symbolsCountedInSentence);
    }

    private String createView(Map<Character, Integer> symbolsCountedInSentence) {
        StringBuilder view = new StringBuilder();

        symbolsCountedInSentence.forEach((key, value) ->
                view.append(String.format(VIEW_FORMAT, key, value)));

        return view.toString();
    }

}
