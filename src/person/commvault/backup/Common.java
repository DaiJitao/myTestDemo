package person.commvault.backup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/15.
 */
public class Common {
    private static Common instance = new Common();
    private Common() {

    }
    public static Common getInstance(){
        return instance;
    }

    public Map<String, String> initUser(){
        Map<String, String> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password", "UEBzc3cwcmQ=");
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
