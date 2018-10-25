package person.commvault.backup;

import java.util.Map;

/**
 * Created by daijitao on 2018/10/12.
 */
public abstract class BackUpBase {
    protected static String IP;
    protected static String PORT;
    protected static String CommVault_SERVER_URL;
    static {
        Configuration configuration = new Configuration();
        try {
            configuration.init();
            IP = configuration.getIP("ip");
            PORT = configuration.getProperty("port");
            CommVault_SERVER_URL = "http://" + IP + ":" + PORT;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public HTTPUtil httpUtil = HTTPUtil.getInstance();
    public Map<String, String> headers = CommConf.getInstance().initHeaders();

}
