package com.nishchay.dsa.d01random;

import java.util.*;


/*
 *	Design a data structure supporting:
 *			insert(val)    → O(1)
 *			remove(val)    → O(1)
 *			getRandom()    → O(1)
 *	Why Normal Structures Fail
 *	Using only HashSet
 *	    • insert/remove → ✅ O(1)
 *	    • getRandom → ❌ not possible in O(1)
 *	Using only ArrayList
 *	    • getRandom → ✅ O(1)
 *	    • insert → ✅ O(1)
 *	    • remove → ❌ O(N) (shift elements)
 *	Key Idea (IMPORTANT)
 *	👉 Combine:
 *			1. ArrayList (for random access)
 *			2. HashMap (for index lookup)
 *	Design
 *		List<Integer> list        → store elements
 *		Map<Integer, Integer> map → value → index in list
 *
 *	Trick That Makes O(1) Remove Possible
 *	When removing an element:
 *	👉 Instead of shifting elements:
 *	    • Swap with last element
 *	    • Remove last element
 *	Example
 *			list = [10, 20, 30, 40]
 *			map  = {10:0, 20:1, 30:2, 40:3}
 *			remove(20)
 *	    1. Swap with last (40)  => 	list = [10, 40, 30, 40]
 *	    2. Update map 		=>	map[40] = 1
 *	    3. Remove last 		=>	list = [10, 40, 30]
 *							map.remove(20)
 *
 * */
public class RandomizedSet {

    private final List<Integer> list;
    private final Map<Integer, Integer> map;
    private final Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;

        list.add(val);
        map.put(val, list.size() - 1);

        return true;
    }

    /*
     * Using map here because :
     * The requirement is: remove(val) → O(1)
     *      remove(30) => list.indexOf(30) => This is O(N) ❌
     * So the REAL Bottleneck is: Finding the index of an element in O(1) instead of O(N)
     * Map is there to offer the same need :
     *          Map<Integer, Integer> map; // val → index
     *          index = map.get(val);  => This is O(N)
     *
     * Think of:
     *      ArrayList → storage
     *      HashMap → index lookup table
     *
     *  Complexity - O(1)
     * */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int lastIndex = list.size() - 1;
        int deletingIndex = map.get(val);
        int lastElement = list.get(lastIndex);

        // Swap - shift the lastElement to deletingIndex at both place -  list, map
        list.set(deletingIndex, lastElement);
        map.put(lastElement, deletingIndex);

        // Now delete the element at lastIndex at both place -  list, map
        list.remove(lastIndex);
        map.remove(val);

        return true;
    }

    /*
     *
     *  Complexity - O(1)
     * */
    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public void printState() {
        System.out.println("List: " + list);
        System.out.println("Map: " + map);
    }
}