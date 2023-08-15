package com.hsiaoweiyun.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Roman2Integer {


//    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
    //Symbol       Value
//    I             1
//    V             5
//    X             10
//    L             50
//    C             100
//    D             500
//    M             1000
//    For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
//
//    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
//    I can be placed before V (5) and X (10) to make 4 and 9.
//    X can be placed before L (50) and C (100) to make 40 and 90.
//    C can be placed before D (500) and M (1000) to make 400 and 900.
//    Given a roman numeral, convert it to an integer.
//
//
//
//    Example 1:
//
//    Input: s = "III"
//    Output: 3
//    Explanation: III = 3.
//    Example 2:
//
//    Input: s = "LVIII"
//    Output: 58
//    Explanation: L = 50, V= 5, III = 3.
//    Example 3:
//
//    Input: s = "MCMXCIV"
//    Output: 1994
//    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
//
//
//    Constraints:
//
//            1 <= s.length <= 15
//    s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
//    It is guaranteed that s is a valid roman numeral in the range [1, 3999].


    //暴力法, 還是很廢, 關鍵沒意識到這段英文 'Roman numerals are usually written largest to smallest from left to right.' ~ 大到小左到右, 特殊規則是小到大的
    public static int myRomanToInt(String s) {

        String input = s.toUpperCase();
        Map<String, Integer> specialRoman = new HashMap<String, Integer>(6) {{
            put("IV", 4);
            put("IX", 9);
            put("XL", 40);
            put("XC", 90);
            put("CD", 400);
            put("CM", 900);
        }};

        Map<String, Integer> roman = new HashMap<String, Integer>(7) {{
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }};

        int result = 0;

        for (int i = 0; i < input.length(); i++) {
            if (i < input.length() - 1 && Objects.nonNull(specialRoman.get(input.substring(i, i + 2)))) {
                result += specialRoman.get(input.substring(i, i + 2));
                i++;
                continue;
            }
            result += roman.get(input.substring(i, i + 1));
        }
        return result;
    }

    //看過答案後的解法
    public static int myRomanToInt2(String s) {

        Map<Character, Integer> roman = new HashMap<Character, Integer>(7) {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        String input = s.toUpperCase();
        int result = 0;

        for (int i = 0; i < input.length(); i++) {
            if (i < input.length() - 1 && (roman.get(input.charAt(i)) < roman.get(input.charAt(i + 1)))) {
                result += roman.get(input.charAt(i + 1)) - roman.get(input.charAt(i));
                i++;
            } else {
                result += roman.get(input.charAt(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(myRomanToInt("III"));
        System.out.println(myRomanToInt("LVIII"));
        System.out.println(myRomanToInt("MCMXCIV"));

        System.out.println();

        System.out.println(myRomanToInt2("III"));
        System.out.println(myRomanToInt2("LVIII"));
        System.out.println(myRomanToInt2("MCMXCIV"));
    }
}
