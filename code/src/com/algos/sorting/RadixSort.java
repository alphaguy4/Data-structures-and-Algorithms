package com.algos.sorting;

import java.util.stream.IntStream;

/*
* Only applicable for array with positive integer
* Uses counting Sort
* */
public class RadixSort {

    // O(d * (n + b)) - Time
    // O(n + b) - Space
    // n is number of elements in the array
    // b is the base of the number
    // d is the total number of digit in maxNumber
    public static int[] sort(int [] array) {
        if(array.length == 0) {
            return array;
        }

        int maxNumber = IntStream.of(array).max().getAsInt();
        int digit = 0;
        while( maxNumber / Math.pow(10, digit) > 0) {
            countSort(array, digit);
            digit++;
        }

        return array;
    }

    private static void countSort(int[] array, int digit) {
        int[] sortedArray = new int[array.length];
        int[] countArray = new int[10];

        // populate the countArray with digit
        for(int num: array) {
            int countIdx = (int)(num / Math.pow(10, digit)) % 10;
            countArray[countIdx] += 1;
        }

        // preSum
        for(int idx = 1; idx < countArray.length; idx++) {
            countArray[idx] += countArray[idx - 1];
        }

        for(int idx = array.length - 1; idx >= 0; idx--) {
            int countIdx = (int)(array[idx] / Math.pow(10, digit)) % 10;
            countArray[countIdx] -= 1;

            int sortedIdx = countArray[countIdx];
            sortedArray[sortedIdx] = array[idx];
        }

        for(int i = 0; i < sortedArray.length; i++) {
            array[i] = sortedArray[i];
        }
    }
    public static void main(String[] args) {
        int[] arr = {133, 32, 64, 1992};
        RadixSort.sort(arr);
        for(int num: arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
