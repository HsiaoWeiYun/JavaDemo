package com.hsiaoweiyun.nio;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import lombok.SneakyThrows;

import java.nio.ByteBuffer;

public class BufferTest {

//    @SneakyThrows
//    public static void main(String[] args){
//        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
//
//        byteBuffer.put((byte) 0x00);
//        System.out.printf("writing, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        byteBuffer.put((byte) 0x01);
//        System.out.printf("writing, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        byteBuffer.put((byte) 0x02);
//        System.out.printf("writing, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        byteBuffer.put((byte) 0x03);
//        System.out.printf("writing, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        byteBuffer.put((byte) 0x04);
//        System.out.printf("writing, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//
//        System.out.printf("before flip, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        byteBuffer.flip();
//        System.out.printf("after flip, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//
//        while (byteBuffer.hasRemaining()){
//            System.out.println("0x" + HexBin.encode(new byte[]{byteBuffer.get()}));
//            System.out.printf("reading, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        }
//
//        byteBuffer.clear();
//        System.out.printf("after clear, position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//    }



//    @SneakyThrows
//    public static void main(String[] args){
//        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
//        byteBuffer.put((byte) 0x00);
//        byteBuffer.put((byte) 0x01);
//        byteBuffer.put((byte) 0x02);
//        byteBuffer.put((byte) 0x03);
//        byteBuffer.put((byte) 0x04);
//        System.out.printf("before get position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        byteBuffer.flip();
//        byteBuffer.get();
//        byteBuffer.get();
//        byteBuffer.get();
//        System.out.printf("after get position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//
//        byteBuffer.compact();
//        System.out.printf("after compact position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//
//        byteBuffer.flip();
//        System.out.printf("after flip position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//
//        System.out.println("0x" + HexBin.encode(new byte[]{byteBuffer.get()}));
//        System.out.println("0x" + HexBin.encode(new byte[]{byteBuffer.get()}));
//    }

//    @SneakyThrows
//    public static void main(String[] args) {
//        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
//        byteBuffer.put((byte) 0x00);
//        byteBuffer.put((byte) 0x01);
//        byteBuffer.put((byte) 0x02);
//        byteBuffer.put((byte) 0x03);
//        byteBuffer.put((byte) 0x04);
//
//        byteBuffer.flip();
//
//        System.out.println("0x" + HexBin.encode(new byte[]{byteBuffer.get()}));
//        System.out.printf("mark position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        byteBuffer.mark();
//        System.out.println("0x" + HexBin.encode(new byte[]{byteBuffer.get()}));
//        System.out.println("0x" + HexBin.encode(new byte[]{byteBuffer.get()}));
//
//        byteBuffer.reset();
//        System.out.printf("reset after flip position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
//        System.out.println("0x" + HexBin.encode(new byte[]{byteBuffer.get()}));
//
//    }


    @SneakyThrows
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
        byteBuffer.put((byte) 0x00);
        byteBuffer.put((byte) 0x01);
        byteBuffer.put((byte) 0x02);
        byteBuffer.put((byte) 0x03);
        byteBuffer.put((byte) 0x04);

        byteBuffer.flip();

        byteBuffer.get();
        byteBuffer.get();
        byteBuffer.get();

        System.out.printf("before rewind position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

        byteBuffer.rewind();

        System.out.printf("after rewind position: %d, limit: %d, capacity: %d%n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
    }
}
