/**
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * @author: lisp2c
 * @date: 2019-02-22
 */
public class P2 {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode cn1 = l1;
        ListNode cn2 = l2;
        ListNode h = null;
        ListNode pre = null;

        int e = 0, v = 0;
        while (cn1 != null || cn2 != null || v >= 10) {

            int v1 = cn1 == null ? 0 : cn1.val;
            int v2 = cn2 == null ? 0 : cn2.val;
            v = v1 + v2 + e;
            e = v / 10;
            ListNode cur = new ListNode(v % 10);
            if (h == null) {
                pre = h = cur;
            } else {
                pre.next = cur;
                pre = cur;
            }

            cn1 = cn1 == null ? null : cn1.next;
            cn2 = cn2 == null ? null : cn2.next;
        }
        return h;

    }

    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

}
