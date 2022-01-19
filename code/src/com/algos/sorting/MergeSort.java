package com.algos.sorting;

import java.util.Arrays;

public class MergeSort {


    // O(nlog(n)) Time | O(nlog(n)) space
    public static int[] mergeSort(int[] array) {
        if(array.length <= 1) {
            return array;
        }

        int middleIdx = array.length / 2;
        int[] leftSubArray = Arrays.copyOfRange(array, 0, middleIdx);
        int[] rightSubArray = Arrays.copyOfRange(array, middleIdx, array.length);

        return mergeSortedArrays(mergeSort(leftSubArray), mergeSort(rightSubArray));
    }

    private static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
        int[] sortedArray = new int[leftHalf.length + rightHalf.length];
        int k = 0;
        int i = 0;
        int j = 0;

        while(i < leftHalf.length && j < rightHalf.length) {
            if(leftHalf[i] <= rightHalf[j]) {
                sortedArray[k++] = leftHalf[i++];
            } else {
                sortedArray[k++] = rightHalf[j++];
            }
        }

        while(i < leftHalf.length) {
            sortedArray[k++] = leftHalf[i++];
        }

        while(j < rightHalf.length) {
            sortedArray[k++] = rightHalf[j++];
        }

        return sortedArray;

    }
}
