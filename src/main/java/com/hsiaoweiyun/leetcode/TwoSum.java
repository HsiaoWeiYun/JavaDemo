package com.hsiaoweiyun.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TwoSum {


//    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//    You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//    You can return the answer in any order.
//
//
//
//            Example 1:
//
//    Input: nums = [2,7,11,15], target = 9
//    Output: [0,1]
//    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
//    Example 2:
//
//    Input: nums = [3,2,4], target = 6
//    Output: [1,2]
//    Example 3:
//
//    Input: nums = [3,3], target = 6
//    Output: [0,1]
//
//
//    Constraints:
//
//            2 <= nums.length <= 104
//            -109 <= nums[i] <= 109
//            -109 <= target <= 109
//    Only one valid answer exists.
//
//
//            Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?

    //自己寫的, 比暴力法還廢
    public static int[] myTwoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(i == j){
                    continue;
                }

                if((nums[i] + nums[j]) == target){
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    public static int[] myTwoSum2(int[] nums, int target) {
        Map<Integer, Integer> tmp = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            tmp.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(!(Objects.nonNull(tmp.get(complement)) && tmp.get(complement) != i)){
                continue;
            }
            return new int[]{i, tmp.get(complement)};
        }
        return null;
    }

    public static int[] myTwoSum3(int[] nums, int target) {
        Map<Integer, Integer> tmp = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(Objects.isNull(tmp.get(complement))){
                tmp.put(nums[i], i);
                continue;
            }

            return new int[]{i, tmp.get(complement)};
        }
        return null;
    }

    public static int[] bruteForceTwoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }

//    Approach 2: Two-pass Hash Table (複雜度 2n=O(n))

//    Intuition
//    To improve our runtime complexity, we need a more efficient way to check if the complement exists in the array. If the complement exists, we need to get its index. What is the best way to maintain a mapping of each element in the array to its index? A hash table.
//
//    We can reduce the lookup time from O(n)O(n)O(n) to O(1)O(1)O(1) by trading space for speed. A hash table is well suited for this purpose because it supports fast lookup in near constant time. I say "near" because if a collision occurred, a lookup could degenerate to O(n)O(n)O(n) time. However, lookup in a hash table should be amortized O(1)O(1)O(1) time as long as the hash function was chosen carefully.
//
//    Algorithm
//    A simple implementation uses two iterations. In the first iteration, we add each element's value as a key and its index as a value to the hash table. Then, in the second iteration, we check if each element's complement (target−nums[i]target - nums[i]target−nums[i]) exists in the hash table. If it does exist, we return current element's index and its complement's index. Beware that the complement must not be nums[i]nums[i]nums[i] itself!
    public static int[] twoSum2(int[] nums, int target) {
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
        // In case there is no solution, we'll just return null
        return null;
    }


//    Approach 3: One-pass Hash Table 加法有交換性
//    Algorithm
//
//    It turns out we can do it in one-pass. While we are iterating and inserting elements into the hash table, we also look back to check if current element's complement already exists in the hash table. If it exists, we have found a solution and return the indices immediately.

    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }


    public static void main(String[] args){
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(myTwoSum3(nums, target)));

        nums = new int[]{3,2,4};
        target = 6;
        System.out.println(Arrays.toString(myTwoSum3(nums, target)));

        nums = new int[]{3,3};
        target = 6;
        System.out.println(Arrays.toString(myTwoSum3(nums, target)));
    }

}
