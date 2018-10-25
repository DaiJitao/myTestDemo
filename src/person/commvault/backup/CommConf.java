package person.commvault.backup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/15.
 */
public class CommConf {
    private static CommConf instance = new CommConf();
    private CommConf() {

    }
    public static CommConf getInstance(){
        return instance;
    }

    public Map<String, String> initUser(){
        Map<String, String> params = new HashMap<>();
        Configuration configuration = new Configuration();
        configuration.init();
        String username = configuration.getProperty("username");
        String pwd = configuration.getProperty("password");
        params.put("username", username);
        params.put("password", pwd); // UEBzc3cwcmQ=
        return params;
    }

    public Map<String, String> initHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        String token = Token.getToken(initUser());
        headers.put("Authtoken", token);
        return headers;
    }
}
