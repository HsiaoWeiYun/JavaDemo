package com.hsiaoweiyun.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author VictorHsiao on 2019/11/1.
 */
public class PalindromeNumber {

    @Test
    public void testPalindrome() {
        System.out.println(isPalindrome(10));
    }

    //my answer
    public boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }

        List<Integer> container = new ArrayList<>();
        int orgVal = x;

        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            container.add(pop);
        }

        int sum = 0;
        for (int num : container) {
            sum = (sum * 10) + num;
        }
        return sum == orgVal;
    }


}
