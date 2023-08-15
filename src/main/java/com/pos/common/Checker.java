package com.pos.common;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.matches;

public class Checker {
    public static boolean validStrOnly(String str){
        return str != null && !str.isEmpty()
                && str.matches("^[a-zA-Z]*$");
    }

    public static boolean isNullStr(String str){
        return str != null && !str.isEmpty();
    }

    public static final Pattern EMAIL = Pattern.compile("^[_A-Za-z0-9-+]+([._A-Za-z0-9]+)@[A-Za-z0-9-]+(.[A-Za-z0-9]+)(.[A-Za-z]{2,})$");

    public static boolean isEmail(String str) {
        return matches(str, EMAIL.pattern());
    }

}

