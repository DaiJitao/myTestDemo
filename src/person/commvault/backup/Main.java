package person.commvault.backup;

import person.commvault.backup.StoragePolicy.VirtualMachine;
import person.commvault.backup.subclient.SubClientOp;

import java.util.Properties;

/**
 * Created by daijitao on 2018/10/24.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();

        System.out.println("classLoader:");
        //System.out.println(Main.class.getResource("test.properties"));// 加载类所在的目录文件
        //String test = Main.class.getResource();
        prop.load(Main.class.getResourceAsStream("conf.properties"));// 加载类所在的目录文件
        System.out.println(prop.getProperty("retries"));
        //System.out.println(Main.class.getClassLoader().getResource("")); // 加载根项目下的文件myTestDemo/name.filetype
    }


}
