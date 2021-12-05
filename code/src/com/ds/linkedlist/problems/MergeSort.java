package com.ds.linkedlist.problems;

//https://leetcode.com/problems/sort-list/
public class MergeSort {

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode middle = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(middle);
        return merge(left, right);
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = null;

        while(head != null && head.next != null) {
            slow = slow == null ? head : slow.next;
            head = head.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        return mid;
    }

    private ListNode merge(ListNode first, ListNode second) {
        ListNode mergedListHead = new ListNode();
        ListNode tail = mergedListHead;

        while(first != null && second != null) {
            if(first.val < second.val) {
                tail.next = first;
                first = first.next;
                tail = tail.next;
            } else {
                tail.next = second;
                second = second.next;
                tail = tail.next;
            }
        }
        tail.next = first != null ? first : second;
        return mergedListHead.next;
    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode() {}
        public ListNode(int val) {
            this.val = val;
        }
    }

}
