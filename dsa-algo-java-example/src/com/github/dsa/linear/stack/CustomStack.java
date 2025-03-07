package com.github.dsa.linear.stack;

public class CustomStack {
    private int[] array;
    private int top;
    private int capacity;

    // Constructor
    public CustomStack(int size) {
        array = new int[size];
        capacity = size;
        top = -1; // Stack is empty initially
    }

    // Push element to stack
    public void push(int item) {
        if (isFull()) {
            resize();
        }
        array[++top] = item;
    }

    // Pop element from stack
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int item = array[top];
        array[top--] = 0; // Optional: clear reference
        return item;
    }

    // Peek at top element
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Get current size
    public int size() {
        return top + 1;
    }

    // Double the array size when full
    private void resize() {
        int[] newArray = new int[capacity * 2];
        for (int i = 0; i <= top; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity *= 2;
    }

    // Main method to test
    public static void main(String[] args) {
        CustomStack stack = new CustomStack(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Size: " + stack.size()); // 3

        stack.push(4); // Will trigger resize
        System.out.println("After resize, size: " + stack.size()); // 4

        System.out.println("Top element: " + stack.peek()); // 4

        System.out.println("Popped: " + stack.pop()); // 4
        System.out.println("Popped: " + stack.pop()); // 3
        System.out.println("Size: " + stack.size()); // 2
    }
}
