package com.smartwaste.algorithm;

import com.smartwaste.model.Bin;
import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {

    private final List<Bin> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    // Add a bin to the priority queue
    public void add(Bin bin) {
        heap.add(bin);
        heapifyUp(heap.size() - 1);
    }

    // Remove and return the highest-priority bin
    public Bin poll() {
        if (heap.isEmpty()) {
            return null;
        }

        Bin highestPriority = heap.get(0);

        Bin last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return highestPriority;
    }

    // View the highest-priority bin
    public Bin peek() {
        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // Move a newly added bin upward
    private void heapifyUp(int index) {

        while (index > 0) {

            int parent = (index - 1) / 2;

            if (heap.get(parent).getPredictedFill()
                    >= heap.get(index).getPredictedFill()) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    // Move a replaced bin downward
    private void heapifyDown(int index) {

        while (true) {

            int left = 2 * index + 1;
            int right = 2 * index + 2;

            int largest = index;

            if (left < heap.size()
                    && heap.get(left).getPredictedFill()
                    > heap.get(largest).getPredictedFill()) {
                largest = left;
            }

            if (right < heap.size()
                    && heap.get(right).getPredictedFill()
                    > heap.get(largest).getPredictedFill()) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }
    }

    private void swap(int first, int second) {

        Bin temp = heap.get(first);

        heap.set(first, heap.get(second));

        heap.set(second, temp);
    }

    // Display bins in priority order
    public void displayQueue() {

        System.out.println("Collection Priority:");

        while (!heap.isEmpty()) {

            Bin bin = poll();

            System.out.println(
                    bin.getBinId()
                    + " | "
                    + bin.getLocation()
                    + " | Predicted Fill: "
                    + bin.getPredictedFill()
                    + "%"
                    + " | Status: "
                    + bin.getStatus()
            );
        }
    }
}