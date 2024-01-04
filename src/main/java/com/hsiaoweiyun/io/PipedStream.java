package com.hsiaoweiyun.io;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class PipedStream {

    @SneakyThrows
    public static void main(String[] args){

        try(PipedInputStream pis = new PipedInputStream();
            PipedOutputStream pos = new PipedOutputStream(pis)){

            new Thread(()->{
                try {
                    pos.write("ABC123456".getBytes(StandardCharsets.UTF_8));
                    pos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            int len = 0;
            byte[] buf = new byte[2];
            while ((len = pis.read(buf))!=-1){
                System.out.print(new String(buf, 0, len));
            }

        }

    }

}
