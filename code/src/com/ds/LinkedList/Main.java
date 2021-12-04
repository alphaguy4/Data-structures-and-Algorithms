package com.ds.LinkedList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to LinkedList world!");

        CircularLinkedList cll = new CircularLinkedList();
        cll.insert(12);
        cll.insert(13);
        cll.insert(14);
        cll.insert(15);
        cll.delete(14);
        cll.display();

    }
}
