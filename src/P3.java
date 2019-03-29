/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * @author: lisp2c
 * @date: 2019-02-23
 */
public class P3 {

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;

        boolean[] map = new boolean[128];

        int i = 0, j = 0;

        while (i < n && j < n) {
            if (!map[chars[j]]) {
                map[chars[j]] = true;
                j++;
                ans = Math.max(ans, j - i);
            } else {
                map[chars[i]] = false;
                i++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));

    }

}
