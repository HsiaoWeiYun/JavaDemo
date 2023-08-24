package com.hsiaoweiyun.leetcode;

import java.util.*;

public class LongestCommonPrefix {

//    Write a function to find the longest common prefix string amongst an array of strings.
//
//    If there is no common prefix, return an empty string "".
//
//
//
//    Example 1:
//
//    Input: strs = ["flower","flow","flight"]
//    Output: "fl"
//    Example 2:
//
//    Input: strs = ["dog","racecar","car"]
//    Output: ""
//    Explanation: There is no common prefix among the input strings.
//
//
//            Constraints:
//
//            1 <= strs.length <= 200
//            0 <= strs[i].length <= 200
//    strs[i] consists of only lowercase English letters.

    //我的版本
    public static String longestCommonPrefix1(String[] strs) {

        if (Objects.isNull(strs) || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        //先找出最小長度的字
        int shortestStrIndex = 0;
        int length = 0;
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                length = strs[i].length();
                continue;
            }

            if (strs[i].length() < length) {
                length = strs[i].length();
                shortestStrIndex = i;
            }
        }

        char[] shortestChar = strs[shortestStrIndex].toCharArray();

        //用最小長度的字逐一比對
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < shortestChar.length; i++) {
            boolean match = true;
            char tmpChar = shortestChar[i];
            for(String str : strs){
                char a = str.charAt(i);
                if( a != tmpChar){
                    match = false;
                    break;
                }
            }
            if(match){
                sb.append(shortestChar[i]);
            }else{
                break;
            }
        }

        return sb.toString();
    }

    //網路的版本, 思路差不多, 但是他只找最小與最大的字進行兩個字串的比較就可找出答案
    public static String longestCommonPrefix2(String[] strs) {
        //字典排序
        Arrays.sort(strs);
        //最小
        String s1 = strs[0];
        //最大
        String s2 = strs[strs.length-1];

        //最長前綴必定是最小字串或最小字串子集, 也必定是最長字串的前綴(甚至其本身), 所以沒必要每個都去比較

        int idx = 0;
        while(idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx) == s2.charAt(idx)){
                idx++;
            } else {
                break;
            }
        }
        return s1.substring(0, idx);
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix1(strs));
        System.out.println(longestCommonPrefix1(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix1(new String[]{"ab", "a"}));
        System.out.println(longestCommonPrefix1(new String[]{"cir","car"}));
    }

}
