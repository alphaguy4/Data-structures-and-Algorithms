package com.algos;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        System.out.println(fiboRecursive(3));
//        System.out.println(fiboRecursive(40));
//        System.out.println(Dp.fibo(30));
//        System.out.println(Dp.gridTraveller(18,18));

//        System.out.println(Dp.canSum(new int[]{3,4,7}, 1 ));
        //System.out.println(Dp.howSum(new int[]{7, 14}, 300));


    }

    static int fiboRecursive(int n) {
        if(n <= 2) {
            return 1;
        }
        return fiboRecursive(n-1) + fiboRecursive(n-2);
    }

}
