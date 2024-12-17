package netease;

import java.util.Scanner;

/**
 * 题目:
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * LeetCode:<a href="https://leetcode-cn.com/problems/max-area-of-island/">...</a>
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_6 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int[][] graph = buildGraph(sc.nextLine());
            int m = graph.length;
            int n = graph[0].length;

            int max = 0;
            // 遍历所有节点，取最大岛屿值
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, dfs(graph, i, j));
                }
            }
            System.out.println(String.valueOf(max));
        }

        // dfs深度优先搜索二维数组中以[i,j]为起点的岛屿面积
        public static int dfs(int[][] array, int i, int j) {
            // 边界条件
            if (i < 0 || i >= array.length || j < 0 || j >= array[0].length) {
                return 0;
            }
            if (array[i][j] == 0) {
                return 0;
            }
            // 沉岛思想。即访问过的岛屿节点置为0，避免重复访问
            array[i][j] = 0;
            int area = 1;
            // 累加所有相邻节点岛屿数量
            area += dfs(array, i - 1, j);
            area += dfs(array, i + 1, j);
            area += dfs(array, i, j - 1);
            area += dfs(array, i, j + 1);
            return area;
        }

        // 根据输入构建二维数组，注意替换中括号
        public static int[][] buildGraph(String input) {
            input = input.replaceAll("\\[", "");
            input = input.replaceAll("]", "");
            String[] array = input.split(";");
            int m = array.length;
            int n = array[0].split(",").length;
            int[][] result = new int[m][n];
            for (int i = 0; i < m; i++) {
                String[] row = array[i].split(",");
                for (int j = 0; j < n; j++) {
                    result[i][j] = Integer.parseInt(row[j]);
                }
            }
            return result;
        }


}










