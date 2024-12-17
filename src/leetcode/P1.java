package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * @author: lisp2c
 * @date: 2019-02-22
 */
public class P1 {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int[] re = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int o = target - nums[i];
            Integer obj = map.get(o);
            if (obj != null && obj != i) {
                re[0] = i;
                re[1] = obj;
                return re;
            }
        }
        throw new RuntimeException("404");
    }

    public static void main(String[] args) {

        int[] ints = new int[]{3, 2, 4};

        System.out.println(Arrays.toString(twoSum(ints, 6)));

    }
}
