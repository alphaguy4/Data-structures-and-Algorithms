package com.algos;

import java.util.ArrayList;
import java.util.Arrays;

public class Dp {

    public static long[] fib = new long[1000];
    public static long[][] grid = new long[100][100];
    public static int[] sumArray = new int[100];
    static  {
        Arrays.fill(fib, -1);

        for(int i = 1; i < 100; i++) {
            Arrays.fill(grid[i], -1);
        }

        Arrays.fill(sumArray, -1);
    }

    // Time complexityO(n)
    // Space complexity O(n + n) -> O(n)
    static long fibo(int n) {
        if(fib[n] != -1) {
            return fib[n];
        }
        if(n <= 2) {
            return 1;
        }
        return (fib[n] = fibo(n-1) + fibo(n-2));
    }

    // Time complexity O(n*m)
    // Space complexity O(m+n)
    static long gridTraveller(int n, int m) {
        if(grid[n][m] != -1) return grid[n][m];
        if(m == 1 && n == 1) {
            return 1;
        }
        if(m == 0 || n == 0) {
            return 0;
        }
        return (grid[m][n] = gridTraveller(n - 1, m) + gridTraveller(n, m - 1));
    }

    // Given a target and an array
    // Find if elements in array can sum to target
    // n - total number of elements
    // m - target
    // Time complexity - O(m^n)
    // Space complexity - O(m*n)
    // Memoized solution time complexity - O(mxn)
    static boolean canSum(int[] nums, int target) {

        if(target == 0) {
            return true;
        }
        if(target < 0) {
            return false;
        }

        if(sumArray[target]  != -1) {
            return (sumArray[target] == 1);
        }

        boolean isSumPossible = false;
        for(int num: nums) {
            int remainder = target - num;
            isSumPossible = (isSumPossible | canSum(nums, remainder));
        }
        sumArray[target] = isSumPossible ? 1 : 0;
        return isSumPossible;
    }

    // m = targetSum
    // n = nums.length

    // time complexity - O(n ^ m)
    // Space complexity O(2m + n )
    static ArrayList<Integer> howSum(int[] nums, int target) {
        if(target == 0) {
            return new ArrayList<Integer>(){};
        }
        if(target < 0) {
            return null;
        }

        for(int num: nums) {
            int remainder = target - num;
            ArrayList<Integer> remainderResult =  howSum(nums, remainder);
            if(remainderResult != null) {
                remainderResult.add(num);
                return remainderResult;
            }
        }
        return null;
    }


}
