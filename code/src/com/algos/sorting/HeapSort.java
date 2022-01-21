package com.algos.sorting;

public class HeapSort {

    // O(nlog(n)) - Time
    // O(1) - space
    public static void sort(int[] arr) {
        buildMaxHeap(arr);
        for(int idx = arr.length - 1; idx >=0; idx--) {
            swap(0, idx, arr);
            siftDown(0, idx - 1, arr);
        }
    }

    // O(n) time to build heap, roughly using shiftDown
    private static void buildMaxHeap(int[] array) {
        int parentIdx = (array.length - 2) / 2;
        for(int currentIdx = parentIdx; currentIdx >= 0; currentIdx--) {
            siftDown(currentIdx, array.length - 1, array);
        }
    }

    private static void siftDown(int currentIdx, int endIdx, int[] heap) {
        int childOneIdx = 2 * currentIdx + 1;
        while(childOneIdx <= endIdx) {
            int childTwoIdx = 2 * currentIdx + 2 <= endIdx ? 2 * currentIdx + 2 : -1;
            int idxToSwap = childOneIdx;
            if(childTwoIdx != -1) {
                if(heap[childTwoIdx] > heap[childOneIdx]) {
                    idxToSwap = childTwoIdx;
                }
            }
            if(heap[idxToSwap] > heap[currentIdx]) {
                swap(idxToSwap, currentIdx, heap);
                currentIdx = idxToSwap;
                childOneIdx = 2 * currentIdx + 1;
            } else {
                return;
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
