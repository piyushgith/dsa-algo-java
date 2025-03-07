package com.github.dsa.sorting;

public class OptimizedMergeSort {
    private static final int CUTOFF = 7; // Cutoff point for insertion sort

    // Main merge sort method
    public static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length]; // Single auxiliary array
        sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] aux, int low, int high) {
        // Switch to insertion sort for small subarrays
        if (high <= low + CUTOFF - 1) {
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {
            int mid = low + (high - low) / 2; // Avoid integer overflow
            sort(arr, aux, low, mid);
            sort(arr, aux, mid + 1, high);

            // Skip merge if already sorted
            if (arr[mid] <= arr[mid + 1]) {
                return;
            }

            merge(arr, aux, low, mid, high);
        }
    }

    private static void merge(int[] arr, int[] aux, int low, int mid, int high) {
        // Copy to auxiliary array only once
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }

        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > high) {
                arr[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    // Insertion sort for small subarrays
    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Main method to test
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array:");
        printArray(arr);

        mergeSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}