package com.ds.LinkedList;

public class DoublyLinkedList {
    private Node head;
    private int size;

    public DoublyLinkedList() {
        this.size = 0;
    }

    public void insertFirst(int value) {
        Node node = new Node(value);
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        size += 1;
    }

    public void display() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.value + " <-> ");
            currentNode = currentNode.next;
        }
        System.out.println("null");
        System.out.println("Size = " + size);
    }

    public void displayReverse() {
        Node tailNode = getLastNode();

        while (tailNode != null) {
            System.out.print(tailNode.value + " <-> ");
            tailNode = tailNode.prev;
        }
        System.out.println("null");
        System.out.println("Size = " + size);
    }

    public Node getLastNode() {
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    public void insertLast(int val) {
        if (size == 0) {
            insertFirst(val);
            return;
        }
        Node tail = getLastNode();
        Node node = new Node(val);
        node.next = null;
        node.prev = tail;
        tail.next = node;
        size += 1;
    }

    public void insertAt(int value, int idx) {
        if (idx == 0) {
            insertFirst(value);
        } else if (idx == size) {
            insertLast(value);
        } else if (idx > size) {
            System.err.println("Error !! Index can't be greater than size of Doubly linked list");
        } else {
            Node prev = get(idx - 1);
            Node next = prev.next;

            Node node = new Node(value);
            node.next = next;
            node.prev = prev;
            prev.next = node;
            next.prev = node;
            size += 1;
        }
    }

    public void deleteFirst() {
        if (size == 0) {
            System.err.println("No elements to delete. List is empty");
            return;
        } else if (size == 1) {
            head = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size -= 1;
    }

    public void delete(int value) {
        // Todo Improvement required
        int idx = find(value);
        if (idx == -1) {
            System.err.println("Error !! 404 not found");
        } else {
            if (idx == 0) {
                deleteFirst();
            } else if (idx == size - 1) {
                deleteLast();
            } else {
                Node prev = get(idx - 1);
                Node node = get(idx);
                Node next = get(idx + 1); //

                prev.next = next;
                next.prev = prev;
                node.next = null;
                node.prev = null;

                size -= 1;
            }
        }
    }

    public void deleteLast() {
        if (size <= 1) {
            deleteFirst();
            return;
        }
        Node secondLast = get(size - 2);
        secondLast.next.prev = null;
        secondLast.next = null;
        size -= 1;
    }

    private int find(int value) {
        Node node = head;
        int cnt = 0;
        while (node != null) {
            if (node.value == value) {
                return cnt;
            }
            cnt++;
            node = node.next;
        }
        return -1;
    }

    public Node get(int idx) {
        Node node = head;
        for (int i = 0; i < idx; i++) {
            node = node.next;
        }
        return node;
    }

    static class Node {
        private Node prev;
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
