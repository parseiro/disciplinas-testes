package com.vilelapinheiro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Identifier {

    public static void validate(final String string) {

        final String patternString = "[a-zA-Z][a-zA-Z0-9]{0,5}";

        final Pattern pattern = Pattern.compile(patternString);
        Matcher m = pattern.matcher(string);

        if (!m.matches()) {
            System.out.println("Recusei '" + string + "'");
            throw new IllegalArgumentException();
        }
    }
}
