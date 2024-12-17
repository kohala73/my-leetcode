package leetcode;

/**
 * @link https://leetcode.com/problems/regular-expression-matching/
 *
 * @author: lisp2c
 * @date: 2019-03-03
 */
public class P10 {


    public static boolean isMatch(String s, String p) {

        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        boolean[][] dp = new boolean[sc.length+1][pc.length+1];

        //init,index 0表示空字符，从1开始
        dp[0][0] = true;

        //s为空
        for (int j = 0; j < pc.length; j++) {
            dp[0][j + 1] = pc[j] == '*' && dp[0][j-1];
        }


        for (int i = 0; i < sc.length; i++) {
            for (int j = 1; j < pc.length; j++) {
                if (sc[i] == pc[j] || pc[j] == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (pc[j] == '*') {
                    if (sc[i] == pc[j - 1] || pc[j - 1] == '.') {
                        dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }

                }
            }

        }
        return dp[sc.length][pc.length];
    }


    public static void main(String[] args) {

        System.out.println(isMatch("aab", "c*a*b"));

        System.out.println(isMatch("a", "*"));
    }


    public static boolean isMatch0(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}
