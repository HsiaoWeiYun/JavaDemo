package com.hsiaoweiyun.io;


import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class ByteArrayStream {

//    @SneakyThrows
//    public static void main(String[] args) {
//
//        byte[] buf = new byte[64];
//
//        try (ByteArrayInputStream bai = new ByteArrayInputStream("ABC123456".getBytes())) {
//
//            int len = bai.read(buf);
//            System.out.println(new String(buf, 0, len));
//
//            bai.reset();
//
//            len = bai.read(buf);
//            System.out.println(new String(buf, 0, len));
//
//        }
//    }

    @SneakyThrows
    public static void main(String[] args) {

        byte[] data = "ABC123456".getBytes();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(data, 0, data.length);
        System.out.println(new String(baos.toByteArray(), StandardCharsets.UTF_8));

    }

}
