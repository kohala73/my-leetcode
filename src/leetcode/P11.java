package leetcode;

/**
 * see https://leetcode.com/problems/container-with-most-water/
 *
 * @author: lisp2c
 * @date: 2019-03-28
 */
public class P11 {

    public int maxArea(int[] height) {

        int mw = 0;
        int l = 0, r = height.length-1;

        while (l < r) {
            mw = Math.max(Math.min(height[l], height[r]) * (r - l), mw);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return mw;


    }
}
