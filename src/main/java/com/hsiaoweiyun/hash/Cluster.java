package com.hsiaoweiyun.hash;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

@Data
public abstract class Cluster {

    protected List<Node> nodes;

    public Cluster() {
        this.nodes = new ArrayList<>();
    }

    public abstract void addNode(Node node);

    public abstract void removeNode(Node node);

    public abstract Node get(String key);


    protected long doHash(@NonNull final String key){
        CRC32 crc32 = new CRC32();
        crc32.update(key.getBytes());
        return crc32.getValue();
    }

}
