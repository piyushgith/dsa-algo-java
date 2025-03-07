package com.github.dsa.linear.stack;

import java.util.Stack;

/*
Key Methods:
            push(E item): Adds item to top

            pop(): Removes and returns top item

            peek(): Returns top item without removing

            isEmpty(): Checks if stack is empty

            size(): Returns number of elements
*/
public class StackExample {
    public static void main(String[] args) {
        // Create a Stack
        Stack<Integer> stack = new Stack<>();

        // Push elements (add to top)
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Stack: " + stack); // [1, 2, 3]

        // Peek at top element without removing
        System.out.println("Top element: " + stack.peek()); // 3

        // Pop elements (remove from top)
        System.out.println("Popped: " + stack.pop()); // 3
        System.out.println("After pop: " + stack); // [1, 2]

        // Check if empty
        System.out.println("Is empty? " + stack.isEmpty()); // false

        // Get size
        System.out.println("Size: " + stack.size()); // 2
    }
}
