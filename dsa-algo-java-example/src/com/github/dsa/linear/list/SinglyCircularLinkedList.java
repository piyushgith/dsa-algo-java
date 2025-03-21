package com.github.dsa.linear.list;

public class SinglyCircularLinkedList {
    // Node class
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // Constructor
    public SinglyCircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add element at the beginning
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
            tail.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
        size++;
    }

    // Add element at the end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
            tail.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
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
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = head;
            tail = current;
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

    // Print list
    public void printList() {
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

    // Rotate list (move head forward)
    public void rotate() {
        if (!isEmpty()) {
            head = head.next;
            tail = tail.next;
        }
    }

    public static void main(String[] args) {
        SinglyCircularLinkedList list = new SinglyCircularLinkedList();

        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(0);

        System.out.println("Original list:");
        list.printList(); // [0, 1, 2, 3]

        System.out.println("Size: " + list.size()); // 4

        list.rotate();
        System.out.println("After rotation:");
        list.printList(); // [1, 2, 3, 0]

        System.out.println("Removed first: " + list.removeFirst()); // 1
        System.out.println("Removed last: " + list.removeLast()); // 0
        System.out.println("After removals:");
        list.printList(); // [2, 3]
    }
}