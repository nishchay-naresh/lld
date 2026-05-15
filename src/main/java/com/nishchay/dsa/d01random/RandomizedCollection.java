package com.nishchay.dsa.d01random;

import java.util.*;

public class RandomizedCollection {

    private final List<Integer> list;
    private final Map<Integer, Set<Integer>> map;
    private final Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean isNew = !map.containsKey(val);

        map.computeIfAbsent(val, k -> new HashSet<>())
           .add(list.size());

        list.add(val);

        return isNew;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        // Step 1: get any index of val
        int indexToRemove = map.get(val).iterator().next();

        int lastIndex = list.size() - 1;
        int lastElement = list.get(lastIndex);

        // Step 2: swap with last element
        list.set(indexToRemove, lastElement);

        // Step 3: update map for last element
        map.get(lastElement).add(indexToRemove);
        map.get(lastElement).remove(lastIndex);

        // Step 4: remove index from val set
        map.get(val).remove(indexToRemove);

        // Step 5: remove last element from list
        list.remove(lastIndex);

        // Step 6: cleanup
        if (map.get(val).isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public void printState() {
        System.out.println("List: " + list);
        System.out.println("Map: " + map);
    }
}