package com.github.dsa.linear.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeBuiltInExample {

    public static void main(String[] args) {
        // Create a Deque
        Deque<Integer> deque = new ArrayDeque<>();

        // Add elements to both ends
        deque.addFirst(1);    // Front
        deque.addLast(2);     // Back
        deque.offerFirst(0);  // Front
        deque.offerLast(3);   // Back

        System.out.println("Deque: " + deque); // [0, 1, 2, 3]

        // Peek at elements
        System.out.println("First element: " + deque.peekFirst()); // 0
        System.out.println("Last element: " + deque.peekLast());   // 3

        // Remove elements from both ends
        System.out.println("Removed from front: " + deque.pollFirst()); // 0
        System.out.println("Removed from back: " + deque.pollLast());   // 3
        System.out.println("After removals: " + deque); // [1, 2]

        // Use as stack (LIFO)
        deque.push(5);  // Adds to front
        System.out.println("After push: " + deque); // [5, 1, 2]
        System.out.println("Popped: " + deque.pop()); // 5 (removes from front)

        // Use as queue (FIFO)
        deque.offer(6);  // Adds to back
        System.out.println("After offer: " + deque); // [1, 2, 6]
        System.out.println("Polled: " + deque.poll()); // 1 (removes from front)
    }
}