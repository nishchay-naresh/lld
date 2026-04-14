package com.nishchay.oops.tesco.ruleengine;

import com.nishchay.oops.tesco.ruleengine.pojo.*;

import java.util.List;

/*
 * ====================================== Strategy Pattern + Rule Engine ===============================================
 *
 *	Problem Statement:
 *	Tesco gets millions of orders every day with an average basket size of 100 items.
 *	Tesco Business has got some regulations around selling products online and in stores.
 *	These regulations are mandatory from legal and business perspective to enforce for all order transactions.
 *	You are given an order with a list of products in the shopping cart/basket with productid, product Category and quantity.
 *  And also, Restriction Rules on Qty and Qty/Category.
 *	Example:
 *	        Ordered items in the shopping cart/basket
 *	        Item-1 -> productid=1, category=Paracetamol, quantity=3
 *	        Item-2 -> productid=2, category=analgesic, quantity=3
 *	        Item-3 -> productid=3, category=chocolate, quantity=8
 *	        Item-4 -> productid=4, category= Paracetamol, quantity=2
 *
 *	 Business Restriction rules:
 *	        Cannot buy more than 10 Quantity of any products - BulkBuyLimit
 *	        Cannot buy more than 5 Quantity of paracetamol products – BulkBuyLimitCategory
 *
 *	        Write a restriction rule engine to run the restriction check against the shopping cart/basket and return the status as to MET/BREACHED indicating restriction
 *	        status for the given restriction rules.
 *	        For the above given example, the restriction status returned would be MET.
 *
 * */
public class MainApp {


    /*
     *	You are given:
     *		A basket of items - List<Item>
     *		A set of rules (constraints)
     *	Goal: Apply all rules ->  List<Item> -> return MET / BREACHED
     *
     *	Don’t hardcode logic like: if (category.equals("Paracetamol")) ...
     *
     * Instead: Treat each rule as a pluggable component
     * This is basically: Strategy Pattern + Rule Engine
     *
     * */
    public static void main(String[] args) {

        List<Rule> rules = List.of(
                new MaxQuantityPerProductRule(10),
                new CategoryQuantityRule("Paracetamol", 5)
        );
        RuleEngine engine = new RuleEngine(rules);

        List<Item> order1 = List.of(
                new Item(1, "Paracetamol", 3),
                new Item(2, "Analgesic", 3),
                new Item(3, "Chocolate", 8),
                new Item(4, "Paracetamol", 2)
        );
        System.out.println("order1 validation status - " + engine.evaluate(order1)); // MET

        List<Item> order2 = List.of(
                new Item(1, "Paracetamol", 4),
                new Item(2, "soap", 9),
                new Item(3, "Chocolate", 6),
                new Item(4, "Paracetamol", 2)
        );
        System.out.println("order2 validation status - " + engine.evaluate(order2)); // BREACHED

        List<Item> order3 = List.of(
                new Item(3, "Chocolate", 1),
                new Item(10, "Toothpaste", 10)
        );
        System.out.println("order3 validation status - " + engine.evaluate(order3)); // MET

        List<Item> order4 = List.of(
                new Item(3, "Chocolate", 1),
                new Item(10, "Toothpaste", 12)
        );
        System.out.println("order4 validation status - " + engine.evaluate(order4)); // BREACHED
    }
}