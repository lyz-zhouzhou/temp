package com.example.learn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoNumSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] index = new TwoNumSum().twoSum(nums, target);
        System.out.println(Arrays.toString(index));
    }

    public int[] twoSum(int[] nums, int target) {
        /*
        解题思路 1、双层循环，暴力破解
                2、利用hashmap
         */
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
