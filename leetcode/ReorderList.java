
143. Reorder List
题目描述：
/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

*/

解决方案（两种）：
1、将链表每个节点存放在一个数组中，通过头下标递增、尾下标递减的方式确认每个节点的位置
2、两个指针，慢指针每次走一步，快指针每次走两步，直到快指针到达末尾；分两个链表，一个链表从头结点到慢指针的前一个节点；另一个从慢指针的位置开始，将后面的每个节点转向，得到新链表（链表2）的节点为最末节点；合并两个链表


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	public void reorderList(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        ListNode[] nodes = new ListNode[len];
        int count = 0;
        node = head;
        while (count < len) {
            nodes[count++] = node;
            node = node.next;
        }

        int start = 0;
        int end = len - 1;
        while (start < end) {
            ListNode forward = nodes[start];
            ListNode last = nodes[end];
            nodes[end - 1].next = null;
            last.next = forward.next;
            forward.next = last;
            start++;
            end--;
        }
    }
	
    public void reorderList2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode node1 = head;
        ListNode node2 = reverse(slow);
        while (node1 != null && node2 != null) {
            ListNode next = node2.next;
            node2.next = node1.next;
            node1.next = node2;
            node1 = node2.next;
            node2 = next;
        }
        if (node1 != null)
            node1.next = null;
    }

    public ListNode reverse(ListNode head) {
        //将链表反转
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }
}