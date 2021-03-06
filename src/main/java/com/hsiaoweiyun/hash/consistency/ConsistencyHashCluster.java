package com.hsiaoweiyun.hash.consistency;

import com.hsiaoweiyun.hash.Cluster;
import com.hsiaoweiyun.hash.Node;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;


public class ConsistencyHashCluster extends Cluster {

    private SortedMap<Long, Node> virNodes = new TreeMap();
    private static final int VIR_NODE_COUNT = 512;
    private static final String SPLIT = "#";

    public ConsistencyHashCluster() {
        super();
    }

    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
        IntStream.range(0, VIR_NODE_COUNT)
                .forEach(index -> {
                    long hash = doHash(node.getIp() + SPLIT + index);
                    virNodes.put(hash, node);
                });
    }

    @Override
    public void removeNode(Node node) {
        nodes.removeIf(o -> node.getIp().equals(o.getIp()));
        IntStream.range(0, VIR_NODE_COUNT)
                .forEach(index -> {
                    long hash = doHash(node.getIp() + SPLIT + index);
                    virNodes.remove(hash);
                });
    }

    @Override
    public Node get(String key) {
        long hash = doHash(key);
        long a = virNodes.lastKey();
        boolean c = (hash >= virNodes.lastKey());
        SortedMap<Long, Node> subMap = (hash >= virNodes.lastKey()) ? virNodes.tailMap(0L) : virNodes.tailMap(hash);
        if (subMap.isEmpty()) {
            return null;
        }
        long b = subMap.firstKey();
        return subMap.get(subMap.firstKey());
    }
}
