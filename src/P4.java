/**
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 *
 * @author: lisp2c
 * @date: 2019-02-24
 */
public class P4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n3 = n1 + n2;

        int[] nums3 = new int[n3];
        int i = 0, j = 0, m = 0;

        while (i < n1 || j < n2) {
            if (i == n1) {
                nums3[m] = nums2[j];
                j++;
            } else if (j == n2) {
                nums3[m] = nums1[i];
                i++;
            } else {
                int m1 = nums1[i];
                int m2 = nums2[j];
                if (m1 <= m2) {
                    i++;
                    nums3[m] = m1;
                } else {
                    nums3[m] = m2;
                    j++;
                }
            }

            m++;
        }

        if (n3 % 2 != 0) {
            return nums3[n3 / 2];
        }

        double d = nums3[n3 / 2] + nums3[n3 / 2 - 1];
        return  d/ 2;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};

        findMedianSortedArrays(nums1, nums2);
    }
}
