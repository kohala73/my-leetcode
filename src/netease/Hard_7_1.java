package netease;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目:按格式合并两个链表
 * 1.反转链表
 * 2.递归合并链表
 * @author xuyj
 * @date 2024/4/10
 */
public class Hard_7_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] line1 = sc.nextLine().split(" ");

        String list1HeadAddr = line1[0];

        String list2HeadAddr = line1[1];

        int nums = Integer.parseInt(line1[2]);

        Map<String, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < nums; i++) {

            String[] line = sc.nextLine().split(" ");

            Node node = new Node();

            node.addr = line[0];

            node.val = Integer.parseInt(line[1]);

            node.nextAddr = line[2];

            nodeMap.put(node.addr, node);

        }

        Node head1 = nodeMap.get(list1HeadAddr);

        Node head2 = nodeMap.get(list2HeadAddr);

        int length1 = 0;

        int length2 = 0;

        while (!head1.nextAddr.equals("-1")) {

            head1.next = nodeMap.get(head1.nextAddr);

            head1 = head1.next;

            length1++;

        }

        while (!head2.nextAddr.equals("-1")) {

            head2.next = nodeMap.get(head2.nextAddr);

            head2 = head2.next;

            length2++;

        }

        Node longed = length1 > length2 ? nodeMap.get(list1HeadAddr) : nodeMap.get(list2HeadAddr);

        Node shorted = length1 > length2 ? dfs(nodeMap.get(list2HeadAddr)) : dfs(nodeMap.get(list1HeadAddr));

        Node result = exchange(longed, shorted);

        while (result != null) {

            System.out.print(result.addr + " " + result.val + " " + result.nextAddr);

            if (result.next != null) {

                System.out.println();

            }

            result = result.next;

        }

    }

    private static Node exchange(Node longed, Node shorted) {

        if (longed == null || longed.next == null || longed.next.next == null || shorted == null) {

            return longed;

        }
        exchange(longed.next.next, shorted.next);

        Node temp = longed.next.next;

        longed.next.next = shorted;

        longed.next.nextAddr = shorted.addr;

        shorted.next = temp;

        shorted.nextAddr = temp.addr;

        return longed;

    }

    private static Node dfs(Node head) {

        if (head.next == null) {

            return head;

        }

        Node temp = dfs(head.next);

        head.next.next = head;

        head.next.nextAddr = head.addr;

        head.next = null;

        head.nextAddr = "-1";

        return temp;

    }


    static class Node {

        int val;

        Node next;

        String addr;

        String nextAddr;

    }

}

