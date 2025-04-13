package com.github.dsa.temp;

import java.util.PriorityQueue;

class User implements Comparable<User> {
    int priority;
    String name;

    public User(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public int compareTo(User other) {
        return Integer.compare(this.priority, other.priority); // Lower priority first
    }

    @Override
    public String toString() {
        return "User{priority=" + priority + ", name='" + name + "'}";
    }
}

public class LoadUsers {

    public static void main(String[] args) {
        PriorityQueue<User> userQueue = new PriorityQueue<>();

        // Simulate loading 1 million users (replace with actual data loading)
        for (int i = 0; i < 1_000_000; i++) {
            int priority = (int) (Math.random() * 99) + 1; // Random priority between 1 and 100
            String name = "User_" + i;
            System.out.println("Adding user: " + name + " with priority: " + priority);
            userQueue.offer(new User(priority, name));
        }

        // Process users (example: print the first 10)
        for (int i = 0; i < 10 && !userQueue.isEmpty(); i++) {
            System.out.println(userQueue.poll());
        }
    }
}
