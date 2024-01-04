package com.hsiaoweiyun.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataStream {

//    @SneakyThrows
//    public static void main(String[] args) {
//
//        String filePath = "/Users/xiaoweiyun/testData.txt";
//
//        try(FileOutputStream fos = new FileOutputStream(filePath);
//            DataOutputStream dos = new DataOutputStream(fos)){
//
//            dos.writeBoolean(true);
//            dos.flush();
//            dos.writeUTF("ABC123456");
//            dos.flush();
//            dos.writeInt(100);
//            dos.flush();
//        }
//
//        try(FileInputStream fis = new FileInputStream(filePath);
//            DataInputStream dis = new DataInputStream(fis)){
//            System.out.println(dis.readBoolean());
//            System.out.println(dis.readUTF());
//            System.out.println(dis.readInt());
//        }
//
//    }

    @SneakyThrows
    public static void main(String[] args) {
        String filePath = "/Users/xiaoweiyun/testData2.txt";

        try(FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(new Test("aabb", 30));
        }

        try(FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis)){

            System.out.println((Test)ois.readObject());

        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Test implements Serializable {
        private String name;

        private int age;
    }

}
