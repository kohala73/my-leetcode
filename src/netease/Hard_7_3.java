package netease;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 题目:拼接最大数
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (0 <=k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 1.构建单调栈，用数组实现
 * LeetCode:<a href="https://leetcode.cn/problems/create-maximum-number/solutions/505931/pin-jie-zui-da-shu-by-leetcode-solution/">...</a>
 *
 * @author xuyj
 * @date 2024/4/10
 */
public class Hard_7_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums1 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::valueOf).toArray();

        int[] nums2 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::valueOf).toArray();

        int k = Integer.valueOf(sc.nextLine());
        int[] maxNums = maxNum(nums1, nums2, k);

        System.out.println(
                Arrays.stream(maxNums).mapToObj(String::valueOf).collect(Collectors.joining(","))
        );


    }

    public static int[] maxNum(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxNum = new int[k];
        int start = Math.max(0, k - n), end = Math.min(m, k);
        for (int i = start; i <= end; i++) {
            int[] maxSeq1 = maxSeq(nums1, i);
            int[] maxSeq2 = maxSeq(nums2, k - i);
            int[] curSeq = merge(maxSeq1, maxSeq2);
            if (compare(curSeq, 0, maxNum, 0) > 0) {
                maxNum = curSeq;

            }
        }
        return maxNum;
    }

    public static int[] merge(int[] nums1, int[] nums2) {
        int x = nums1.length, y = nums2.length;

        if (x == 0) {
            return nums2;
        }
        if (y == 0) {
            return nums1;
        }
        int len = x + y;
        int[] ans = new int[len];
        int ind1 = 0, ind2 = 0;
        for (int i = 0; i < len; i++) {
            if (compare(nums1, ind1, nums2, ind2) > 0) {
                ans[i] = nums1[ind1];
                ind1++;
            } else {
                ans[i] = nums2[ind2];
                ind2++;
            }

        }
        return ans;
    }


    public static int compare(int[] nums1, int ind1, int[] nums2, int ind2) {
        int x = nums1.length, y = nums2.length;
        while (ind1 < x && ind2 < y) {
            int diff = nums1[ind1] - nums2[ind2];
            if (diff != 0) {
                return diff;
            }
            ind1++;
            ind2++;
        }
        return (x - ind1) - (y - ind2);
    }

    public static int[] maxSeq(int[] nums, int k) {
        int n = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = n - k;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            //在remain>0即还有炮弹剩余的情况下，把大数往栈里塞
            while (top >= 0 && stack[top] < num && remain > 0) {
                remain--;
                top--;
            }
            //栈没有塞满，放进去
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                //塞满了，丢弃当前数
                remain--;
            }
        }
        return stack;

    }


}










