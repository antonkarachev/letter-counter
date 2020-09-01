package com.karachev.lettercounter;

import com.karachev.lettercounter.domain.CacheProvider;
import com.karachev.lettercounter.domain.CacheProviderImpl;
import com.karachev.lettercounter.provider.CountingProvider;
import com.karachev.lettercounter.provider.CountingProviderImpl;
import com.karachev.lettercounter.provider.ViewProvider;
import com.karachev.lettercounter.provider.ViewProviderImpl;
import com.karachev.lettercounter.validator.Validator;
import com.karachev.lettercounter.validator.ValidatorImpl;

import java.util.Scanner;

public class SymbolCounterApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi! This is console application for counting unique " +
                "characters in sentence, which is inputted");
        System.out.print("Enter your sentence: ");
        String sentence = scanner.nextLine();

        Validator validator = new ValidatorImpl();
        CountingProvider countingProvider = new CountingProviderImpl();
        ViewProvider viewProvider = new ViewProviderImpl();
        CacheProvider cacheProvider = new CacheProviderImpl(100);

        SymbolCounter symbolCounter = new SymbolCounter(validator, countingProvider,
                viewProvider, cacheProvider);

        System.out.println(symbolCounter.countSymbols(sentence));
    }

}
