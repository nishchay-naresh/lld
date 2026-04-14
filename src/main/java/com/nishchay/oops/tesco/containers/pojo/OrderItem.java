package com.nishchay.oops.tesco.containers.pojo;

public record OrderItem(Product product, int quantity) {
    public int getTotalVolume() {
        return product.getVolume() * quantity;
    }
}