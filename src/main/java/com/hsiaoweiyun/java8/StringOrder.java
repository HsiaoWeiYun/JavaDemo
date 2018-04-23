package com.hsiaoweiyun.java8;

public class StringOrder {

    public static int byLength(String s1, String s2){
        return s1.length() - s2.length();
    }

    public int byLexicography(String s1, String s2){
        return s1.compareTo(s2);
    }
}
