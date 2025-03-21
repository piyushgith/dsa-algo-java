package com.github.dsa.linear.list;

import java.util.Random;

public class RealTimeSkipList {
    private class Node {
        int value;
        Node[] next; // Array of next pointers for each level
        int level;

        Node(int value, int level) {
            this.value = value;
            this.level = level;
            this.next = new Node[level + 1];
        }
    }

    private Node head;
    private int maxLevel;
    private int level;
    private Random rand;

    public RealTimeSkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        this.level = 0;
        this.head = new Node(Integer.MIN_VALUE, maxLevel);
        this.rand = new Random();
    }

    private int randomLevel() {
        int lvl = 0;
        while (rand.nextBoolean() && lvl < maxLevel) lvl++;
        return lvl;
    }

    public void insert(int value) {
        Node[] update = new Node[maxLevel + 1];
        Node current = head;

        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }

        int newLevel = randomLevel();
        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            level = newLevel;
        }

        Node newNode = new Node(value, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    public boolean contains(int value) {
        Node current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
        }
        current = current.next[0];
        return current != null && current.value == value;
    }

    public void printList() {
        Node current = head.next[0];
        System.out.print("[");
        while (current != null) {
            System.out.print(current.value);
            if (current.next[0] != null) System.out.print(", ");
            current = current.next[0];
        }
        System.out.println("]");
    }

    // Simulate real-time ordered data
    public static void main(String[] args) {
        RealTimeSkipList skipList = new RealTimeSkipList(3);

        skipList.insert(5);
        skipList.insert(2);
        skipList.insert(7);
        skipList.insert(1);

        System.out.println("Skip List contents:");
        skipList.printList(); // [1, 2, 5, 7]

        System.out.println("Contains 5: " + skipList.contains(5)); // true
        System.out.println("Contains 3: " + skipList.contains(3)); // false
    }
}