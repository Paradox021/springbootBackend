package com.example.demo.utils;

public class Utils {
    public static boolean isPalindrome(String value){
        value = value.replace(" ", "");
        StringBuilder builder =new StringBuilder(value);
        String reversedName = builder.reverse().toString();
        return value.equals(reversedName);
    }
}
