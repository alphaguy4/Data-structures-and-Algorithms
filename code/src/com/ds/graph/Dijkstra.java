package com.ds.graph;

import java.util.*;

public class Dijkstra {

    // O(V^2 + E) time | O(V) space
    public int[] dijkstraAlgorithm(int start, int[][][] edges) {
        int totalVertices = edges.length;

        int [] minDistances = new int[edges.length];
        Arrays.fill(minDistances, Integer.MAX_VALUE);

        minDistances[start] = 0;

        Set<Integer> visited = new HashSet<Integer>();

        while(visited.size() < totalVertices) {
            int[] getVertexData = getVertexAndMinDistance(minDistances, visited);
            int vertex = getVertexData[0];
            int currentMinDistance = getVertexData[1];

            if(currentMinDistance == Integer.MAX_VALUE) {
                break;
            }

            visited.add(vertex);

            for(int[] edge: edges[vertex]) {
                int destination = edge[0];
                int distanceToDestination = edge[1];

                int newPathDistance = distanceToDestination + currentMinDistance;
                int currentDestinationDistance = minDistances[destination];

                if(newPathDistance < currentDestinationDistance) {
                    minDistances[destination] = newPathDistance;
                }
            }
        }

        int[] finalDistances = new int[minDistances.length];
        for(int i = 0; i < minDistances.length; i++) {
            finalDistances[i] = minDistances[i] == Integer.MAX_VALUE ? -1 : minDistances[i];
        }
        return finalDistances;
    }

    private int[] getVertexAndMinDistance(int[] distances, Set<Integer> visited) {
        int minDistance = Integer.MAX_VALUE;
        int vertex = -1;
        for(int i = 0; i < distances.length; i++) {
            int distance = distances[i];
            if(visited.contains(i)) {
                continue;
            }
            if(distance <= minDistance) {
                minDistance = distance;
                vertex = i;
            }
        }
        return new int[]{vertex, minDistance};
    }
}
