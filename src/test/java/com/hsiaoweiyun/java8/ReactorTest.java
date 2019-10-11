package com.hsiaoweiyun.java8;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author VictorHsiao on 2019/7/12.
 */
public class ReactorTest {

    @Test
    public void testMonoJust() {
        Mono<String> data = Mono.just("a");
        data.subscribe(System.out::println);
    }

    @Test
    public void testFluxRange() {
        Flux<Integer> numbers = Flux.range(1, 6);
        numbers.subscribe(System.out::print, Throwable::printStackTrace,
                () -> {
                    System.out.println("sequence successfully completes");
                });
    }

    @Test
    public void testSubscribe() throws InterruptedException {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });

        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));


        Flux<Integer> nums = Flux.range(1, 4);
        nums.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(2));//要求兩個元素
    }

    @Test
    public void testBaseSubscriber() {
        Flux<Integer> one2Ten = Flux.range(1, 10);

//        one2Ten.map(num->num+10).subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error " + error),
//                () -> System.out.println("Done"));

        one2Ten.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected Subscription upstream() {
                System.out.println("upstream");
                return super.upstream();
            }

            @Override
            public boolean isDisposed() {
                System.out.println("isDisposed");
                return super.isDisposed();
            }

            @Override
            public void dispose() {
                System.out.println("dispose");
                super.dispose();
            }

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                //第一次呼叫
                System.out.println("hookOnSubscribe");
                //super 預設是無限制, 但若自己實作的話最起碼要呼叫request相關method一次, 否則剛開始Subscribe就結束了
                request(2);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("hookOnNext " + value);
                //要求每次消費一個
                request(1);
                //if(value == 2) cancel();
                //if(value == 2) throw new RuntimeException("error test");
            }

            @Override
            protected void hookOnComplete() {
                //完成整個Flux時呼叫
                System.out.println("hookOnComplete");
                super.hookOnComplete();
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                //錯誤時呼叫
                System.out.println("hookOnError " + throwable.getMessage());
                //super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                //取消時呼叫
                System.out.println("hookOnCancel");
                super.hookOnCancel();
            }

            @Override
            protected void hookFinally(SignalType type) {
                //結束時呼叫, type 為結束狀態
                System.out.println("hookFinally " + type);
                super.hookFinally(type);
            }
        });
    }

    @Test
    public void testSynchronousGenerate() {
        //Programmatically create a Flux by generating signals one-by-one via a consumer callback and some state.
        Flux<String> testGenerate1 = Flux.generate(
                () -> 0,//需要產生物件序列的物件初始狀態
                (state, sink) -> {//這邊是一個bifunction, (物件上一步的狀態, SynchronousSink<T>); T是最終產出的物件
                    sink.next("next " + state);//emit 物件
                    if (state == 10) {
                        sink.complete();//完成物件序列, 發送完成訊號
                    }
                    return state + 1;//改變狀態
                }
        );

        testGenerate1.subscribe(System.out::println);


        Flux<String> testGenerate2 = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("next " + state);//emit 物件
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                },
                data -> System.out.println("consumer " + data)//消費最後狀態
        );

        testGenerate2.subscribe(System.out::println);

    }

    @Test
    public void testAsynchronousCreate() throws InterruptedException {

        DataProcessor dp = new DataProcessor();


        Flux<String> data1 = Flux.create(sink -> {
            dp.register(new DataEventListener<String>() {
                @Override
                public void onProcessData(List<String> datas) {
                    for (String data : datas) {
                        System.out.println("onProcessData: " + Thread.currentThread().getId() + " " + data);
                        sink.next("@@@" + data);
                    }
                }

                @Override
                public void processComplete() {
                    System.out.println("processComplete: " + Thread.currentThread().getId());
                    sink.complete();
                }
            });

            sink.onDispose(() -> {
                System.out.println("onDispose: " + Thread.currentThread().getId());
                dp.removeListerer();
            });
        });

        data1.subscribe(data -> {
            System.out.println("subscribe: " + Thread.currentThread().getId() + " " + data);
        });


        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {
            dp.setData(Arrays.asList("A", "B", "C"));
        });
        executorService.execute(() -> {
            dp.setData(Arrays.asList("D", "E", "F"));
        });
        executorService.execute(() -> {
            dp.setData(Arrays.asList("G", "H", "I"));
        });
        executorService.execute(() -> {
            dp.setData(Arrays.asList("J", "K", "L"));
        });
        executorService.execute(() -> {
            dp.setData(Arrays.asList("M", "N", "O"));
        });
        executorService.execute(() -> {
            dp.setData(Arrays.asList("P", "Q", "R"));
        });
        executorService.execute(() -> {
            dp.setData(Arrays.asList("S", "T", "U"));
        });

        Thread.sleep(3000L);

        dp.finish();
    }

    @Test
    public void testHandle() {
        //handle必須當配輸入源一起使用, 每個物件逐步處理, 把他當作成filter與map的操作就對了
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null) {//過濾null物件
                        sink.next(letter);
                    }
                });

        alphabet.subscribe(System.out::println);
    }


    public String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }

    @Test
    public void testSchedulers() throws InterruptedException {

        //由mono or flux 產生出來的資料並不代表會用不同thread執行, 大多數操作都會用跟上游一樣的thread做事,
        //除非指定, 最上游 (source) 會使用跟subscribe相同的thread 做事情

        final Flux<String> flux = Flux.just("hello", "Hi", "你好");

        Thread t = new Thread(() -> flux
                .map(msg -> msg + " " + Thread.currentThread().getId())
                .subscribe(v ->
                        System.out.println(v + " subscribe id: " + Thread.currentThread().getId())
                )
        );
        t.start();
        t.join();
    }

}


class DataProcessor {
    private DataEventListener<String> myEventListener;

    public void register(DataEventListener<String> myEventListener) {
        this.myEventListener = myEventListener;
    }

    public void setData(List<String> data) {
        myEventListener.onProcessData(data);
    }

    public void finish() {
        myEventListener.processComplete();
    }

    public void removeListerer() {
        myEventListener = null;
    }
}

interface DataEventListener<T> {
    void onProcessData(List<T> data);

    void processComplete();
}


