package com.nishchay.dsa.d02mergek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {

        mergeKListEx();
        mergeKIterators();
    }

    private static void mergeKListEx() {

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(5, 8, 15, 20));
        lists.add(Arrays.asList(6, 7, 9, 12, 22, 25, 36));
        lists.add(Arrays.asList(1, 8));
        lists.add(Arrays.asList(8, 10, 11, 22, 25));
        lists.add(Arrays.asList(16));
        lists.add(Arrays.asList(6, 9, 12, 13, 18));

        MergeKList merge = new MergeKList();

        List<Integer> merged = merge.mergeHaveSmallestK(lists);
        System.out.println(merged);
        // Output: [1, 5, 6, 6, 7, 8, 8, 8, 9, 9, 10, 11, 12, 12, 13, 15, 16, 18, 20, 22, 22, 25, 25, 36]

    }

    private static void mergeKIterators() {

        List<Integer> l1 = Arrays.asList(1, 4, 5);
        List<Integer> l2 = Arrays.asList(1, 3, 4);
        List<Integer> l3 = Arrays.asList(6, 7, 9);

        MergeKStream it = new MergeKStream(Arrays.asList(
                l1.iterator(),
                l2.iterator(),
                l3.iterator()
        ));

        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        // o/p => 1 1 3 4 4 5 6 7 9
    }
}
