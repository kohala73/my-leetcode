package netease;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 题目:二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * LeetCode:<a href="https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_5 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //注意节点值为负数的情况
    static int MAX_NUM=Integer.MIN_VALUE;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        TreeNode root=constructTree(sc.nextLine().split(","));
        maxGain(root);
        System.out.println(MAX_NUM);


    }
    public static int maxGain(TreeNode node){
        if(node==null){
            return 0;
        }
        int leftMaxGain=Math.max(maxGain(node.left),0);
        int rightMaxGain=Math.max(maxGain(node.right),0);

        int curMaxGain=node.val+leftMaxGain+rightMaxGain;
        MAX_NUM=Math.max(curMaxGain,MAX_NUM);
        return node.val+Math.max(leftMaxGain,rightMaxGain);


    }


    public static TreeNode constructTree(String[] valArr){
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(valArr[0]));
        queue.offer(root);
        int ind=1;
        while(!queue.isEmpty() && ind<valArr.length){
            TreeNode node=queue.poll();

            if(!"null".equals(valArr[ind])){
                node.left=new TreeNode(Integer.parseInt(valArr[ind]));
                queue.offer(node.left);
            }

            ind++;
            if(ind<valArr.length &&!"null".equals(valArr[ind])){
                node.right=new TreeNode(Integer.valueOf(valArr[ind]));
                queue.offer(node.right);

            }
            ind++;
        }
        return root;

    }


}










