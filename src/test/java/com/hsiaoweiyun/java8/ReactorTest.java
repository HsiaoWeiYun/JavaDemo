package com.hsiaoweiyun.java8;

import org.junit.Test;
import reactor.core.publisher.Mono;

/**
 * @author VictorHsiao on 2019/7/12.
 */
public class ReactorTest {

    @Test
    public void testMonoJust(){
        Mono<String> data = Mono.just("a");
        data.subscribe(System.out::println);
    }


}
