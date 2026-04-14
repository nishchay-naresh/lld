package com.nishchay.oops.containers;

import com.nishchay.oops.containers.pojo.Container;
import com.nishchay.oops.containers.pojo.Order;
import com.nishchay.oops.containers.pojo.OrderItem;
import com.nishchay.oops.containers.pojo.Product;

import java.util.Arrays;
import java.util.List;

/*
 *	Problem Statement:
 * 		Ecom has a fleet of vehicles to deliver orders to the customer.
 * 		Assigning the right set of orders to different sized vehicles is crucial.
 * 		Different vehicles can fit different container sizes.
 * 		You are given:
 *			c containers, along with their volumes (l, b, h),
 * 			a catalogue of products with their volume requirements (l, b, h), and
 * 		 	an order with p products and their quantities.
 * 		Example:
 * 		Containers:
 * 			SMALL → id=1, length=10, breadth=20, height=30
 * 			MEDIUM → id=2, length=50, breadth=60, height=70
 * 			LARGE → id=3, length=100, breadth=200, height=300
 * 		Products:
 * 			productId=1, length=2, breadth=4, height=10
 * 			productId=2, length=2, breadth=6, height=4
 * 			productId=3, length=5, breadth=6, height=7
 * 		Order:
 * 			productId=1, quantity=3
 * 			productId=2, quantity=2
 * 		Task:
 * 		Determine whether the order fits in any of the given c containers and return the container id.
 * 		Expected Output for the sample: The order fits in the SMALL container (id=1)
 *
 * */
public class MainApp {
    public static void main(String[] args) {
        // Containers
        List<Container> containers = Arrays.asList(
                new Container(1, 10, 20, 30),   // SMALL
                new Container(2, 50, 60, 70),   // MEDIUM
                new Container(3, 100, 200, 300) // LARGE
        );

        // Products
        Product p1 = new Product(1, 2, 4, 10);
        Product p2 = new Product(2, 2, 6, 4);
        Product p3 = new Product(3, 5, 6, 7);

        // Order
        Order order = new Order();
        order.addItem(new OrderItem(p1, 3)); // 3 units of product 1
        order.addItem(new OrderItem(p2, 2)); // 2 units of product 2

        // Find suitable container
        Container chosenContainer = findContainerForOrder(order, containers);
        if (chosenContainer != null) {
            System.out.println("Order fits in container id=" + chosenContainer.getId());
        } else {
            System.out.println("No container can fit the order.");
        }
    }

    public static Container findContainerForOrder(Order order, List<Container> containers) {
        int orderVolume = order.getTotalVolume();
        Container bestFit = null;

        for (Container curr : containers) {
            if (bestFit == null) {
                if (curr.getVolume() >= orderVolume)
                    bestFit = curr;
            } else {
                if (curr.getVolume() < bestFit.getVolume() && curr.getVolume() >= orderVolume)
                    bestFit = curr;
            }
        }
        return bestFit;
    }

}