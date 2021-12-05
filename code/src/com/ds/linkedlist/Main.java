package com.ds.linkedlist;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to LinkedList world!");

        LinkedList ll = new LinkedList();
        ll.insertFirst(12);
        ll.insertFirst(16);
        ll.insertRecursively(15,0);
        ll.insertRecursively(16,1);
        ll.insertRecursively(18,1);
        ll.display();

        ll.reverse();
        ll.insertLast(133);
        ll.display();
    }
}
