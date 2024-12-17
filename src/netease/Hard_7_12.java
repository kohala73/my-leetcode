package netease;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目：最长有效括号
 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * LeetCode:<a href="https://leetcode.cn/problems/longest-valid-parentheses/description/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_12 {

    public static void main(String[] args){
        System.out.println(longVaildParenthese(new Scanner(System.in).nextLine()));



    }
    public static long longVaildParenthese(String s){
        int ans=0;
        //用stack存没有')' 配对的 '('的index
        Deque<Integer>  stack=new LinkedList<>();

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                stack.push(i);
            }else {
                if(!stack.isEmpty()&&s.charAt(stack.peek())=='('){
                    //配对成功，让他们飞
                    stack.pop();
                    //都飞走了
                    if(stack.isEmpty()){
                        ans=Math.max(ans,i+1);
                    }else{
                        ans=Math.max(ans,i-stack.peek());
                    }
                }else{
                    //没人要的 ')', 成了垃圾栈
                    stack.push(i);
                }
            }

        }
        return ans;



    }
}










