package com.github.dsa.sorting;

/**
 * Key Characteristics:
 * Bubble Sort: Simple but inefficient, good for small datasets
 *
 * Selection Sort: Performs well on small lists, minimizes swaps
 *
 * Insertion Sort: Efficient for small/partially sorted arrays, adaptive
 *
 * Merge Sort: Stable, guaranteed O(n log n), requires extra space
 *
 * Quick Sort: Fast in practice, in-place sorting, not stable
 *
 * Heap Sort: In-place, not stable, good when worst-case O(n log n) is needed
 */
public class SortingAlgoExample {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        // Call any sorting method, e.g.:
        //BubbleSortExample.bubbleSort(arr);


        HeapSortExample.heapSort(arr);

        MergeSortExample.mergeSort(arr,0, arr.length-1);
        // Print sorted array
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
