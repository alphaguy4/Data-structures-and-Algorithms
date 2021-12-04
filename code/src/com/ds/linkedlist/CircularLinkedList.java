package com.ds.linkedlist;

public class CircularLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public CircularLinkedList() {
        this.size = 0;
    }

    public void insert(int value) {
        Node node = new Node(value);
        if(head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.next = head;
            tail = node;
        }
        size += 1;
    }

    public void display() {
        Node node = head;

        do {
            System.out.print(node.value + " -> ");
            node = node.next;
        } while (node != head);

        System.out.println("HEAD");
        System.out.println("Size = " + size);
    }

    public void delete(int value) {
        if(size == 0) {
            System.err.println("Error !! No elements to delete");
            return;
        }

        Node node = head;

        if(node.value == value) {
            head = head.next;
            tail.next = head;
        }

        do {
            Node nextNode = node.next;
            if(nextNode.value == value) {
                node.next = nextNode.next;
                break;
            }
            node = node.next;
        } while (node != head);
        size -= 1;
    }

    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}
