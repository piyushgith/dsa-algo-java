package com.github.dsa.sorting;

public class BubbleSortExample {
    /**
     * Concept: Repeatedly steps through the list, compares adjacent elements and swaps them if they're in the wrong order
     *
     * Time Complexity: O(n²) worst/average, O(n) best
     *
     * Space Complexity: O(1)
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
