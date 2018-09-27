package com.epam.spring.hometask.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Volkov_Mikhail
 */
public class StringToSetConverter {
    public static Set<String> convert(String input, String separator) {
        String[] mas = input.split(separator);
        return new HashSet<>(Arrays.asList(mas));
    }
}
