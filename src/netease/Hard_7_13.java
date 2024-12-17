package netease;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 题目：无重复字符的最长子串
 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 *
 * LeetCode:<a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_13 {

    public static void main(String args[]) {
        Scanner sc=  new Scanner(System.in);
        System.out.println(lengthOfLongestSubstring(sc.nextLine()));
        sc.close();
    }

    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * //外层循环扩展右边界，内层循环扩展左边界
     * for (int l = 0, r = 0 ; r < n ; r++) {
     * 	//当前考虑的元素
     * 	while (l <= r && check()) {//区间[left,right]不符合题意
     *         //扩展左边界
     *     }
     *     //区间[left,right]符合题意，统计相关信息
     * }
     * @param s
     * @return
     */

    public static int lengthOfLongestSubstring_(String s) {
        Set<Character> occ=new HashSet<>();
        int left=0;
        int res=0;
        for(int right=0;right<s.length();right++){
            while(occ.contains(s.charAt(right))){
                occ.remove(s.charAt(left));
                left++;
            }
            occ.add(s.charAt(right));
            res=Math.max(res,right-left+1);
        }


        return res;
    }

}











