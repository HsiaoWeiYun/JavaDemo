package com.hsiaoweiyun.generic;

public class Banana extends Fruit {

    private String color = "yellow";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void show() {
        System.out.println("Banana");
    }
}
