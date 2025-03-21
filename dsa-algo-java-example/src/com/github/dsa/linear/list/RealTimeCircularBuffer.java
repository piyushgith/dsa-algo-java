package com.github.dsa.linear.list;

public class RealTimeCircularBuffer {
    private int[] buffer;
    private int capacity;
    private int head;
    private int tail;
    private int size;

    public RealTimeCircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public void add(int data) {
        buffer[tail] = data;
        tail = (tail + 1) % capacity;
        if (size < capacity) size++;
        else head = (head + 1) % capacity; // Overwrite oldest
    }

    public int getLatest() {
        if (isEmpty()) throw new IllegalStateException("Buffer empty");
        return buffer[(tail - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printBuffer() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        int count = 0;
        int index = head;
        while (count < size) {
            System.out.print(buffer[index]);
            if (count < size - 1) System.out.print(", ");
            index = (index + 1) % capacity;
            count++;
        }
        System.out.println("]");
    }

    // Simulate real-time sensor data
    public static void main(String[] args) {
        RealTimeCircularBuffer sensorBuffer = new RealTimeCircularBuffer(3);

        // Simulate sensor readings
        int[] readings = {10, 20, 30, 40, 50};
        for (int reading : readings) {
            sensorBuffer.add(reading);
            System.out.println("Added " + reading + ":");
            sensorBuffer.printBuffer();
            System.out.println("Latest reading: " + sensorBuffer.getLatest());
        }
    }
}
