package com.nishchay.oops.containers.pojo;

public class Container {
    private int id;
    private int length;
    private int breadth;
    private int height;

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