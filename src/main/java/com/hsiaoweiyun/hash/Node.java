package com.hsiaoweiyun.hash;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Node<T> {

    private String domain;
    private String ip;
    private Map data = new HashMap();


    public Node(String domain, String ip) {
        this.domain = domain;
        this.ip = ip;
    }

    public void put(String key, T value) {
        data.put(key, value);
    }

    public void remove(String key) {
        data.remove(key);
    }

    public T get(String key) {
        return (T) data.get(key);
    }
}
