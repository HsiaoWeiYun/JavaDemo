package com.hsiaoweiyun.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.*;

public class LambdaTest {

    @Test
    public void simpleLambda(){

        //舊的寫法長這樣, 當interface只有一個方法需要實作時可以用Lambda語法,
        //其實我們在舊有寫法只關心匿名類別內部那個需要實作的方法裡面的輸入 實作 輸入,
        //所以Lambda讓語意變得更清晰, 效能也變得更好,
        //不需要new一個新的匿名class出來而是類似call function的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("simpleLambda");
            }
        }).start();

        //最簡單的Lambda表示式, 輸入->匿名類別內部實作
        new Thread(
                ()->{
                    System.out.println("simpleLambda");
                }
                ).start();

        String[] names = {"Victor", "Tom", "Willson"};
        Arrays.sort(names, (o1,o2)-> {return o1.length()- o2.length();});   //Lambda
        System.out.println(Arrays.toString(names));

        Arrays.sort(names, new Comparator<String>() {   //舊寫法
            @Override
            public int compare(String o1, String o2) {
                return o2.length()- o1.length();
            }
        });
        System.out.println(Arrays.toString(names));
    }

    @Test
    public void methodReferenceTest(){
        //一般的lambda
        Arrays.asList("Victor", "Tom", "Willson").forEach(s->{System.out.println(s);});
        //method reference
        Arrays.asList("Victor", "Tom", "Willson").forEach(System.out::println);

        //method reference, 自動推導輸入輸出, 可避免到處寫下lambda
        String[] names = {"Victor", "Tom", "Willson"};
        Arrays.sort(names, StringOrder::byLength); //int StringOrder.byLength(String, String)
        System.out.println(Arrays.toString(names));

        //非static 的用法
        StringOrder so = new StringOrder();
        Arrays.sort(names, so::byLexicography);
        System.out.println(Arrays.toString(names));


    }

    @Test
    public void consumerInterfaceTest(){
        //單純消費一個input, 沒有回傳值
        Consumer<String> consumer = (s)->{System.out.println(s);};
        consumer.accept("Hello Consumer!!");
        //直接消費一個int
        IntConsumer intConsumer = (i)->System.out.println(i);
        intConsumer.accept(Integer.MAX_VALUE);
        //直接消費一個long
        LongConsumer longConsumer = (i)->System.out.println(i);
        longConsumer.accept(Long.MAX_VALUE);
        //直接消費一個Double
        DoubleConsumer doubleConsumer = (d)->System.out.println(d);
        doubleConsumer.accept(Double.MAX_EXPONENT);
        //消費兩個參數
        BiConsumer<Integer, Integer> biConsumer = (i1, i2)->System.out.println(i1*i2);
        biConsumer.accept(10,10);
        //消費第一個參數是物件第二參數是int, ObjLongConsumer ObjDoubleConsumer 以此類推
        ObjIntConsumer<String> objIntConsumer = (s,i)->System.out.println(s+i);
        objIntConsumer.accept("1+",1);//print 1+1
    }

    @Test
    public void functionInterfaceTest(){
        //一個input, 回傳一個output   行為像是 y=f(x)
        Function<String, String> function = (s)->{return s.toUpperCase();};
        System.out.println(function.apply("hello Function!!"));
        //兩個輸入一個輸出可用BiFunction
        BiFunction<Integer,Integer,String> biFunction = (i1,i2)->{return "" + i1 + i2;};
        System.out.println(biFunction.apply(1,2));
        BinaryOperator<String> binaryOperator = (s1, s2) ->{return s1 + s2;};
        System.out.println(binaryOperator.apply("Q_","_Q"));
    }

    @Test
    public void predicateInterfaceTest(){
        //一個輸入回傳ture or false
        Predicate<String> predicate = (s1)->{return s1.endsWith("test");};
        System.out.println(predicate.test("my test"));
        //基本對應函式介面為 IntPredicate LongPredicate DoublePredicate
    }

    @Test
    public void supplierInterfaceTest(){
        //不接受引數, 只有回傳值
        Supplier<String> supplier = ()->{return "test 123";};
        System.out.println(supplier.get());
        //對應回傳型態使用: BooleanSupplier DoubleSupplier IntSupplier LongSupplier
    }


}
