package cn.ctsi.bigdata.zkdist;

import org.apache.zookeeper.ZooKeeper;

public class DistributedServer {
    private ZooKeeper zk = null;

    public  void getConnect(){
        zk = null;
    }

    public void registerServer(){

    }

    public static void main(String[] args) {
        //connntion
        DistributedServer server = new DistributedServer();
        server.getConnect();
        // 注册服务器

        //
    }
}
