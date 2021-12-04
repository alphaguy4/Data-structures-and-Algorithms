package com.ds;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to LinkedList world!");

        LinkedList ll = new LinkedList();
        ll.insertFirst(12);
        ll.insertFirst(13);
        ll.insertFirst(14);
        ll.insertFirst(15);
        ll.printList();

//        ll.insertLast(11);
//        ll.insertLast(12);
//        ll.insertLast(15);
//        ll.printList();
//
//        ll.insertAt(13, 2);
//        ll.insertAt(14, 3);
//        ll.printList();
//
//        ll.deleteFirst();
//        ll.printList();
//
//        ll.deleteLast();
//        ll.printList();
//
//        ll.deleteLast();
//        ll.deleteFirst();
//        ll.printList();

        ll.insertAt(100, 2);
        ll.printList();


        ll.delete(13);
        ll.delete(12);
        ll.printList();

    }
}
