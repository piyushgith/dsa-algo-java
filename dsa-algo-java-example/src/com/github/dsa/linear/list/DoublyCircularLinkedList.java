package com.github.dsa.linear.list;

public class DoublyCircularLinkedList {
    // Node class
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

    private Node head;
    private Node tail;
    private int size;

    // Constructor
    public DoublyCircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add element at the beginning
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
            head.next = head;
            head.prev = head;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }

    // Add element at the end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
            head.next = head;
            head.prev = head;
        } else {
            newNode.prev = tail;
            newNode.next = head;
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
        }
        size++;
    }

    // Remove first element
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        int data = head.data;
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        size--;
        return data;
    }

    // Remove last element
    public int removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        int data = tail.data;
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        size--;
        return data;
    }

    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get size
    public int size() {
        return size;
    }

    // Print list forward
    public void printListForward() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        Node current = head;
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(current.data);
            if (i < size - 1) System.out.print(", ");
            current = current.next;
        }
        System.out.println("]");
    }

    // Print list backward
    public void printListBackward() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        Node current = tail;
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(current.data);
            if (i < size - 1) System.out.print(", ");
            current = current.prev;
        }
        System.out.println("]");
    }

    // Rotate forward
    public void rotateForward() {
        if (!isEmpty()) {
            head = head.next;
            tail = tail.next;
        }
    }

    // Rotate backward
    public void rotateBackward() {
        if (!isEmpty()) {
            head = head.prev;
            tail = tail.prev;
        }
    }

    public static void main(String[] args) {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();

        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(0);

        System.out.println("Forward:");
        list.printListForward(); // [0, 1, 2, 3]
        System.out.println("Backward:");
        list.printListBackward(); // [3, 2, 1, 0]

        System.out.println("Size: " + list.size()); // 4

        list.rotateForward();
        System.out.println("After forward rotation:");
        list.printListForward(); // [1, 2, 3, 0]

        list.rotateBackward();
        System.out.println("After backward rotation:");
        list.printListForward(); // [0, 1, 2, 3]

        System.out.println("Removed first: " + list.removeFirst()); // 0
        System.out.println("Removed last: " + list.removeLast()); // 3
        System.out.println("After removals:");
        list.printListForward(); // [1, 2]
    }
}
