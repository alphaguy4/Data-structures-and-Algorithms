package com.algos.sorting;


/*
* Insert element at idx to correct position
* */
public class InsertionSort {

    // O(n^2) time | O(1) space
    public static void sort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            for(int j = i; j > -1; j--) {
                if(arr[j] < arr[j-1]) {
                    swap(i, j, arr);
                }
            }
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 11, 4, 11, 11,  3, 8, 12, -23, 55, 12, 21};
        HeapSort.sort(arr);
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();

    }
}
