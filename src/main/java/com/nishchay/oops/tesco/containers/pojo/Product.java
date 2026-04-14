package com.nishchay.oops.tesco.containers.pojo;

public record Product(int productId, int length, int breadth, int height) {
    public int getVolume() {
        return length * breadth * height;
    }
}