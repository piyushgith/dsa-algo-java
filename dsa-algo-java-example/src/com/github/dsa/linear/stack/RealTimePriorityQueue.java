package com.github.dsa.linear.stack;

public class RealTimePriorityQueue {
    private Task[] heap;
    private int size;
    private int capacity;

    static class Task {
        int priority; // Lower value = higher priority
        String name;

        Task(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }
    }

    public RealTimePriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new Task[capacity];
        this.size = 0;
    }

    public void add(int priority, String name) {
        if (size == capacity) throw new IllegalStateException("Queue full");
        Task task = new Task(priority, name);
        heap[size] = task;
        siftUp(size);
        size++;
    }

    public Task poll() {
        if (isEmpty()) throw new IllegalStateException("Queue empty");
        Task min = heap[0];
        heap[0] = heap[--size];
        heap[size] = null;
        if (size > 0) siftDown(0);
        return min;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[parent].priority <= heap[index].priority) break;
            swap(parent, index);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int minIndex = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            if (left < size && heap[left].priority < heap[minIndex].priority) minIndex = left;
            if (right < size && heap[right].priority < heap[minIndex].priority) minIndex = right;
            if (minIndex == index) break;
            swap(index, minIndex);
            index = minIndex;
        }
    }

    private void swap(int i, int j) {
        Task temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Simulate real-time task scheduling
    public static void main(String[] args) {
        RealTimePriorityQueue pq = new RealTimePriorityQueue(5);

        pq.add(3, "Low priority task");
        pq.add(1, "High priority task");
        pq.add(2, "Medium priority task");
        pq.add(0, "Critical task");

        System.out.println("Processing tasks in priority order:");
        while (!pq.isEmpty()) {
            Task task = pq.poll();
            System.out.println("Priority " + task.priority + ": " + task.name);
        }
    }
}
