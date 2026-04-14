package com.nishchay.oops.containers.pojo;

public class Product {
    int productId, length, breadth, height;

    public Product(int productId, int l, int b, int h) {
        this.productId = productId;
        this.length = l;
        this.breadth = b;
        this.height = h;
    }

    public int getVolume() {
        return length * breadth * height;
    }
}