package netease;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目:按照公因式计算最大组件大小
 * 1.公因式计算，i*i<=num,num、num/i、i属于同一组
 * 2.构建并查集,启发式合并
 * <p>
 * LeetCode:<a href="https://leetcode-cn.com/problems/largest-component-size-by-common-factor/">...</a>
 *
 * @author xuyj
 * @date 2024/4/10
 */
public class Hard_7_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                maxCompentSize(Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray())
        );


    }


    public static int maxCompentSize(int[] nums) {
        int maxNum = Arrays.stream(nums).max().orElse(0);
        UnionFind uf = new UnionFind(maxNum + 1);
        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    uf.union(i, num / i);
                    uf.union(i, num);
                }
            }
        }
        int[] cnt = new int[maxNum + 1];
        int ans = 0;
        for (int num : nums) {
            int root = uf.find(num);
            cnt[root]++;

            ans = Math.max(ans, cnt[root]);
        }

        return ans;


    }


    static class UnionFind{
        int[] parent;
        int[] rank;


        public UnionFind(int n){
            parent=new int[n];
            rank=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
            }

        }

        public void union(int x,int y){
            int rootX=find(x),rootY=find(y);

            if(rootX!=rootY){
                int rankX=rank[rootX],rankY=rank[rootY];
                if(rankX>rankY){
                    parent[rootY]=rootX;

                }else if(rankX<rankY){
                    parent[rootX]=rootY;
                }else{
                    parent[rootX]=rootY;
                    rank[rootY]++;
                }


            }


        }

        public int find(int x){
            if(parent[x]==x){
                return parent[x];

            }
            return find(parent[x]);
        }
    }

}















