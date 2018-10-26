package person.commvault.backup;


import com.alibaba.fastjson.JSONObject;
import person.commvault.backup.utils.HTTPUtil;

import java.util.Map;

/**
 * Created by daijitao on 2018/10/9.
 */
public class Token extends BackUpBase{
    private static String url = CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/Login";
    private static String loginOutUrl = CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/Logout";

    public static void main(String[] args) {
        CommConf common = CommConf.getInstance();
        Map<String, String> params = common.initUser();
        System.out.println(Token.getToken(params));
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
