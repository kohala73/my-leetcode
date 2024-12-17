package netease;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 给你一个整数数组 perm ，它是前 n 个正整数（1,2,3,4,5,…,n-1,n 共n个正整数）的排列，且 n 是个奇数 。
 * 它被加密成另一个长度为 n-1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i+1]。比方说，如果 perm=[1,3,2] ，那么 encoded=[2,1]。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * 提示：
 * n 是奇数。
 * 3 <= n < 10^5
 * encoded.length == n - 1
 * <p>
 * LeetCode:<a href="https://leetcode.cn/problems/decode-xored-permutation/description/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */public class _Hard_7_8 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int[] nums1 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::valueOf).toArray();


        System.out.println(Arrays.stream(decode(nums1)).mapToObj(String::valueOf).collect(Collectors.joining(",")));

    }

    public static int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }
        int odd = 0;
        for (int i = 1; i < n - 1; i += 2) {
            odd ^= encoded[i];
        }
        int[] perm = new int[n];
        perm[0] = total ^ odd;
        for (int i = 0; i < n - 1; i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }
        return perm;
    }
}










