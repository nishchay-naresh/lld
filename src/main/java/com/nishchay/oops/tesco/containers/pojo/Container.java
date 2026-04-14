package com.nishchay.oops.tesco.containers.pojo;

public record Container( int id, int length, int breadth, int height){
    public int getVolume() {
        return length * breadth * height;
    }
}