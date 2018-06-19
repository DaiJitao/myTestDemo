package cn.ctsi.bigdata.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class SimpleZKClient {
    private static String connectString = "server01:2181,server01:2181,server01:2181";
    public static void main(String[] args) throws Exception{
        ZooKeeper zkClient = new ZooKeeper(connectString, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
        zkClient.create();

    }
}
