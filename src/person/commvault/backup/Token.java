package person.commvault.backup;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/9.
 */
public class Token {
    private static String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Login";
    private static String loginOutUrl = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Logout";

    public static void main(String[] args) throws Exception {
        //将map转换成jsonObject
        Map<String, String> itemMap = new HashMap<>();
        itemMap.put("Accept", "application/json");
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));
        System.out.println(itemJSONObj.toJSONString());
        System.out.println(JSON.toJSONString(itemMap));
        System.out.println("========================>>>>");
        System.out.println(getToken());
    }

    public static void loginOut(String token) {

    }

    public static String getToken() {

        Map<String, String> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password", "UEBzc3cwcmQ=");

        String result = new Token().jsonPost(url, params);
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
        if (token == null){
            try {
                throw new Exception("token is null: error! cause:" + jsonObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return token;
    }


    /**
     * 发送HttpPost请求
     *
     * @param strURL 服务地址
     * @param params
     * @return 成功:返回json字符串<br/>
     */
    public String jsonPost(String strURL, Map<String, String> params) {
        OutputStreamWriter out = null;
        InputStream is = null;
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // 允许写出
            connection.setDoInput(true); // 允许读入
            connection.setUseCaches(false);// 不适用缓存
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            out.append(JSON.toJSONString(params));//JSONUtil.object2JsonString(params));
            out.flush();


            int code = connection.getResponseCode();

            if (code == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }

            // 读取响应
            int length = connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length]; // 目的数组
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                return result;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";

    }
}
