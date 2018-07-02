package cn.ctsi.bigdata.zk;

import org.apache.zookeeper.*;

import java.util.List;

public class SimpleZKClient {
    private static String connectString = "server01:2181,server01:2181,server01:2181";
    public static void main(String[] args) throws Exception{
        ZooKeeper zkClient = new ZooKeeper(connectString, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 我们自己时间的处理逻辑
                System.out.println(event.getPath() + " " + event.getType());
            }
        });


        /**
         * 创建者拥有所有的权限 ZooDefs.Ids.CREATOR_ALL_ACL
         *
         */
        zkClient.create("/daijitao", "hello".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        /**
         * true 代表用上一个wach
         */
        List<String> childrens = zkClient.getChildren("/", true);
        for (String children:childrens){
            System.out.println(children);
        }

        byte[] data = zkClient.getData("/", false,null);
        System.out.println(new String(data, "utf-8"));
    }
}
