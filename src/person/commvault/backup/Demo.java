package person.commvault.backup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.util.hash.Hash;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/10.
 */
public class Demo {
    public static String token = null;
    private static String subClientUrl = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient";
    static {
        token = LoginDemo.getToken();
    }
    public static void main(String[] args) {
        Map<String, String> headers = new HashMap<>();

        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authtoken",token);

        Map<String, String> params = new HashMap<>();
        params.put("clientId", "4");

        Demo demo = new Demo();
        demo.listSubclient(headers, params);



    }

    //获取所有的子客户端
    public void listSubclient(Map<String, String> headers, Map<String, String> params){
        String result = Demo.sendGet("http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient", headers, params);
        System.out.println(JSONObject.parseObject(result));
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("subClientProperties");
        int len = jsonArray.size();
        Map<String, JSONObject> hashMap = new HashMap<>();
        for (int i = 0; i < len ; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String subclientName = object.getJSONObject("subClientEntity").getString("subclientName");
            if (subclientName.contains("test_instanceUUID")) {
                hashMap.put(subclientName,object);
                System.out.println(object.toJSONString());
                break;
            }

        }
    }


    public static String jsonPost( String strURL,Map<String, String> headers, Map<String, String> params) {
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", headers.get("Accept")); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", headers.get("Content-Type")); // 设置发送数据的格式
            connection.setRequestProperty("Authtoken", headers.get("Authtoken"));
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码

            out.append(JSON.toJSONString(params));
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

        }
        return "error"; // 自定义错误信息
    }


    //GET 请求
    public static String sendGet(String url, Map<String, String> headers, Map<String, String> params) {
        String result = "";
        BufferedReader in = null;
        try {
            String sendParam = ParamUtil.getParam(params);
            String urlNameString = url + "?" + sendParam;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept", headers.get("Accept")); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", headers.get("Content-Type")); // 设置发送数据的格式
            connection.setRequestProperty("Authtoken", headers.get("Authtoken"));
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
