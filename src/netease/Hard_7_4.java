package netease;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目:最长递增子序列
 * 给你一个整数数组nums，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * LeetCode:<a href="https://leetcode-cn.com/problems/longest-increasing-subsequence/">...</a>
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(lengthOfLIS(nums));

    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int maxAns = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxAns = Math.max(maxAns, dp[i]);

        }
        return maxAns;
    }


}










