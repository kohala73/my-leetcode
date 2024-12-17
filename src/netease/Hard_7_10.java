package netease;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目：超级回文数
 * 如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为超级回文数。
 * 现在，给定两个正整数 L 和 R （以字符串形式表示），返回包含在范围 [L, R] 中的超级回文数的数目。
 * <p>
 * LeetCode:<a href="https://leetcode.cn/problems/super-palindromes/description/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_10 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lr = sc.nextLine().split(",");
        System.out.println(superPalindromes(lr[0], lr[1]));
    }

    public static List<Long> superPalindromes(String ls, String rs) {
        long m = 100000;
        long l = Long.valueOf(ls), r = Long.valueOf(rs);
        List<Long> ans = new ArrayList<>();
        for (int k = 1; k < m; k++) {
            String ks = String.valueOf(k);
            StringBuilder sb = new StringBuilder(ks);
            for (int i = ks.length() - 1; i >= 0; i--) {
                sb.append(ks.charAt(i));
            }
            long v = Long.valueOf(sb.toString());
            v *= v;
            if (v >= l && v <= r && isPalindromes(v)) {
                ans.add(v);
            }

            sb = new StringBuilder(ks);
            for (int i = ks.length() - 2; i >= 0; i--) {
                sb.append(ks.charAt(i));
            }
            v = Long.valueOf(sb.toString());
            v *= v;
            if (v >= l && v <= r && isPalindromes(v)) {
                ans.add(v);
            }

        }

        ans.sort(Comparator.comparingLong(a -> a));

        return ans;

    }

    public static boolean isPalindromes(long v) {
        return v == reverse(v);
    }

    public static long reverse(long v) {
        long r = 0;
        while (v > 0) {
            r = 10 * r + v % 10;
            v = v / 10;
        }
        return r;
    }


}










