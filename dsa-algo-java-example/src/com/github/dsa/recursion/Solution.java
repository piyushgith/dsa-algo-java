package com.github.dsa.recursion;

public class Solution {
    public static void main(String[] args) {
        //printNos(5);
        System.out.println(func(5));
        System.out.println();
        func1(5,0);
    }

    public static void printNos(int n) {
        if (n > 0) {
            printNos(n - 1);
            System.out.print(n + " ");
        }
    }

    //recursive call to add all numbers from 1 to n.
    public static void func1(int i, int sum) {
        // Base Condition.
        if (i < 1) {
            System.out.println(sum);
            return;
        }
        // Function call to increment sum by i till i decrements to 1.
        func1(i - 1, sum + i);
    }

    public static int func(int n){
        // Base Condition.
        if(n == 0){
            return 0;
        }
        // Problem broken down into 2 parts and then combined.
        return n + func(n-1);
    }

}
