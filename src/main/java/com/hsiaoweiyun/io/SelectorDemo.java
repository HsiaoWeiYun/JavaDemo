package com.hsiaoweiyun.io;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {

    @SneakyThrows
    public static void main(String[] args){
        try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open()){

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8888));

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                //selector.select(); 監控所有已註冊channel, 當任一channel準備就緒才會返回, 並加入selectedKeys
                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }

                System.out.println("");
                System.out.println("readyChannels: " + readyChannels);

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isAcceptable()) {
                        System.out.println("處理連接事件");
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);

                        System.out.println("註冊讀事件");
                        client.register(selector, SelectionKey.OP_READ);
                    }else if (key.isReadable()) {
                        System.out.println("處理讀事件");
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = client.read(buffer);

                        System.out.println("bytesRead: " + bytesRead);
                        if (bytesRead > 0) {
                            buffer.flip();
                            System.out.println("接收到資料：" +new String(buffer.array(), 0, bytesRead));

                            System.out.println("註冊寫事件");
                            client.register(selector, SelectionKey.OP_WRITE);
                        }else if (bytesRead < 0) {
                            System.out.println("關閉連接");
                            client.close();
                        }
                    }else if (key.isWritable()) {
                        System.out.println("處理寫事件");
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.wrap("Hello, Client!".getBytes());
                        client.write(buffer);
                        System.out.println("已發送");

                        System.out.println("註冊讀事件");
                        client.register(selector, SelectionKey.OP_READ);
                    }
                    keyIterator.remove();
                }
            }

        }
    }
}
