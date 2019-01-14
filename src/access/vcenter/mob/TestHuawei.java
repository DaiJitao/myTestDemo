package access.vcenter.mob;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by daijitao on 2019/1/10.
 */
public class TestHuawei {
    private static String HEADER_CONTENT_TYPE = "application/json;charset=UTF-8";


    public static void main(String[] args) {
        System.out.println("测试华为FCAPI 获取虚机属性 输入参数: java -jar hweiapitest.jar http://192.168.13.250:7070 admin P@ssw0rd@ccc siteId vmId");

        try {
            String url = "";
            String username = "";
            String password = "";
            String siteId = "";
            String vmId = "";

            url = "http://192.168.13.250:7070";
            username = "admin";
            password = "P@ssw0rd@ccc";
            siteId = "36A6070E";
            vmId = "i-0000000D";
            String token = getServiceInstance(username, password, url);
            if (token == null) {
                System.out.println("无法获取token");
                throw new Exception("token is" + token);
            }
            System.out.println("00000000000000000000000000000000000000000");
            String version = getVersions(url + "/service/versions");
            System.out.println("---token:" + token + " version:" + version);
            String headerAccept = "application/json;version=" + version + ";charset=UTF-8";
            Map<String, String> property = new HashMap();
            property.put("Accept", headerAccept);
            property.put("Content-Type", HEADER_CONTENT_TYPE);
            property.put("X-Auth-Token", token);
            String resultInfo = sendGet(url + "/service/sites/" + siteId + "/vms/" + vmId, property);
            System.out.println("====>result:" + resultInfo);
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    private static String getSHA256Password(String password) {
        String encodePassword = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes("UTF-8"));
            encodePassword = byte2Hexadecimal(messageDigest.digest());
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        return encodePassword;
    }

    private static String byte2Hexadecimal(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;

        for (int i = 0; i < bytes.length; ++i) {
            temp = Integer.toHexString(bytes[i] & 255);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }

            stringBuffer.append(temp);
        }

        return stringBuffer.toString();
    }

    public static String getServiceInstance(String username, String password, String serviceUrl) {
        int maxRetry = 3;
        Map<String, List<String>> header = null;
        String param = null;
        Map<String, String> property = new HashMap();
        String version = null;

        try {
            version = getVersions(serviceUrl + "/service/versions");
            version = "v5.0";
        } catch (MalformedURLException var15) {
            var15.printStackTrace();
        } catch (IOException var16) {
            var16.printStackTrace();
        }

        System.out.println("--------version:\n" + version);
        String headerAccept = "application/json;version=" + version + ";charset=UTF-8";
        property.put("Accept", headerAccept);
        property.put("Content-Type", HEADER_CONTENT_TYPE);
        property.put("Accept-Language", "zh_CN");
        property.put("X-Auth-UserType", "0");
        property.put("X-Auth-User", username);
        property.put("X-Auth-Key", password);

        for (; header == null && maxRetry > 0; --maxRetry) {
            try {
                header = sendPost2GetHeader(serviceUrl + "/service/session", property, param);
            } catch (Exception var14) {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException var13) {
                    var13.printStackTrace();
                }
            }
        }

        String token;
        if (header != null && !header.isEmpty()) {
            token = (String) ((List) header.get("X-Auth-Token")).get(0);
        } else {
            token = null;
        }

        return token;
    }

    public static Map<String, List<String>> sendPost2GetHeader(String url, Map<String, String> property, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        Map header = null;

        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            Iterator var9 = property.keySet().iterator();

            String sss;
            while (var9.hasNext()) {
                sss = (String) var9.next();
                conn.setRequestProperty(sss, (String) property.get(sss));
            }

            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            sss = IOUtils.toString(conn.getInputStream());
            header = conn.getHeaderFields();
        } catch (Exception var18) {
            var18.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    (in).close();
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }

        return header;
    }

    public static String getSites(String serviceUrl, String token) {
        if (serviceUrl != null && serviceUrl.trim().length() != 0) {
            String result = null;
            Map<String, String> property = new HashMap();
            String version = null;

            try {
                version = getVersions(serviceUrl + "/service/versions");
            } catch (MalformedURLException var7) {
                var7.printStackTrace();
            } catch (IOException var8) {
                var8.printStackTrace();
            }

            String headerAccept = "application/json;version=" + version + ";charset=UTF-8";
            property.put("Accept", headerAccept);
            property.put("Content-Type", HEADER_CONTENT_TYPE);
            property.put("X-Auth-Token", token);
            String url = serviceUrl + "/service/sites/";
            result = sendGet(url, property);
            return result;
        } else {
            return null;
        }
    }

    public static String getVersions(String url) throws MalformedURLException, IOException {
        URL requestUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
        conn.setReadTimeout(6000);
        conn.setConnectTimeout(6000);
        conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
        int retry = 3;
        String respMsg = null;

        String version;
        while (retry > 0) {
            try {
                conn.connect();
                if (conn.getResponseCode() >= 400) {
                    version = IOUtils.toString(conn.getErrorStream());
                } else {
                    respMsg = IOUtils.toString(conn.getInputStream());
                }
                break;
            } catch (IOException var13) {
                var13.printStackTrace();
                --retry;
            }
        }

        if (respMsg == null) {
            throw new RuntimeException("华为反向纳管: 无法获取华为虚拟化版本信息");
        } else {
            version = null;
            double versionNum = 2.2250738585072014E-308D;
            JSONArray versions = JSONObject.parseObject(respMsg).getJSONArray("versions");

            for (int i = 0; i < versions.size(); ++i) {
                String vStr = versions.getJSONObject(i).getString("version");
                double vNum = Double.parseDouble(vStr.replace("v", ""));
                if (vNum > versionNum) {
                    version = vStr;
                    versionNum = vNum;
                }
            }

            return version;
        }
    }

    public static String sendGet(String url, Map<String, String> property) {
        String result = "";
        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            Iterator var8 = property.keySet().iterator();

            while (var8.hasNext()) {
                String key = (String) var8.next();
                connection.setRequestProperty(key, (String) property.get(key));
            }

            connection.connect();
            Map<String, List<String>> header = connection.getHeaderFields();

            String line;
            for (in = new BufferedReader(new InputStreamReader(connection.getInputStream())); (line = in.readLine()) != null; result = result + line) {
                ;
            }
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            }

        }

        return result;
    }

    public static String sendPost(String url, Map<String, String> property, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            Iterator var9 = property.keySet().iterator();

            String line;
            while (var9.hasNext()) {
                line = (String) var9.next();
                conn.setRequestProperty(line, (String) property.get(line));
            }

            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            for (in = new BufferedReader(new InputStreamReader(conn.getInputStream())); (line = in.readLine()) != null; result = result + line) {
                ;
            }
        } catch (Exception var18) {
            var18.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }

        return result;
    }
}

