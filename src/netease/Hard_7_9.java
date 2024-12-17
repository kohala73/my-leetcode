package netease;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目：找出最长的超赞子字符串
 * 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
 * 「超赞子字符串」需满足满足下述两个条件：
 * 1. 该字符串是 s 的一个非空子字符串
 * 2. 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
 * <p>
 * LeetCode:<a href="https://leetcode.cn/problems/find-longest-awesome-substring/description/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_9 {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(longestAwesome(sc.nextLine()));

    }

    public static int longestAwesome(String s) {
        Map<Integer,Integer> prefix=new HashMap<>();
        //状态值是bit算的，每个bit位下标代表该数字，bit位的值表示count奇偶性， 0^1=1，1^1=0
        int status=0;
        //代表这个状态值首次出现位置
        prefix.put(status,-1);
        int ans=1;

        for(int i=0;i<s.length();i++){
            int num=s.charAt(i)-'0';
            //异或做计数
            status^=1<<num;
            if(prefix.containsKey(status)){
                ans=Math.max(ans,i-prefix.get(status));
            }else{
                prefix.put(status,i);
            }
            //用0-9的数字做消消乐，被消掉了，它就是多余的那个数字
            for(int j=0;j<10;j++){
                int next=status^(1<<j);
                if(prefix.containsKey(next)){
                    ans=Math.max(ans,i-prefix.get(next));
                }

            }

        }
        return ans;

    }

}






