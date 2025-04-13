package com.github.dsa.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        //printNos(5);
//        System.out.println(func(5));
//        System.out.println();
//        func1(5,0);

        //int arr[] = {5, 4, 3, 2, 1};
        //reverseArray(arr, 0, arr.length - 1);
        //Arrays.stream(arr).forEach(element -> System.out.print(element + " "));

        //frequencyCount(new int[]{2, 3, 2, 3, 5});
        //frequencyCount(new int[]{3, 3, 3, 3});

        getSecondLargest(new int[]{10, 10, 10});

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

    public static int func(int n) {
        // Base Condition.
        if (n == 0) {
            return 0;
        }
        // Problem broken down into 2 parts and then combined.
        return n + func(n - 1);
    }

    //Function to reverse array using recursion
    static void reverseArray(int arr[], int start, int end) {
        if (start < end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            reverseArray(arr, start + 1, end - 1);
        }
    }

    public static List<Integer> frequencyCount(int[] arr) {
        List<Integer> list = new LinkedList<>();
        int count = 0;
        for (int i = 1; i <= arr.length; i++) {
            count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (i == arr[j]) {
                    count++;
                }
            }
            System.out.print(count + ",");
            list.add(count);
        }
        return list;
    }

    public static int getSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE; // Or throw an exception
        }

        int largest = Math.max(arr[0], arr[1]);
        int secondLargest = Math.min(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) { // check for != largest
                secondLargest = arr[i];
            }
        }
        return arr[0] == arr[1] ? -1 : secondLargest;
    }


}
