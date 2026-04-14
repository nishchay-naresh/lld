package com.nishchay.oops.containers.pojo;

public class Container {
    private final int id;
    private final int length;
    private final int breadth;
    private final int height;

    public Container(int id, int l, int b, int h) {
        this.id = id;
        this.length = l;
        this.breadth = b;
        this.height = h;
    }

    public int getId() {
        return id;
    }

    public int getVolume() {
        return length * breadth * height;
    }
}