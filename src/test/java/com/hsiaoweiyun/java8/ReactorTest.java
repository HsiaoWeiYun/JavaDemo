package com.hsiaoweiyun.java8;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

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
    public void testThreads() throws InterruptedException {

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

    @Test
    public void testSchedulers() throws InterruptedException {
        Flux<Integer> nums1 = Flux.range(0, 10).delayElements(Duration.ofMillis(100))
                .map(num->{
                    System.out.println("map thread : " + Thread.currentThread().getName());
                    return num*10;
                }).map(num->{
                    System.out.println("map2 thread : " + Thread.currentThread().getName());
                    return num*10;
                });

        //上游全部thread會與訂閱指定的thread相同
        nums1 = nums1.subscribeOn(Schedulers.parallel(), false);
        nums1.subscribe(num->{System.out.println("subscribe thread : " + Thread.currentThread().getName());});


        Thread.sleep(1100L);
        System.out.println("============================================");

        //使用delayXXX之後的操作會使用 Schedulers.parallel()
        Flux<Integer> nums2 = Flux.range(0, 10).delayElements(Duration.ofMillis(100))
                .doOnNext(num->{System.out.println("before publishOn : "+ Thread.currentThread().getName());})
                .publishOn(Schedulers.elastic())  //publishOn 後面幾個操作都會使用指定thread pool
                .doOnNext(num->{System.out.println("after publishOn : "+ Thread.currentThread().getName());});

        nums2.subscribe(num->{System.out.println("subscribe thread : " + Thread.currentThread().getName());});

        Thread.sleep(1100L);

    }

    @Test
    public void testErrorHandling() throws InterruptedException {
        //1. catch and return a static default value
        Flux<Integer> num1 = Flux.range(0, 6)
                .map(num->doSomethingDangerous(num))
                .onErrorReturn(-1);

        num1.subscribe(num->System.out.println(num));

        num1 = Flux.range(0, 6)
                .map(num->doSomethingDangerous(num))
                .onErrorReturn(throwable -> {
                    return throwable instanceof RuntimeException;
                    }, -1);

        num1.subscribe(num->System.out.println(num));

        //2. 發生exception時使用其他function處理
        Flux<Integer> num2 = Flux.range(0, 6)
                .map(num->doSomethingDangerous(num))
                .onErrorResume(e -> getDefaultValueIfError(e));

        num2.subscribe(num->System.out.println(num));

        //包裝後重新拋出
        num2 = Flux.range(0, 6)
                .map(num->doSomethingDangerous(num))
                .onErrorResume(e -> Flux.error(new UnsupportedOperationException("Not support")));

        num2.subscribe(num->System.out.println(num), System.err::println);

        //先log, 包裝後重新拋出
        num2 = Flux.range(0, 6)
                .map(num->doSomethingDangerous(num))
                //doOnError 只不過是消費與產生副作用而已
                .doOnError(throwable -> {System.err.println("error: " + throwable.getMessage());})
                .onErrorMap(throwable -> new UnsupportedOperationException("Not support", throwable));

        num2.subscribe(num->System.out.println(num), System.err::println);

        //3. try catch finally
        AtomicBoolean isDisposed = new AtomicBoolean();
        Disposable disposableInstance = new Disposable() {
            @Override
            public void dispose() {
                isDisposed.set(true);
            }

            @Override
            public String toString() {
                return "DISPOSABLE";
            }
        };

        Flux<String> flux =
                Flux.using(
                        //資源生成
                        () -> disposableInstance,
                        //資源處理
                        disposable -> Flux.just(disposable.toString()),
                        //清理資源
                        dispose->dispose.dispose()
                );

        flux.subscribe(result->{System.out.println(result + " "+ isDisposed.get());}, System.err::println);
        //消費過後資源才釋放
        System.out.println(isDisposed + " "+ isDisposed.get());

        //finally
        Flux<Integer> num3 = Flux.range(0, 6)
                .map(num->doSomethingDangerous(num))
                .doFinally(signalType -> {
                    //釋放資源或者在你想要在finally做的事情
                    if(signalType == SignalType.ON_ERROR){
                        System.out.println("Error Happened");
                    }else if(signalType == SignalType.CANCEL){
                        System.out.println("Cancel Happened");
                    }else if(signalType == SignalType.ON_COMPLETE){
                        System.out.println("Success");
                    }
                });
        num3.subscribe(num->System.out.println(num), System.err::println);

        //4. 重試, 重試也是重要的錯誤處理, 但這邊的重試指的是重新訂閱, 與原來已經是不同序列
        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("boom");
                })
                .retry(1)
                .subscribe(System.out::println, System.err::println);

        Thread.sleep(2100);


    }

    public Flux<Integer> getDefaultValueIfError(Throwable throwable){
        if(throwable instanceof RuntimeException){
            return Flux.just(-1,-1,-1);
        }
        return Flux.error(throwable);
    }

    public Integer doSomethingDangerous(Integer value){
        if(Objects.isNull(value)){
            throw new NullPointerException("value is null");
        }

        if((value % 2) != 0){
            throw new RuntimeException("ERROR");
        }

        return value;
    }

    /*
     * DirectProcessor
     * 1. 不支援背壓
     * */
    @Test
    public void testDirectProcessor(){
        DirectProcessor<Integer> directProcessor = DirectProcessor.create();

        Flux<Integer> nums = directProcessor
                .filter(num -> num % 2 == 0)
                .map(num -> num + 1);

        nums.subscribe(System.out::println);

        IntStream.range(1,20)
                .forEach(e -> {
                    directProcessor.onNext(e);
                });

    }

    @Test
    public void testDirectProcessorBackpressure(){
        DirectProcessor<Integer> directProcessor = DirectProcessor.create();

        Flux<Integer> nums = directProcessor
                .filter(num -> num % 2 == 0)
                .map(num -> num + 1);

        nums.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.printf("onNext: %d\n", integer);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        //如果publisher發布了n筆資料, 如過Subscriber要求<n則會拋出OverflowException
        //Can't deliver value due to lack of requests

        IntStream.range(1,20)
                .forEach(e -> {
                    directProcessor.onNext(e);
                });

    }

    /*
    * UnicastProcessor特點
    * 1. 只能有一個subscriber
    * 2. 支持背壓
    * 3. 默認是unbound, 發布資料後若來不及消費會先緩存
    * 4. 超過緩存大小會呼叫doOnError callback
    * */
    @Test
    public void testUnicastProcessor() throws InterruptedException {

        //預設是無界的(unbound), 發布資料後若來不及消費會把資料緩存下來
        UnicastProcessor<Integer> unicastProcessor = UnicastProcessor.create(Queues.<Integer>get(8).get());
        //UnicastProcessor<Integer> unicastProcessor = UnicastProcessor.create();
        Flux<Integer> flux = unicastProcessor
                .map(e -> e)
                //若buffer滿了processor會發生錯誤
                .doOnError(System.err::println);

        IntStream.rangeClosed(1,12)
                .forEach(e -> {
                    System.out.printf("emit:%d\n",e);
                    unicastProcessor.onNext(e);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });

        System.out.println("begin to sleep 7 seconds");
        TimeUnit.SECONDS.sleep(7);
        flux.subscribe(System.out::println);

        //UnicastProcessor只能有一個subscriber
        //flux.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);
    }

    /*EmitterProcessor 特點
    * 1. 支援多個訂閱
    * 2. 支援背壓
    * 3. bufferSize參數, 若發布資料後還沒有訂閱, 當buffer滿時會阻塞onNext直到被消費
    * 4. 後續訂閱者只能消費到自己訂閱當下的資料
    * */
    @Test
    public void testEmitterProcessor() throws InterruptedException {
        int bufferSize = 3;

        //初始化bufferSize, 設太小的話似乎會有個預設值, 不確定是多少
        FluxProcessor<Integer, Integer> processor = EmitterProcessor.create(bufferSize);
        Flux<Integer> flux1 = processor.map(e -> e);
        Flux<Integer> flux2 = processor.map(e -> e*10);

        IntStream.rangeClosed(1,8).forEach(e -> {
            System.out.printf("emit:%d\n",e);
            //假如超過bufferSize則會阻塞
            processor.onNext(e);
        });

        flux1.subscribe(num-> System.out.printf("flux1: %d\n", num));

        IntStream.rangeClosed(9,10).forEach(e -> {
            System.out.printf("emit:%d\n",e);
            processor.onNext(e);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        //後面的subscriber只能消費到這個時間點之後推送的資料
        flux2.subscribe(num-> System.out.printf("flux2: %d\n", num));

        processor.onNext(11);
        processor.onNext(12);

        processor.onComplete();
        processor.blockLast();//阻塞至最後一個元素或complete
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


