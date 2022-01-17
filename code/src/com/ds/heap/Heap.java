package com.ds.heap;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
* Implementing Min/Max Heap using a single implementation
* This program leverages java's BiFunction to choose btw MinHeap or MaxHeap
* */

public class Heap {
    private List<Integer> heap;
    private BiFunction<Integer, Integer, Boolean> compareFunc;
    int length;

    public Heap(int[] array, BiFunction<Integer, Integer, Boolean> func) {
        heap = IntStream.of(array).boxed().collect(Collectors.toList());
        compareFunc = func;
        length = array.length;
        buildHeap(heap);
    }

    // Time complexity
    // O(n) roughly using siftDown() method
    private void buildHeap(List<Integer> heap) {
        int lastParentIdx = (heap.size() - 2) / 2;
        for(int currentIdx = lastParentIdx; currentIdx >= 0; currentIdx--) {
            siftDown(currentIdx, heap.size() - 1, heap);
        }
    }

    public Integer peek(){
        return length > 0 ? heap.get(0) : null;
    }

    public void insert(int value) {
        heap.add(value);
        length++;
        siftUp(heap.size() - 1, heap);
    }

    public int remove() {
        swap(0, heap.size() - 1, heap);
        int valueToRemove = heap.get(heap.size() - 1);
        heap.remove(heap.size() - 1);
        length--;
        siftDown(0, heap.size() - 1, heap);
        return valueToRemove;
    }


    // Time - O(logn)
    private void siftUp(int currentIdx, List<Integer> heap) {
        int parentIdx = (currentIdx - 1) / 2;
        while(currentIdx > 0) {
            if(compareFunc.apply(heap.get(currentIdx), heap.get(parentIdx))) {
                swap(currentIdx, parentIdx, heap);
                currentIdx = parentIdx;
                parentIdx = (currentIdx - 1) / 2;
            } else {
                return;
            }
        }
    }

    // Time - O(logn)
    private void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
        int childOneIdx = 2 * currentIdx + 1;
        while(childOneIdx <= endIdx) {
            int childTwoIdx = 2 * currentIdx + 2 <= endIdx ? 2 * currentIdx + 2 : -1;
            int idxToSwap = childOneIdx;
            if(childTwoIdx != -1) {
                if(compareFunc.apply(heap.get(childTwoIdx), heap.get(childOneIdx))) {
                    idxToSwap = childTwoIdx;
                }
            }
            if(compareFunc.apply(heap.get(idxToSwap), heap.get(currentIdx))) {
                swap(currentIdx, idxToSwap, heap);
                currentIdx = idxToSwap;
                childOneIdx = 2 * currentIdx + 1;
            } else {
                return;
            }
        }
    }

    private void swap(int i, int j, List<Integer> array) {
        int tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

    public static boolean MIN_HEAP_FUNC(int a, int b) {
        return a < b;
    }

    public static boolean MAX_HEAP_FUNC(int a, int b) {
        return a > b;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 4, 5, 9, 11, 16, 100, 50, 45};

//        Heap minHeap = new Heap(arr, Heap::MIN_HEAP_FUNC);

//        System.out.print("MinHeap :: ");
//        for(int i = 0; i < minHeap.heap.size(); i++) {
//            System.out.print(minHeap.heap.get(i) + " ");
//        }
//        System.out.println();

        Heap maxHeap = new Heap(arr, Heap::MAX_HEAP_FUNC);
        maxHeap.insert(55);

        System.out.println("Remove :: " + maxHeap.remove());
        System.out.println("Peek :: " + maxHeap.peek());


    }
}


