package person.commvault.backup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by daijitao on 2018/10/25.
 */
public class Configuration {
    private Properties properties = new Properties();
    public static void main(String[] args) throws Exception {
        //System.out.println(Main.class.getResource("test.properties"));// 加载类所在的目录文件
        //properties.load(Main.class.getResourceAsStream("1conf.properties"));// 加载类所在的目录文件
        //System.out.println(Main.class.getResourceAsStream("1conf.properties"));
        //System.out.println(Main.class.getClassLoader().getResource("")); // 加载根项目下的文件myTestDemo/name.filetype
        Configuration configuration = new Configuration();

        configuration.init("conf.properties");



    }

    private void init(String fileName) throws Exception {

        if (null == fileName || fileName.trim().length() == 0) {
            throw new Exception("文件名[" + fileName + "]不可为空");
        }
        InputStream in = Configuration.class.getResourceAsStream(fileName);
        if (in == null) {
            String path = Configuration.class.getResource("").getPath();
            throw new Exception("在目录["+ path + "]下找不到[" + fileName + "]文件");
        }
        this.properties.load(in);
        System.out.println(this.properties.getProperty("ip"));
    }
}
