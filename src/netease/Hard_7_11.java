package netease;

import java.util.*;

/**
 * 题目：最多能完成排序的块 II
 将 arr 分割成若干 块 ，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 返回能将数组分成的最多块数？
 *
 * LeetCode:<a href="https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/description/">...</a>
 *
 * @author xuyj
 * @date 2024/4/22
 */
public class Hard_7_11 {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(
                maxSortedBlockSize(Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray()));

    }

    public static int maxSortedBlockSize(int[] nums){
        int ans=0;
        int len= nums.length;
        int[] snums=new int[len];
        System.arraycopy(nums,0,snums,0,len);
        Arrays.sort(snums);
        Map<Integer,Integer> cnt=new HashMap<>();
        for(int i=0;i<len;i++){
            int x=nums[i];
            int y=snums[i];
            cnt.put(x,cnt.getOrDefault(x,0)+1);
            if(cnt.get(x)==0){
                cnt.remove(x);
            }
            cnt.put(y,cnt.getOrDefault(y,0)-1);
            if(cnt.get(y)==0){
                cnt.remove(y);
            }
            if(cnt.isEmpty()){
                ans++;
            }
        }
        return ans;

    }


    class Solution {
        public int maxChunksToSorted(int[] arr) {
            Deque<Integer> stack = new ArrayDeque<Integer>();
            for (int num : arr) {
                if (stack.isEmpty() || num >= stack.peek()) {
                    stack.push(num);
                } else {
                    int mx = stack.pop();
                    while (!stack.isEmpty() && stack.peek() > num) {
                        stack.pop();
                    }
                    stack.push(mx);
                }
            }
            return stack.size();
        }
    }
    



}










