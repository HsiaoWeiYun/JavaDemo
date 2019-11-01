package com.hsiaoweiyun.leetcode;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author VictorHsiao on 2019/10/31.
 */
public class IntReverse {


    /*
     * Given a 32-bit signed integer, reverse digits of an integer.
     * Example 1:
     * Input: 123  Output: 321
     *
     * Example 2:
     * Input: -123  Output: -321
     *
     * Example 3:
     * Input: 120  Output: 21
     *
     * Note:
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
     * [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     * */


    @Test
    public void testReverse() {
        int value = -123;
        int expect = -321;
        Assert.assertEquals(expect, reverse(value));
        Assert.assertEquals(expect, reverseApproach2(value));
    }

    //my answer Brute Force...
    public int reverse(int x) {
        if (x < 10 && x > -10) {
            return x;
        }

        boolean isPostive = x >= 0;

        char[] val = String.valueOf(Math.abs(x)).toCharArray();
        StringBuilder sb = new StringBuilder(val.length);
        for (int i = val.length - 1; i > -1; i--) {
            sb.append(val[i]);
        }
        int result = 0;
        try {
            result = Integer.valueOf(sb.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        if (!isPostive) {
            return Math.negateExact(result);
        }
        return result;
    }


    public int reverseApproach2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;  //Integer.MAX_VALUE = 2147483647(end with 7)
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0; //Integer.MIN_VALUE = -2147483648(end with -8 )
            rev = rev * 10 + pop;
        }
        return rev;
    }


}
