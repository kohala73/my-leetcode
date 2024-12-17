package netease;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目:
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 * <p>
 * LeetCode:<a href="https://leetcode-cn.com/problems/uncrossed-lines/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_7 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            int[] nums1 = Arrays.stream(sc.nextLine().split(" ")).skip(1).mapToInt(Integer::valueOf).toArray();
            int[] nums2 = Arrays.stream(sc.nextLine().split(" ")).skip(1).mapToInt(Integer::valueOf).toArray();
            System.out.println(numOfNotLine(nums1, nums2));
        }

    }

    public static int numOfNotLine(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 0; i < l1; i++) {
            int m = nums1[i];
            for (int j = 0; j < l2; j++) {
                int n = nums2[j];
                if (m == n) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }

            }

        }
        return dp[l1][l2];

    }


}










