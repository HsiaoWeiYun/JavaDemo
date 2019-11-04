package com.hsiaoweiyun.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author VictorHsiao on 2019/11/4.
 */
public class LongestCommonPrefix {

    @Test
    public void test() {
        String[] strs = new String[]{"flower", "flow", "flight"};
        Assert.assertEquals("fl", longestCommonPrefix(strs));
        Assert.assertEquals("fl", longestCommonPrefixApproach1(strs));
        Assert.assertEquals("fl", longestCommonPrefixApproach2(strs));

        //還有很多方法, Approach 3: Divide and conquer   Approach 4: Binary search
    }

    //my answer
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (Objects.isNull(strs) || strs.length == 0) {
            return "";
        }

        int minIndex = -1;
        int min = -1;
        for (int i = 0; i < strs.length; i++) {
            if (min == -1 || strs[i].length() < min) {
                min = strs[i].length();
                minIndex = i;
            }
        }

        char[] minChar = strs[minIndex].toCharArray();
        String minStr = strs[minIndex];

        for (int i = minChar.length; i > -1; i--) {
            String subStr = minStr.substring(0, i);
            int count = 0;
            for (String str : strs) {
                if (str.startsWith(subStr)) {
                    count++;
                }
            }
            if (count == strs.length) {
                result = subStr;
                break;
            }
        }

        return result;
    }

    //Horizontal scanning
    public String longestCommonPrefixApproach1(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public String longestCommonPrefixApproach2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }


}
