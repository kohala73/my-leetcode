/**
 *
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: 121
 * Output: true
 *
 * @author: lisp2c
 * @date: 2019-03-03
 */
public class P9 {

    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }

        int rev = P7.reverse(x);

        return rev == x;
    }

}
