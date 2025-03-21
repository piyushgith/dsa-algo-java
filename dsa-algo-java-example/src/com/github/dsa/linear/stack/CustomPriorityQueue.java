package com.github.dsa.linear.stack;

public class CustomPriorityQueue {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor
    public CustomPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    // Add element to priority queue
    public void offer(int element) {
        if (size == capacity) {
            resize();
        }

        heap[size] = element;
        siftUp(size);
        size++;
    }

    // Remove and return minimum element
    public int poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty");
        }

        int min = heap[0];
        heap[0] = heap[--size];
        heap[size] = 0; // Clear reference
        if (size > 0) {
            siftDown(0);
        }
        return min;
    }

    // Peek at minimum element without removing
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty");
        }
        return heap[0];
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get current size
    public int size() {
        return size;
    }

    // Move element up to maintain heap property
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[parent] <= heap[index]) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    // Move element down to maintain heap property
    private void siftDown(int index) {
        while (true) {
            int minIndex = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && heap[left] < heap[minIndex]) {
                minIndex = left;
            }
            if (right < size && heap[right] < heap[minIndex]) {
                minIndex = right;
            }

            if (minIndex == index) {
                break;
            }

            swap(index, minIndex);
            index = minIndex;
        }
    }

    // Swap two elements in heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Double capacity when full
    private void resize() {
        int[] newHeap = new int[capacity * 2];
        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        capacity *= 2;
    }

    // Main method to test
    public static void main(String[] args) {
        CustomPriorityQueue pq = new CustomPriorityQueue(4);

        // Adding elements
        pq.offer(5);
        pq.offer(2);
        pq.offer(7);
        pq.offer(1);

        System.out.println("Size: " + pq.size()); // 4
        System.out.println("Minimum element: " + pq.peek()); // 1

        // Remove and print all elements
        System.out.println("Removing elements:");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();

        // Test resize
        pq.offer(3);
        pq.offer(8);
        pq.offer(4);
        pq.offer(6);
        pq.offer(2); // Triggers resize

        System.out.println("After adding more elements:");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
    }
}