package cn.ctsi.bigdata.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daijitao on 2018/6/29.
 */
public class ZKClientDemo {
    static class ClientBase{
        static int CONNECT_TIMEOUT = 2000;
    }
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String hostPort = "localhost:2181";
        String zpath = "/";

        List<String> zooChildren = new ArrayList<>();
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + " 事件!");
            }
        };

        // 创建一个与服务器的连接  并监控所触发的事件
        ZooKeeper zkCLient = new ZooKeeper(hostPort, ClientBase.CONNECT_TIMEOUT, watcher);
        //zkCLient.create("/daijitao/hello", "hello daijitao".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 创建一个目录节点
        // zkCLient.create("/testRootPath", "testrootdata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // zkCLient.create("/testRootPath/childFirst", "childData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //zkCLient.create("/daijitao", "hello daijitao".getBytes(), -1);
        System.out.println(zkCLient.getData("/daijitao", watcher, null).toString());
        System.out.println(zkCLient.getData("/testRootPath", watcher, null));
        System.out.println(zkCLient.getChildren("/testRootPath", watcher, null));
        zkCLient.close();

    }
}
