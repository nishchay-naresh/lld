package com.nishchay.oops.containers.pojo;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getTotalVolume() {
        return product.getVolume() * quantity;
    }
}