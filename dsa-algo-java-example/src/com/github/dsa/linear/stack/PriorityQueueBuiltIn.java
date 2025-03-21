package com.github.dsa.linear.stack;

import java.util.PriorityQueue;

public class PriorityQueueBuiltIn {
    public static void main(String[] args) {
        // Create a min-heap Priority Queue (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Adding elements
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(7);
        minHeap.offer(1);

        System.out.println("Min Heap Priority Queue: " + minHeap);

        // Remove and print elements in order
        System.out.println("Removing elements:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // poll() removes and returns min element
        }
        System.out.println();

        // Create a max-heap Priority Queue using comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Adding elements
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(7);
        maxHeap.offer(1);

        System.out.println("Max Heap Priority Queue: " + maxHeap);

        // Remove and print elements in order
        System.out.println("Removing elements:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
    }
}
