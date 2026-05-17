package com.nishchay.dsa.d02mergek;

import java.util.*;

/*
 * ================================ Merge k sorted streams Using Min Heap =======================================
 *
 * Suppose you have K sorted data streams (or sorted iterators) too large to fit in memory.
 * How would you design an iterator to return elements in global sorted order while minimizing memory usage and supporting lazy loading
 *
 * input : [[1, 4, 5,......],[1, 3, 4,.......],[6, 7, 9......]]
 * Elements in streams are in increasing order
 * Need to store them in global increasing order
 * Output : 1, 1, 3, 4, 4, 5, 6, 7, 9
 *
 *
 * Been asked in Altimetrik
 * */
class MergeKStream {

    static class Node {
        int value;
        Iterator<Integer> iterator;

        Node(int value, Iterator<Integer> iterator) {
            this.value = value;
            this.iterator = iterator;
        }
    }

    private final PriorityQueue<Node> minHeap;

    public MergeKStream(List<Iterator<Integer>> streams) {

        minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));

        // Initialize heap with first element from each stream
        for (Iterator<Integer> it : streams) {
            if (it.hasNext()) {
                minHeap.offer(new Node(it.next(), it));
            }
        }
    }

    public boolean hasNext() {
        return !minHeap.isEmpty();
    }


    /*
     *
     *  Time Complexity     : all 1 element - O(log K). For all n element it will be - O(n log k)
     *  Space complexity    : O(k)
     * */
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Node node = minHeap.poll();
        int result = node.value;

        // Lazy load next element from same stream
        if (node.iterator.hasNext()) {
            minHeap.offer(new Node(node.iterator.next(), node.iterator));
        }

        return result;
    }
}