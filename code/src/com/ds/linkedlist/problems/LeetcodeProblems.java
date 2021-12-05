package com.ds.linkedlist.problems;

public class LeetcodeProblems {


    //https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public ListNode removeDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode currentNode = head;
        while (currentNode.next != null) {
            ListNode nextNode = currentNode.next;
            while (currentNode.value == nextNode.value) {
                nextNode = nextNode.next;
            }
            currentNode.next = nextNode;
            currentNode = currentNode.next;
        }
        return head;
    }

    //https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode first = list1;
        ListNode second = list2;

        ListNode mergedListHead = new ListNode();
        ListNode tail = mergedListHead;
        while (first != null && second != null) {
            if (first.value < second.value) {
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

    //https://leetcode.com/problems/linked-list-cycle/
    public boolean detectCycle1(ListNode head) {
        if(head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null ) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

    // Length of cycle
    public int lengthCycle(ListNode head) {
        if(head == null) {
            return 0;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null ) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                int length = 0;
                do {
                    slow = slow.next;
                    length += 1;
                } while(slow != fast);

                return length;
            }
        }
        return 0;
    }

    //https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        int length = lengthCycle(head);
        if(length == 0)  {
            return null;
        }

        ListNode first = head;
        ListNode second = head;

        for(int i=0;i<length;i++) {
            first = first.next;
        }
        while(first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }

    //https://leetcode.com/problems/happy-number/
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = squaredSum(slow);
            fast = squaredSum(squaredSum(fast));
        } while(slow != fast);

        if(slow == 1) {
            return true;
        }
        return false;
    }

    private int squaredSum(int n){
        int sum = 0;
        while(n > 0) {
            sum += (n%10)  * (n%10);
            n /= 10;
        }
        return sum;
    }

    // https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode() {
        }
    }
}
