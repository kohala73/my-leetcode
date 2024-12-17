package leetcode;

/**
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * @author: lisp2c
 * @date: 2019-02-25
 */
public class P5 {


    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 0) {
            return "";
        }
        boolean[][] dp = new boolean[n][n];
        int lp = 0;
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                dp[j][i] = chars[i] == chars[j] && (i - j < 3 || dp[j+1][i - 1]);
                if (dp[j][i] && (i - j + 1 > lp)) {
                    lp = i - j + 1;
                    x = i;
                    y = j;
                }

            }
        }

        return  s.substring(y, x+1);

    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome("ABA"));
    }
}
