package com.algos.sorting;

/*
* Just like bubble
* */
public class BubbleSort {

    // O(n ^ 2) Time  | O(1) Space
    // n is the size of the array
    public static void sort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length - i - 1; j++) {
                if(arr[i] < arr[j]) {
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
