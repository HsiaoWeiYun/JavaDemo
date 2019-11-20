package com.hsiaoweiyun.designpatterns;

import com.hsiaoweiyun.designpatterns.command.*;
import org.junit.Test;

import java.util.Arrays;

public class CommandTest {

    @Test
    public void commandPatternTest(){
        Receiver videoReceiver = new Receiver();   //接收者

        //三種command
        Command playCommand = new PlayCommand(videoReceiver);
        Command stopCommand = new StopCommand(videoReceiver);
        Command rewindCommand = new RewindCommand(videoReceiver);


        Invoker invoker = new Invoker();   //發起者, 發起者不需要知道接收者是誰, 消除發起者與接收者之間的耦合
        invoker.setCommand(playCommand);
        invoker.setCommandList(Arrays.asList(playCommand, stopCommand, rewindCommand, stopCommand, playCommand));

        invoker.action();
        invoker.actions();


        /*
        * 優點: 1.消除接收者與發起者的耦合
        *       2.新命令添加方便
        *       3.方便做組合命令
        * 缺點: 1.若命令很多會存在很多命令類別
        * */


    }



}
