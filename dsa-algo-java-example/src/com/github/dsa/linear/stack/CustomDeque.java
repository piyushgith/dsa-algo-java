package com.github.dsa.linear.stack;

public class CustomDeque {
    // Node class for doubly linked list
    private class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    // Constructor
    public CustomDeque() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add to front
    public void addFirst(int element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    // Add to back
    public void addLast(int element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remove from front
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        int data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        } else {
            front.prev = null;
        }
        size--;
        return data;
    }

    // Remove from back
    public int removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        int data = rear.data;
        rear = rear.prev;
        if (rear == null) {
            front = null;
        } else {
            rear.next = null;
        }
        size--;
        return data;
    }

    // Peek at front
    public int peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return front.data;
    }

    // Peek at back
    public int peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return rear.data;
    }

    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get size
    public int size() {
        return size;
    }

    // ToString for printing
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node current = front;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // Main method to test
    public static void main(String[] args) {
        CustomDeque deque = new CustomDeque();

        // Add elements
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        deque.addLast(3);

        System.out.println("Deque: " + deque); // [0, 1, 2, 3]

        // Peek
        System.out.println("First: " + deque.peekFirst()); // 0
        System.out.println("Last: " + deque.peekLast());   // 3

        // Remove
        System.out.println("Removed first: " + deque.removeFirst()); // 0
        System.out.println("Removed last: " + deque.removeLast());   // 3
        System.out.println("After removals: " + deque); // [1, 2]

        // Use as stack
        deque.addFirst(5);
        System.out.println("After push: " + deque); // [5, 1, 2]
        System.out.println("Popped: " + deque.removeFirst()); // 5

        // Use as queue
        deque.addLast(6);
        System.out.println("After enqueue: " + deque); // [1, 2, 6]
        System.out.println("Dequeued: " + deque.removeFirst()); // 1
    }
}
