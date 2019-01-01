package com.hsiaoweiyun.hash.normal;

import com.hsiaoweiyun.hash.Cluster;
import com.hsiaoweiyun.hash.Node;

public class NormalHashCluster extends Cluster {

    public NormalHashCluster() {
        super();
    }

    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    @Override
    public void removeNode(Node node) {
        this.nodes.removeIf(o -> o.getIp().equals(node.getIp()) ||
                o.getDomain().equals(node.getDomain()));
    }

    @Override
    public Node get(String key) {
        long hash = doHash(key);
        long index = hash % nodes.size();
        return nodes.get((int) index);
    }
}