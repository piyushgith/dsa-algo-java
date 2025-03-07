package com.github.dsa.sorting;

public class InsertionSortExample {

    /**
     * Concept: Builds the sorted array one item at a time by inserting elements into their proper position
     *
     * Time Complexity: O(n²) worst/average, O(n) best
     *
     * Space Complexity: O(1)
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
