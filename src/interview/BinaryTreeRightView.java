package interview;

import base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树右视图
 *
 * @author xuyj
 * @date 2024/11/29
 */
public class BinaryTreeRightView {


    private static List<Integer> rightView(TreeNode root) {

        List<Integer> rightViewList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode == null) {
                    continue;
                }
                if (i == size - 1) {
                    rightViewList.add(currentNode.val);
                }
                TreeNode left = currentNode.left;
                TreeNode right = currentNode.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
        }
        return rightViewList;
    }

}
