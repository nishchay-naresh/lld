package com.nishchay.oops.tesco.containers.pojo;

import java.util.ArrayList;
import java.util.List;

public class Order {
    List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public int getTotalVolume() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.getTotalVolume();
        }
        return total;
    }
}