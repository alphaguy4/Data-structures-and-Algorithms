package com.algos.sorting;

/*
* At every step select the min element in the array
* Once the iteration is over keep it at correct position
* */
public class SelectionSort {


    // O(n^2) time | O(1) space
    // n is the size of array
    public static void sort(int[] arr) {

        for(int i = 0; i < arr.length; i++) {
            int minElementIdx = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[minElementIdx]) {
                    minElementIdx = j;
                }
            }

            swap(i, minElementIdx, arr);
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
