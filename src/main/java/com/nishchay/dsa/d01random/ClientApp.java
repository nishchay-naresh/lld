package com.nishchay.dsa.d01random;

public class ClientApp {

    public static void main(String[] args) {
        setExample();
        collectionExample();
    }

    private static void setExample() {
        RandomizedSet rc = new RandomizedSet();

        System.out.println("---- INSERT OPERATIONS ----");
        System.out.println("Insert 10: " + rc.insert(10)); // true
        System.out.println("Insert 90: " + rc.insert(90)); // false
        System.out.println("Insert 20: " + rc.insert(20)); // true
        System.out.println("Insert 30: " + rc.insert(30)); // true
        System.out.println("Insert 70: " + rc.insert(70)); // true
        System.out.println("Insert 50: " + rc.insert(50)); // true
        System.out.println("Insert 80: " + rc.insert(80)); // true

        rc.printState();

        System.out.println("\n---- REMOVE OPERATIONS ----");
        System.out.println("Remove 10: " + rc.remove(10)); // true
        rc.printState();

        System.out.println("Remove 20: " + rc.remove(20)); // true
        rc.printState();

        System.out.println("Remove 10: " + rc.remove(90)); // false (no more 10)
        rc.printState();

        System.out.println("\n---- RANDOM PICKS ----");
        for (int i = 0; i < 10; i++) {
            System.out.println("Random: " + rc.getRandom());
        }
    }

    private static void collectionExample() {

        RandomizedCollection rc = new RandomizedCollection();

        System.out.println("---- INSERT OPERATIONS ----");
        System.out.println("Insert 10: " + rc.insert(10)); // true
        System.out.println("Insert 10: " + rc.insert(10)); // false (duplicate)
        System.out.println("Insert 20: " + rc.insert(20)); // true
        System.out.println("Insert 30: " + rc.insert(30)); // true

        rc.printState();

        System.out.println("\n---- REMOVE OPERATIONS ----");
        System.out.println("Remove 10: " + rc.remove(10)); // true
        rc.printState();

        System.out.println("Remove 10: " + rc.remove(10)); // true
        rc.printState();

        System.out.println("Remove 10: " + rc.remove(10)); // false (no more 10)
        rc.printState();

        System.out.println("\n---- RANDOM PICKS ----");
        for (int i = 0; i < 10; i++) {
            System.out.println("Random: " + rc.getRandom());
        }
    }
}