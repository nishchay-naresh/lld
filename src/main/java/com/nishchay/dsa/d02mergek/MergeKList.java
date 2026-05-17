package com.nishchay.dsa.d02mergek;

import java.util.*;

/*
 * ================================ Merge k Sorted Arrays Using Min Heap =======================================
 * Given k sorted arrays of possibly different sizes, merge them and print the sorted output.
 *
 * Examples:
 *				Input: k = 3
 *				arr[][] = {
 *							{1, 3},
 *		                  	{2, 4, 6},
 *		                  	{0, 9, 10, 11}
 *						  } ;
 *				Output: 0 1 2 3 4 6 9 10 11
 *
 *				Input: k = 2
 *				arr[][] = {
 *							{1, 3, 20},
 *		                  	{2, 4, 6}
 *						  } ;
 *				Output: 1 2 3 4 6 20
 *
 * https://www.geeksforgeeks.org/dsa/merge-k-sorted-arrays-set-2-different-sized-arrays/
 *
 * Refer the problem, code and discussed around the solution for the problem  - Merge K Sorted Arrays
 * here at - com.nishchay.ds.array.a05merge.MergeKSortedArray.java
 * ----------------------------------------------------------------------------------------------
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
 * been asked in Altimetrik
 * */
class MergeKList {

    /*
     *
     *  Time Complexity     : O(n*log k)
     *  Space complexity    : O(k)
     * */
    List<Integer> mergeHaveSmallestK(List<List<Integer>> lists) {
        int k = lists.size();
        int n = lists.stream().mapToInt(List::size).sum();
        List<Integer> output = new ArrayList<>(n);

        PriorityQueue<Element> pq = new PriorityQueue<>();

        // Initialize heap - Add first element from each list ( this should be < k)
        for (int i = 0; i < k; i++) {
            List<Integer> currList = lists.get(i);
            if (!currList.isEmpty()) {
                pq.add(new Element(currList.get(0), i, 0));
            }
        }

        // Extract min and push next element from same list
        while (!pq.isEmpty()) {
            Element e = pq.poll();
            output.add(e.value);   // Always extract the smallest element globally

            // Push Next Element from Same List
            int nextIndex = e.elemIndex + 1;
            if (nextIndex < lists.get(e.listIndex).size()) {
                pq.add(new Element(lists.get(e.listIndex).get(nextIndex), e.listIndex, nextIndex));
            }
        }

        return output;
    }

    static class Element implements Comparable<Element> {
        int value;
        int listIndex;
        int elemIndex;

        Element(int value, int listIndex, int elemIndex) {
            this.value = value;
            this.listIndex = listIndex;
            this.elemIndex = elemIndex;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.value, other.value);
        }
    }
}