package com.ds;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    public void insertFirst(int value) {
        System.out.println("Insert First: " + value);
        Node node = new Node(value);
        node.next = head;
        head = node;
        /* if the linked list is empty */
        if(tail == null) {
            tail = head;
        }
        size += 1;
    }

    public void insertLast(int value) {
        System.out.println("Insert Last: " + value);
        if(head == null) {
            insertFirst(value);
            return;
        } else {
            Node node = new Node(value);
            tail.next = node;
            tail = node;
        }
        size += 1;
    }

    public void insertAt(int value, int idx) {
        System.out.println("Insert: " + value + ", idx: " + idx);

        if(idx == 0) {
            insertFirst(value);
        } else if(idx == size) {
            insertLast(value);
        } else if(idx > size) {
            System.err.println("Error! Index can't be greater than size of Linked List");
        } else {
            Node currentNode = head;
            for(int i = 1; i < idx; i++) {
                currentNode = currentNode.next;
            }
            Node node = new Node(value);
            node.next = currentNode.next;
            currentNode.next = node;
        }
        size += 1;
    }

    public int find(int value) {
        Node currentNode = head;

        int cnt = 0;
        while(currentNode != null) {
            if(currentNode.value == value) {
                System.out.println("Found " + value + " at index: " + cnt);
                return cnt;
            }
            currentNode = currentNode.next;
            cnt += 1;
        }
        System.out.println("404 Not found!!");
        return -1;
    }


    public int deleteFirst() {
        if(head == null) {
            System.err.println("Error !! Nothing to delete List is empty");
            return -1;
        }

        System.out.println("Delete First element");
        int value = head.value;
        head = head.next;
        if(head == null) {
            tail = null;
        }
        size -= 1 ;
        return value;
    }

    public int deleteLast() {
        if(size <= 1) {
            return deleteFirst();
        }

        System.out.println("Delete Last element");

        Node secondLastNode = get(size - 2);
        int value = tail.value;
        tail = secondLastNode;
        tail.next = null;

        size -= 1;

        return value;
    }

    public int delete(int value) {
        int idx = find(value);

        if(idx == -1) {
            System.err.println("Error Not found");
            return -1;
        }

        if(idx == 0) {
            return deleteFirst();
        } else if(idx == size - 1) {
            return deleteLast();
        } else {
            Node prevNode = get(idx - 1);
            prevNode.next = prevNode.next.next;
            size -= 1;
        }
        return value;
    }

    public Node get(int idx) {
        Node node = head;
        for(int i = 0; i < idx; i++) {
            node = node.next;
        }
        return node;
    }


    public void printList() {
        Node currentNode = head;
        while(currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("null");
        System.out.println("Size = " + size);
    }

    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

}
