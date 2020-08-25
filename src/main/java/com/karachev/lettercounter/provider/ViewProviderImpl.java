package com.karachev.lettercounter.provider;

import java.util.Map;

public class ViewProviderImpl implements ViewProvider {

    private static final String NEW_LINE = "\n";
    private static final String DASH = "-";
    private static final String SPACE_DELIMITER = " ";
    private static final String QUOTE = "\"";

    @Override
    public String provideView(Map<Character, Integer> lettersCountedInSentence) {
        StringBuilder view = new StringBuilder();

        for (Map.Entry<Character, Integer> entry: lettersCountedInSentence.entrySet()){

            view.append(QUOTE)
                    .append(entry.getKey())
                    .append(QUOTE)
                    .append(SPACE_DELIMITER)
                    .append(DASH)
                    .append(SPACE_DELIMITER)
                    .append(entry.getValue())
                    .append(NEW_LINE);
        }

        return view.toString();
    }

}
