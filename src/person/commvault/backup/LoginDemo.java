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
public class LoginDemo {
    private static String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Login";

    public static void main(String[] args) throws InterruptedException {
        //将map转换成jsonObject
        Map<String, String> itemMap = new HashMap<>();
        itemMap.put("Accept", "application/json");
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));
        System.out.println(itemJSONObj.toJSONString());
        System.out.println(JSON.toJSONString(itemMap));
        System.out.println("========================>>>>");
        Map<String, String> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password","UEBzc3cwcmQ=");
        for (int i = 0; i < 10000; i++) {
            Thread.sleep(0);
            String result = LoginDemo.jsonPost(url, params);
            if (JSONObject.parseObject(result).containsKey("token")){
                System.out.println();
                System.out.println((i + 1) + "次");
                System.out.println(JSONObject.parseObject(result).get("token"));
            } else {
                break;
            }
        }


    }


    /**
     * 发送HttpPost请求
     *
     * @param strURL 服务地址
     * @param params
     * @return 成功:返回json字符串<br/>
     */
    public static String jsonPost(String strURL, Map<String, String> params) {
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码

            out.append(JSON.toJSONString(params));//JSONUtil.object2JsonString(params));
            out.flush();
            out.close();

            int code = connection.getResponseCode();
            InputStream is = null;
            if (code == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }

            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
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

        }
        return "error"; // 自定义错误信息
    }

}
