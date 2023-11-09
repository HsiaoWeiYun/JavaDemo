package com.hsiaoweiyun.compare;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class B implements Comparable<B> {

    private static final Comparator<B> COMPARATOR = Comparator.comparingInt((B b) -> b.result1).thenComparing((B b) -> b.result2);

    private Integer result1;

    private String result2;

    public B(Integer result1, String result2) {
        this.result1 = result1;
        this.result2 = result2;
    }

    @Override
    public String toString() {
        return "B{" +
                "result1=" + result1 +
                ", result2='" + result2 + '\'' +
                '}';
    }

    public static void main(String[] args) {
        B b1 = new B(2, "1");
        B b2 = new B(1, "2");
        B b3 = new B(1, "22");

        B[] bs = new B[]{b1, b2, b3};

        Arrays.sort(bs, COMPARATOR);
        System.out.println(Arrays.stream(bs).map(B::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public int compareTo(B o) {
        return COMPARATOR.compare(this, o);
    }
}
