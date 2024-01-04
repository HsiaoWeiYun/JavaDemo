package com.hsiaoweiyun.io;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PrintStream {

    @SneakyThrows
    public static void main(String[] args) {

        String filePath = "/Users/xiaoweiyun/testPrintStream.txt";

        try(FileOutputStream fos = new FileOutputStream(filePath);
            java.io.PrintStream ps = new java.io.PrintStream(fos)){

            ps.println("ABC123456");
            ps.println(1);
            ps.print(true);
            ps.println("GGGGGGGG");
            ps.flush();

        }

    }

}
