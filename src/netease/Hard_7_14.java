package netease;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目：按位与为零的三元组
 给你一个整数数组 nums ，返回其中 按位与三元组 的数目。
 按位与三元组 是由下标 (i, j, k) 组成的三元组，并满足下述全部条件：
 0 <= i < nums.length
 0 <= j < nums.length
 0 <= k < nums.length
 nums[i] & nums[j] & nums[k] == 0 ，其中 & 表示按位与运算符。
 *
 * LeetCode:<a href="https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero/description/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_14 {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(
                bitAndTriple(Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::valueOf).toArray()));
    }

    public static int bitAndTriple(int[] nums){
        int ans=0;
        int len=1<<16;
        int[] cnt=new int[len];
        for(int i:nums){
            for(int j:nums){
                //
                ++cnt[i & j];
            }
        }

        for (int k:nums){
            for(int i=0;i<len;i++){
                if((k & i) == 0){
                    ans+=cnt[i];

                }
            }

        }
        return ans;


    }


}










