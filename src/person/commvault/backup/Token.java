package person.commvault.backup;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/9.
 */
public class Token {
    private static String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Login";
    private static String loginOutUrl = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Logout";

    public static void main(String[] args) {
        System.out.println(Token.getToken(Common.getInstance().initUser()));
    }

    public static void loginOut(String token) {

    }

    /**
     *
     * @param params 用户名和密码
     * @return
     */
    public static String getToken(Map<String, String> params) {
        HTTPUtil httpUtil = HTTPUtil.getInstance();
        String result = httpUtil.doPostJsonLogin(url, params);
        if (result == null || result.length() == 0) {
            try {
                throw new Exception("连接服务器错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        String token = null;
        if (jsonObject.containsKey("token")) {
            token = jsonObject.get("token").toString();
        }
        if (token == null) {
            try {
                throw new Exception("token is null: error! cause:" + jsonObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return token;
    }
}
