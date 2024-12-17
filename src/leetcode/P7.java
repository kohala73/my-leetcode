package leetcode;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 *
 * @author: lisp2c
 * @date: 2019-02-26
 */
public class P7 {

    public static int reverse(int x) {

        long z = 0;
        int y = x;
        int t = y / 10;
        int i = y % 10;
        while (t != 0 || i != 0) {
            z = z * 10 + i;
            if (z != (int) z) {
                return 0;
            }
            y = t;
            t = y / 10;
            i = y % 10;
        }
        return (int) z;
    }

}
