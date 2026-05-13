package com.nishchay.oops.timerange;


import java.util.*;

/*
 *
 * In a file there are records like <id , time> you need to process them and store them in a in memory DB.
 * Such a away that your implementation should support below requirements:
 * Get the ids for a time range (here time could be duplicate a swell)
 *
 * */
public class AppMain {

    public static void main(String[] args) {


        InMemoryTimeDB db = new InMemoryTimeDB();

        // Simulated file content as String
        String inputData =
                "101,1000\n" +
                        "102,2000\n" +
                        "103,1000\n" +
                        "104,3000\n" +
                        "105,2500\n" +
                        "106,2000";

        // Load data
        db.loadFromString(inputData);

        System.out.println("Current DB state :");
        db.printCurrentDBState();

        // Query
        long startTime = 1000;
        long endTime = 2500;

        List<Integer> ids = db.getIdsInRange(startTime, endTime);

        System.out.println("IDs between " + startTime + " and " + endTime + ":");
        System.out.println(ids);
    }


    /*
     * 	Problem Statement
     * 	You have records: <id, timestamp>
     * 	Example:
     * 			101, 10:15
     * 			102, 10:20
     * 			103, 10:15
     * 			104, 10:25
     *
     * 	Requirements:
     * 	1. Insert records efficiently from file
     * 	2. Query: getIds(startTime, endTime)
     * 		Return all IDs whose timestamps lie in that range.
     *
     * 	Important:
     * 		Duplicate timestamps allowed
     * 		Multiple IDs can exist for same time
     *
     * 	Best Data Structure Choice
     * 		TreeMap<time, List<ids>> => TreeMap<Long, List<Integer>>
     * 	Why TreeMap?
     * 		Sorted by timestamp
     * 	Supports:
     * 		subMap(start, end)
     *
     * 	Range query complexity: O(log n + k)
     * 	Design
     * 		timestamp → list of ids
     * 	Example:
     * 		10:15 → [101,103]
     * 		10:20 → [102]
     * 		10:25 → [104]
     *
     * */
    static class InMemoryTimeDB {

        // timestamp -> list of ids
        private final TreeMap<Long, List<Integer>> timeIndex = new TreeMap<>(Comparator.comparingLong(Long::longValue));

        // Insert single record
        public void insert(int id, long timestamp) {
            timeIndex
                    .computeIfAbsent(timestamp, k -> new ArrayList<>())
                    .add(id);
        }


        // Load records from String input
        // Format: "101,1000\n102,2000\n103,1000"
        public void loadFromString(String data) {
            String[] lines = data.split("\n");

            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0].trim());
                long timestamp = Long.parseLong(parts[1].trim());

                insert(id, timestamp);
            }
        }

        public void printCurrentDBState() {
            System.out.println("timeIndex = " + timeIndex);
        }

        // Query IDs in time range
        public List<Integer> getIdsInRange(long startTime, long endTime) {
            List<Integer> result = new ArrayList<>();

            NavigableMap<Long, List<Integer>> subMap =
                    timeIndex.subMap(startTime, true, endTime, true);

            for (List<Integer> ids : subMap.values()) {
                result.addAll(ids);
            }

            return result;
        }
    }

}
