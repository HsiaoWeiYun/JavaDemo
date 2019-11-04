package com.hsiaoweiyun.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author VictorHsiao on 2019/11/4.
 */
public class RomanToInt {

    private static final Map<Character, Integer> ROMAN_TO_INTEGER = new HashMap<>(7);

    static {
        ROMAN_TO_INTEGER.put('I', 1);
        ROMAN_TO_INTEGER.put('V', 5);
        ROMAN_TO_INTEGER.put('X', 10);
        ROMAN_TO_INTEGER.put('L', 50);
        ROMAN_TO_INTEGER.put('C', 100);
        ROMAN_TO_INTEGER.put('D', 500);
        ROMAN_TO_INTEGER.put('M', 1000);
    }

    @Test
    public void test() {
        System.out.println();
        Assert.assertEquals(3, romanToInt("III"));
        Assert.assertEquals(4, romanToInt("IV"));
        Assert.assertEquals(9, romanToInt("IX"));
        Assert.assertEquals(58, romanToInt("LVIII"));
        Assert.assertEquals(1994, romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {

        char[] romans = s.toCharArray();

        int result = 0;

        for (int i = 0; i < romans.length; i++) {
            if ((i + 1) == romans.length) {
                result += ROMAN_TO_INTEGER.get(romans[i]);
                continue;
            }

            if ((ROMAN_TO_INTEGER.get(romans[i]) - ROMAN_TO_INTEGER.get(romans[i + 1])) < 0) {
                result = result + (ROMAN_TO_INTEGER.get(romans[i + 1]) - ROMAN_TO_INTEGER.get(romans[i]));
                i++;
            } else {
                result += ROMAN_TO_INTEGER.get(romans[i]);
            }

        }
        return result;
    }
}
