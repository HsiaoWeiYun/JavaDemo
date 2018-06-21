package com.hsiaoweiyun.designpatterns.command;

public class Receiver {

    public void play(){
        System.out.println("### play... ");
    }

    public void stop(){
        System.out.println("### stop... ");
    }

    public void rewind(){
        System.out.println("### rewind... ");
    }

}
