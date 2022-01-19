package com.algos.sorting;

public class QuickSort {
    public int[] sort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
        return arr;
    }

    // O(nlog(n)) - Best/Avg case time complexity
    // O(n^2) - Worst case time complexity
    private static void quickSortHelper(int[] arr, int startIdx, int endIdx) {
        if(startIdx >= endIdx) {
            return;
        }
        int pivotIdx = startIdx;
        int leftIdx = startIdx + 1;
        int rightIdx = endIdx;

        while(leftIdx <= rightIdx) {

            if(arr[pivotIdx] < arr[leftIdx] && arr[pivotIdx] > arr[rightIdx]) {
                swap(leftIdx, rightIdx, arr);
            }

            if(arr[pivotIdx] >= arr[leftIdx]) {
                leftIdx++;
            }

            if(arr[pivotIdx] <= arr[rightIdx]) {
                rightIdx--;
            }
        }
        swap(pivotIdx, rightIdx, arr);

        boolean leftSubArrayIsSmaller = rightIdx - 1 - startIdx < endIdx - (rightIdx + 1);
        if(leftSubArrayIsSmaller) {
            quickSortHelper(arr, startIdx, rightIdx - 1);
            quickSortHelper(arr, rightIdx + 1, endIdx);
        } else {
            quickSortHelper(arr, rightIdx + 1, endIdx);
            quickSortHelper(arr, startIdx, rightIdx - 1);
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
