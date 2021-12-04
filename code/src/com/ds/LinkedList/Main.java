package com.ds.LinkedList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to LinkedList world!");

        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insertLast(2);
        dll.insertFirst(12);
        dll.insertFirst(15);
        dll.insertFirst(14);
        dll.display();

        dll.insertAt(102, 2);
        dll.display();

        dll.insertAt(103, 3);
        dll.display();

        dll.deleteFirst();
        dll.display();

        dll.deleteLast();
        dll.display();

        dll.delete(103);
        dll.display();

    }
}
