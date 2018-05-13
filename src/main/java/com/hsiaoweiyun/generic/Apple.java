package com.hsiaoweiyun.generic;

public class Apple extends Fruit {

    private String color = "red";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void show() {
        System.out.println("Apple");
    }
}
