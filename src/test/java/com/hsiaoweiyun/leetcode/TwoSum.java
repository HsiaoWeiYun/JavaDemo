package com.hsiaoweiyun.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author VictorHsiao on 2019/10/31.
 */
public class TwoSum {

    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int[] answer = {0, 1};
        int target = 9;

        Assert.assertArrayEquals(answer, twoSum(nums, target));
        Assert.assertArrayEquals(answer, twoSumApproach2(nums, target));
        Assert.assertArrayEquals(answer, twoSumApproach3(nums, target));
    }

    //my answer Brute Force...
    public int[] twoSum(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return new int[0];
        }

        int size = nums.length;
        for (int i = 0; i < size; i++) {
            for (int j = i +1; j < size; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSumApproach2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSumApproach3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
