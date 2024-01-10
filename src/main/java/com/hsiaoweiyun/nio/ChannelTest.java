package com.hsiaoweiyun.nio;

import lombok.SneakyThrows;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

//    @SneakyThrows
//    public static void main(String[] args) {
//        String filePath = "/Users/xiaoweiyun/test.txt";
//
//        ByteBuffer buf = ByteBuffer.allocate(2);
//
//        try (RandomAccessFile raf = new RandomAccessFile(new File(filePath), "r");
//             FileChannel fc = raf.getChannel()) {
//
//            int len = 0;
//
//            while ((len = fc.read(buf)) != -1) {
//                buf.flip();
//                System.out.print(new String(buf.array(), 0, len));
//                buf.clear();
//            }
//
//        }
//
//    }

//    @SneakyThrows
//    public static void main(String[] args) {
//        String filePath = "/Users/xiaoweiyun/test.txt";
//        String outputPath = "/Users/xiaoweiyun/testOutput.txt";
//
//        try (RandomAccessFile iraf = new RandomAccessFile(new File(filePath), "rw");
//             FileChannel ifc = iraf.getChannel();
//             RandomAccessFile oraf = new RandomAccessFile(new File(outputPath), "rw");
//             FileChannel ofc = oraf.getChannel()) {
//            ifc.transferTo(0, ifc.size(), ofc);
//        }
//    }

    @SneakyThrows
    public static void main(String[] args) {
        String filePath = "/Users/xiaoweiyun/test.txt";

        //ByteBuffer buf = ByteBuffer.allocate(2);

        try (RandomAccessFile raf = new RandomAccessFile(new File(filePath), "rw");
             FileChannel fc = raf.getChannel()) {

            int bufferSize = 2;

            long fileSize = fc.size();
            long position = 0;

            byte[] data = new byte[(int) fc.size()];

            MappedByteBuffer mappedBuffer = fc.map(FileChannel.MapMode.READ_ONLY, position, fc.size());
            mappedBuffer.get(data);
            System.out.print(new String(data));

//            while (position < fileSize) {
//                long remaining = fileSize - position;
//                long size = Math.min(remaining, bufferSize);
//
//                //一次指映射部分檔案內容
//                MappedByteBuffer mappedBuffer = fc.map(FileChannel.MapMode.READ_ONLY, position, size);
//
//                byte[] data = new byte[(int) size];
//                mappedBuffer.get(data);
//                position += size;
//
//                System.out.print(new String(data));
//            }

        }

    }

}
