package com.hsiaoweiyun.io;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class FileStream {


//    @SneakyThrows
//    public static void main(String[] args){
//
//        int len = 0;
//        byte[] buf = new byte[2];
//
//        String filePath = "/Users/xiaoweiyun/test.txt";
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            while ((len = fis.read(buf)) != -1){
//                System.out.print(new String(buf, 0, len, StandardCharsets.UTF_8));
//            }
//        }
//    }

//    @SneakyThrows
//    public static void main(String[] args){
//
//        int len = 0;
//        byte[] buf = new byte[2];
//
//        String filePath = "/Users/xiaoweiyun/test.txt";
//        String outputFilePath = "/Users/xiaoweiyun/test2.txt";
//
//        try (FileInputStream fis = new FileInputStream(filePath);
//             FileOutputStream fos = new FileOutputStream(outputFilePath)) {
//
//            while ((len = fis.read(buf)) != -1){
//                fos.write(buf, 0, len);
//            }
//        }
//    }

//    @SneakyThrows
//    public static void main(String[] args){
//
//        int len = 0;
//        char[] cbuf = new char[2];
//
//        String filePath = "/Users/xiaoweiyun/test.txt";
//
//        try(FileReader fr = new FileReader(filePath)) {
//            while ((len = fr.read(cbuf)) != -1){
//                System.out.print(new String(cbuf, 0, len));
//            }
//        }
//
//    }
//}

    @SneakyThrows
    public static void main(String[] args){

        int len = 0;
        char[] cbuf = new char[2];

        String filePath = "/Users/xiaoweiyun/test.txt";
        String outputFilePath = "/Users/xiaoweiyun/test2.txt";

        try(FileReader fr = new FileReader(filePath);
            FileWriter fw = new FileWriter(outputFilePath)) {
            while ((len = fr.read(cbuf)) != -1){
                fw.write(cbuf, 0, len);
            }
        }

    }
}
