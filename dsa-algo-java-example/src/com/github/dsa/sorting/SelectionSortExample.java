package com.github.dsa.sorting;

public class SelectionSortExample {

    /**
     * Concept: Repeatedly finds the minimum element from unsorted part and puts it at the beginning
     *
     * Time Complexity: O(n²) worst/average/best
     *
     * Space Complexity: O(1)
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap elements
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
}
