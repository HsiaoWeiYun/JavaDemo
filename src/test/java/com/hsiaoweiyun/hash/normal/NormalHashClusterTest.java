package com.hsiaoweiyun.hash.normal;

import com.hsiaoweiyun.hash.Cluster;
import com.hsiaoweiyun.hash.Node;
import org.junit.Test;

import java.util.stream.IntStream;

public class NormalHashClusterTest {

    @Test
    public void test(){
        int DATA_COUNT = 10000;
        String PRE_KEY = "test_";

        Cluster cluster = new NormalHashCluster();
        cluster.addNode(new Node("www.gg1.yy.tw", "192.168.0.1"));
        cluster.addNode(new Node("www.gg2.yy.tw", "192.168.0.2"));
        cluster.addNode(new Node("www.gg3.yy.tw", "192.168.0.3"));
        cluster.addNode(new Node("www.gg4.yy.tw", "192.168.0.4"));

        IntStream.range(0, DATA_COUNT)
                .forEach(index -> {
                    Node node = cluster.get(PRE_KEY + index);
                    node.put(PRE_KEY + index, "Test Data");
                });

        //cluster.addNode(new Node("www.gg5.yy.tw", "192.168.0.5"));  //命中率只剩0.1978
        //cluster.removeNode(new Node("www.gg4.yy.tw", "192.168.0.4"));  //命中率只剩0.251

        System.out.println("資料分佈情況: ");
        cluster.getNodes().forEach(node -> {
            System.out.println("ip: " + node.getIp() + ",數量: " + node.getData().size());
        });

        long hitCount = IntStream.range(0, DATA_COUNT)
                .filter(index -> cluster.get(PRE_KEY + index).get(PRE_KEY + index) != null)
                .count();
        System.out.println("緩存命中率: " + hitCount * 1f / DATA_COUNT);


    }

}
