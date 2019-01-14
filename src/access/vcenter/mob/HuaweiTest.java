package access.vcenter.mob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HuaweiTest {

	private static String HEADER_CONTENT_TYPE ="application/json;charset=UTF-8";
	public static void main(String[] args) {
		System.out.println("输入参数: java -jar hweiapitest.jar http://192.168.13.250:7070 admin P@ssw0rd@ccc");

		try {
			URL r = new URL("http://192.168.13.250:7070/aa/1");
			System.out.println("path="+r.getPath());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			String url="",username="",password="";
			if(args.length >2) {
				url = args[0];
				username = args[1];
				password = getSHA256Password(args[2]);
			}
//
//			String url = "http://192.168.13.250:7070";
//
//			String username = "admin";
//	        String password = getSHA256Password("P@ssw0rd@ccc");


	        //登录华为vrm，获取命令操作需要的token
	        String token=getServiceInstance(username,password,url);
	        String version = getVersions(url+"/service/versions");

	        System.out.println("---token:"+token+" version:"+version);

//	        String headerAccept = "application/json;version="+version+";charset=UTF-8";
//			//v5.1代表华为虚拟化版本
//	        Map<String,String> property=new HashMap<String,String>();
//			property.put("Accept", headerAccept);
//			property.put("Content-Type", HEADER_CONTENT_TYPE);
//			property.put("X-Auth-Token", token);
//
//			String hostUrn = "urn:sites:53AA09F6:hosts:183";
//			String siteId = "53AA09F6";
//
//			String body="[{\"urn\":\""+hostUrn+"\",\"metricId\":[\"cpu_info\"]}]";
//			String bodyProductorName="[{\"urn\":\""+hostUrn+"\",\"metricId\":[\"product_mfg\"]}]";
//			String bodyProductorVersion="[{\"urn\":\""+hostUrn+"\",\"metricId\":[\"product_name\"]}]";
//			String bodyProductorSerial="[{\"urn\":\""+hostUrn+"\",\"metricId\":[\"product_serial\"]}]";
//
//			String resultCPUInfo=sendPost(url+"/service/sites/"+siteId+"/monitors/objectmetric-realtimedata",property,body);
//	        String resultProductorName=sendPost(url+"/service/sites/"+siteId+"/monitors/objectmetric-realtimedata",property,bodyProductorName);
//	        String resultProductorVersion=sendPost(url+"/service/sites/"+siteId+"/monitors/objectmetric-realtimedata",property,bodyProductorVersion);
//	        String resultProductorSerial=sendPost(url+"/service/sites/"+siteId+"/monitors/objectmetric-realtimedata",property,bodyProductorSerial);
//
//
//	        System.out.println("\nresultCPUInfo:\n  "+resultCPUInfo);
//	        System.out.println("resultProductorName:\n  "+resultProductorName);
//	        System.out.println("resultProductorVersion:\n  "+resultProductorVersion);
//	        System.out.println("resultProductorSerial:\n  "+resultProductorSerial);
//
//
//	        String cpuValue=((JSONObject)(((JSONArray) JSONArray.parse(((JSONObject)JSONObject.parse(resultCPUInfo)).getString("items"))).get(0))).getString("value");
//	        String metricValue=((JSONObject)(((JSONArray)JSONArray.parse(cpuValue)).get(0))).getString("metricValue");
//
//	        System.out.println("============metricValue:"+metricValue+"===================");
//	        if(null != metricValue && metricValue.length() > 0) {
//	        	System.out.println("============metricValue.length:"+metricValue.length());
//	        }


        } catch (Exception e) {
        	e.printStackTrace();
        }

	}

	//对密码进行HA-256加密
		private static String getSHA256Password(String password) {
			MessageDigest messageDigest;
	        String encodePassword = "";
	        try {
	            messageDigest = MessageDigest.getInstance("SHA-256");
	            messageDigest.update(password.getBytes("UTF-8"));
	            encodePassword = byte2Hexadecimal(messageDigest.digest());
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return encodePassword;
		}

		//将加密后的密码转化为字符串
		private static String byte2Hexadecimal(byte[] bytes){
	        StringBuffer stringBuffer = new StringBuffer();
	        String temp = null;
	        for (int i=0;i<bytes.length;i++){
	            temp = Integer.toHexString(bytes[i] & 0xFF);
	            if (temp.length()==1){
	                stringBuffer.append("0");
	            }
	            stringBuffer.append(temp);
	        }
	        return stringBuffer.toString();
	    }

		/**
		 * 登录vrm,获取token
		 * @return token 登录需要使用的token
		 * @return null 当未获取到token时返回空
		 *
		 * */
		public static String getServiceInstance(String username,String password,String serviceUrl) {

			String token;
			String result = null;
			int maxRetry = 3;

			Map<String, List<String>> header = null;//用于保存返回的header信息

			String param = null;
			Map<String,String> property=new HashMap<String,String>();
			String version=null;
			try {
				version = getVersions(serviceUrl+"/service/versions");
//				version = "{\"loginUri\":\"/service/session\",\"version\":\"v5.0\"}";
				version = "v5.0";
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("--------version:\n"+version);
	    	String headerAccept="application/json;version="+version+";charset=UTF-8";
			//v5.1代表虚拟化版本
			property.put("Accept", headerAccept);
			property.put("Content-Type", HEADER_CONTENT_TYPE);
			property.put("Accept-Language", "zh_CN");
			property.put("X-Auth-UserType", "0");
			property.put("X-Auth-User", username);
			property.put("X-Auth-Key", password);


			while(header == null && maxRetry > 0) {
				try {
					header =sendPost2GetHeader(serviceUrl+"/service/session", property, param);
				} catch (Exception e) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException interruptEx) {
						interruptEx.printStackTrace();
					}
				}
				maxRetry --;
			}
			if(null==header|| header.isEmpty()) {
				token = null;}
			else {
				token=header.get("X-Auth-Token").get(0);
			}
			return token;
		}

		/**
	     * 利用HTTP发送 URL的post请求，获取header信息
	     *
	     * @param url 请求 URL
	     * @param param 请求时用的body体
	     * @param property 请求时发送的header
	     * @return 请求返回的header信息
	     */
	    public static Map<String, List<String>> sendPost2GetHeader(String url,Map<String,String> property,String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        Map<String, List<String>> header = null;
	        try {
	            URL realUrl = new URL(url);
	            URLConnection conn = realUrl.openConnection();
	            for(String key:property.keySet())
	            	conn.setRequestProperty(key, property.get(key));
	            //以下两个参数必须设置
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            out = new PrintWriter(conn.getOutputStream());
	            out.print(param);
	            out.flush();
//	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	            String sss = IOUtils.toString(conn.getInputStream());




	            header = conn.getHeaderFields();
	        } catch (Exception e) {
//	        	logger.error("---->huawei物理资源同步:POST请求异常");
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }

			return header;
	    }

	    /**
	     * 获取sites列表
	     * @return sites
	     *
	     *
	     * */
	    public static String getSites(String serviceUrl,String token) {

	    	if(serviceUrl==null || serviceUrl.trim().length()==0) {
//	    		logger.error("------>huawei物理资源同步:缺少serviceURL");
	    		return null;
	    	}

	    	String result=null;

	    	Map<String,String> property=new HashMap<String,String>();

	    	String version=null;
			try {
				version = getVersions(serviceUrl+"/service/versions");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	String headerAccept="application/json;version="+version+";charset=UTF-8";
			//v5.1代表虚拟化版本
			property.put("Accept", headerAccept);
			property.put("Content-Type", HEADER_CONTENT_TYPE);
			property.put("X-Auth-Token", token);

			String url=serviceUrl+"/service/sites/";
			result=sendGet(url,property);

	    	return result;
	    }

	    /**
	     * 获取华为虚拟化版本信息
	     */
	    public static String getVersions(String url)
	            throws MalformedURLException, IOException {
	        URL requestUrl = new URL(url);
	        HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
	        conn.setReadTimeout(6000);
	        conn.setConnectTimeout(6000);
	        conn.setRequestProperty("Accept", "application/json; charset=UTF-8");

	        int retry = 3;
	        String respMsg = null;
	        while(retry > 0) {
	            try {
	                conn.connect();
	                if(conn.getResponseCode() >= 400) {
	                    String errorMsg = IOUtils.toString(conn.getErrorStream());
	                    break;
	                }
	                respMsg = IOUtils.toString(conn.getInputStream());
	                break;
	            } catch (IOException ex) {
	                ex.printStackTrace();
	                retry -= 1;
	            }
	        }
	        if(respMsg == null)
	            throw new RuntimeException("华为反向纳管: 无法获取华为虚拟化版本信息");

	        String version = null;
	        double versionNum = Double.MIN_NORMAL;
	        JSONArray versions = JSONObject.parseObject(respMsg).getJSONArray("versions");
	        for(int i = 0; i < versions.size(); i ++) {
	            String vStr = versions.getJSONObject(i).getString("version");
	            double vNum = Double.parseDouble(vStr.replace("v", ""));
	            if(vNum > versionNum) {
	                version = vStr;
	                versionNum = vNum;
	            }
	        }
	        return version;
	    }

	    /**
	     * 利用HTTP发送GET请求
	     *
	     * @param url 请求使用的URL
	     * @param property 请求使用的header
	     * @return result 返回值
	     */
	    public static String sendGet(String url,Map<String,String> property) {
	        String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = url;
	            URL realUrl = new URL(urlNameString);
	            URLConnection connection = realUrl.openConnection();
	            for(String key:property.keySet())
	            	connection.setRequestProperty(key, property.get(key));
	            connection.connect();
	            Map<String, List<String>> header = connection.getHeaderFields();
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
//	        	logger.error("---->huawei物理资源同步:GET请求异常");
	            e.printStackTrace();
	        }
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

	    /**
	     * 利用HTTP向指定 URL 发送POST方法的请求
	     *
	     * @param url 发送请求的 URL
	     * @param param 请求body参数
	     * @param property 请求的header属性
	     * @return 所代表远程资源的响应结果
	     */
	    public static String sendPost(String url,Map<String,String> property,String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            for(String key:property.keySet())
	            	conn.setRequestProperty(key, property.get(key));
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            //发送 POST 请求出现异常！
//	        	logger.error("---->huawei请求:POST请求出现异常");
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }
	}