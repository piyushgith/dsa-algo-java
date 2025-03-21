package com.github.dsa.linear.list;

public class DoublyLinkedList {
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

    private Node head;
    private Node tail;
    private int size;

    // Constructor
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add element at the beginning
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Add element at the end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Add element at specific index
    public void addAtIndex(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    // Remove first element
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        int data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
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
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return data;
    }

    // Get element at index
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
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
        Node current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(", ");
            current = current.next;
        }
        System.out.println("]");
    }

    // Print list backward
    public void printListBackward() {
        Node current = tail;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) System.out.print(", ");
            current = current.prev;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.addAtIndex(4, 1);

        System.out.println("Forward:");
        list.printListForward(); // [1, 4, 2, 3]
        System.out.println("Backward:");
        list.printListBackward(); // [3, 2, 4, 1]

        System.out.println("Size: " + list.size()); // 4
        System.out.println("Element at index 2: " + list.get(2)); // 2

        System.out.println("Removed first: " + list.removeFirst()); // 1
        System.out.println("Removed last: " + list.removeLast()); // 3
        System.out.println("After removals (forward):");
        list.printListForward(); // [4, 2]
    }
}