package com.ds.graph;

import java.util.*;

public class Dijkstra2 {

    // O((V + E) * log(V)) time | O(V) space
    public int[] dijkstraAlgorithm(int start, int[][][] edges) {
        int numberOfVertices = edges.length;
        int[] minDistances = new int[edges.length];
        Arrays.fill(minDistances, Integer.MAX_VALUE);

        minDistances[start] = 0;

        List<Item> minDistancesPairs = new ArrayList<Item>();
        for(int i = 0; i < numberOfVertices; i++) {
            Item item = new Item(i, Integer.MAX_VALUE);
            minDistancesPairs.add(item);
        }

        MinHeap minDistancesHeap = new MinHeap(minDistancesPairs);

        minDistancesHeap.update(start, 0);

        while(!minDistancesHeap.isEmpty()) {
            // log(V) time
            Item heapItem = minDistancesHeap.remove();
            int vertex = heapItem.vertex;
            int currentMinDistance = heapItem.distance;

            // Imp stuff keep in mind
            if(currentMinDistance == Integer.MAX_VALUE) {
                break;
            }

            for(int[] edge: edges[vertex]) {
                int destination = edge[0];
                int distanceToDestination = edge[1];

                int newPathDistance = currentMinDistance + distanceToDestination;
                int currentDistanceToDestination = minDistances[destination];

                if(newPathDistance < currentDistanceToDestination) {
                    minDistances[destination] = newPathDistance;
                    // log(V) time
                    minDistancesHeap.update(destination, newPathDistance);
                }
            }

        }

        int[] minDistancesFinal = new int[numberOfVertices];
        for(int i = 0; i < numberOfVertices; i++) {
            int distance = minDistances[i];
            minDistancesFinal[i] = distance == Integer.MAX_VALUE ? -1 : distance;
        }
        return minDistancesFinal;
    }

    static class Item {
        int vertex;
        int distance;

        public Item(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    static class MinHeap {
        Map<Integer, Integer> vertexMap =
                new HashMap<Integer, Integer>();
        List<Item> heap = new ArrayList<Item>();

        public MinHeap(List<Item> array) {
            for(Item item: array) {
                vertexMap.put(item.vertex, item.vertex);
            }
            heap = buildHeap(array);
        }

        private Item remove() {

            Item itemToRemove = heap.get(0);
            int vertex = itemToRemove.vertex;
            int distance = itemToRemove.distance;

            swap(0, heap.size() - 1, heap);

            heap.remove(heap.size() - 1);
            vertexMap.remove(vertex);

            siftDown(0, heap.size() - 1, heap);

            return new Item(vertex, distance);
        }

        private boolean isEmpty(){
            return heap.size() == 0;
        }

        // O(n) time
        private List<Item> buildHeap(List<Item> array) {
            int parentIdx = (array.size() - 2) / 2;
            for(int currentIdx = parentIdx; currentIdx >= 0; currentIdx--) {
                siftDown(currentIdx, array.size() - 1, array);
            }
            return array;
        }

        // O(log(n)) time
        private void siftDown(int currentIdx, int endIdx, List<Item> heap) {
            int childOneIdx = 2 * currentIdx + 1;

            while(childOneIdx <= endIdx) {
                int childTwoIdx = 2 * currentIdx + 2 <= endIdx ? 2 * currentIdx + 2 : -1;
                int idxToSwap = childOneIdx;
                if(childTwoIdx != -1) {
                    if(heap.get(childTwoIdx).distance < heap.get(childOneIdx).distance) {
                        idxToSwap = childTwoIdx;
                    }
                }

                if(heap.get(idxToSwap).distance < heap.get(currentIdx).distance) {
                    swap(idxToSwap, currentIdx, heap);
                    currentIdx = idxToSwap;
                    childOneIdx = 2 * currentIdx + 1;
                } else {
                    return;
                }
            }
        }

        // O(log(n))
        private void update(int vertex, int distance) {
            int pos = vertexMap.get(vertex);
            heap.set(pos, new Item(vertex, distance));
            siftUp(pos);
        }

        private void siftUp(int currentIdx) {
            int parentIdx = (currentIdx - 1 ) / 2;
            while(currentIdx > 0) {
                if(heap.get(currentIdx).distance < heap.get(parentIdx).distance) {
                    swap(currentIdx, parentIdx, heap);
                    currentIdx = parentIdx;
                    parentIdx = (currentIdx - 1) / 2;
                } else {
                    return;
                }
            }
        }

        private void swap(int i, int j, List<Item> array) {
            vertexMap.put(array.get(i).vertex, j);
            vertexMap.put(array.get(j).vertex, i);
            Item tmp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, tmp);
        }
    }
}
