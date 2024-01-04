package com.hsiaoweiyun.io;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class BufferedStream {

    @SneakyThrows
    public static void main(String[] args) {
        String filePath = "/Users/xiaoweiyun/test.txt";

        int len = 0;
        byte[]buf = new byte[2];

        try(FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis)){

            while ((len = bis.read(buf)) != -1){
                System.out.print(new String(buf, 0, len));
            }
        }
    }
}
