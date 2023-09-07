package com.hsiaoweiyun.designpatterns.builder;

public class Test {

    private final int p1;
    private final int p2;
    private final int p3;

    private Test(Builder builder) {
        p1 = builder.p1;
        p2 = builder.p2;
        p3 = builder.p3;
    }

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public int getP3() {
        return p3;
    }

    public static class Builder {

        private int p1;
        private int p2;
        private int p3;

        public Builder p1(int val) {
            p1 = val;
            return this;
        }

        public Builder p2(int val) {
            p2 = val;
            return this;
        }

        public Builder p3(int val) {
            p3 = val;
            return this;
        }

        public Test build() {
            return new Test(this);
        }

    }

    public static void main(String[] args) {
        Test test = new Builder().p1(1).p2(2).p3(3).build();
        System.out.println(test.getP1());
        System.out.println(test.getP2());
        System.out.println(test.getP3());
    }


}
