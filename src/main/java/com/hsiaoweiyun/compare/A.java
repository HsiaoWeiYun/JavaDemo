package com.hsiaoweiyun.compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class A implements Comparable<A> {

    private Integer result1;

    private String result2;

    public A(Integer result1, String result2) {
        this.result1 = result1;
        this.result2 = result2;
    }

    @Override
    public String toString() {
        return "A{" +
                "result1=" + result1 +
                ", result2='" + result2 + '\'' +
                '}';
    }

    public static void main(String[] args) {
        A a = new A(1, "1");
        A b = new A(2, "2");
        A c = new A(1, "22");

        List<A> list = Arrays.asList(a, b, c);

        Collections.sort(list);
        System.out.println(list.stream().map(A::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public int compareTo(A o) {
        //依序從關鍵的成員開始比較, 直到產生非零結果
        int ans = Integer.compare(result1, o.result1);
        if (ans == 0) {
            ans = result2.compareTo(o.result2);
            if (ans == 0) {
                return 0;
            }
        }
        return ans;
    }
}
