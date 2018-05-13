package com.hsiaoweiyun.generic;

import java.util.Arrays;

public class Node <T> {

    public T value;
    public Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public void testParameter(T t){
    }

    public T testReturn(){
        return value;
    }
}
