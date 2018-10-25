package person.commvault.backup.StoragePolicy;

import com.alibaba.fastjson.JSONObject;
import person.commvault.backup.BackUpBase;

import java.util.Map;

/**
 * Created by daijitao on 2018/10/17.
 */
public class VirtualMachine extends BackUpBase {

    public static void main(String[] args) throws Exception {
        VirtualMachine virtualMachine = new VirtualMachine();
        String result = virtualMachine.getNICs("192.168.56.128", "73", "");
        System.out.println(JSONObject.parseObject(result));
        // applicationId 通过查询子客户端获取
        result = virtualMachine.getDISKs("106", "73", "", "503c4f69-5bb7-9f4b-57fb-d0d98114f837");
        System.out.println("磁盘 " + JSONObject.parseObject(result));
    }

    /**
     * @param clientName
     * @param subClientID
     * @param time        秒 可获取指定时间的网卡
     * @return
     */
    public String getNICs(String clientName, String subClientID, String time) throws Exception {
        if (null == clientName || clientName.length() == 0) {
            throw new Exception("");
        }
        String result = "";
        String url =  CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/DoBrowse";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<databrowse_BrowseRequest opType=\"browse\"><entity clientName=\"");
        stringBuilder.append(clientName);
        stringBuilder.append("\" subclientId=\"");
        stringBuilder.append(subClientID);
        stringBuilder.append("\"/><paths path=\"\\\\\"/><timeRange toTime=\"");
        stringBuilder.append(time);
        stringBuilder.append("\" /></databrowse_BrowseRequest>");
        String token = headers.get("Authtoken");
        result = httpUtil.doPostXML(url, token, stringBuilder.toString());
        return result;
    }

    /**
     * @param applicationId
     * @param subClientID
     * @param time
     * @param guid          vcenter层面的instanceUuid
     * @return
     * @throws Exception
     */
    public String getDISKs(String applicationId, String subClientID, String time, String guid) throws Exception {
        if (null == applicationId || applicationId.length() == 0) {
            throw new Exception("");
        }
        String result = "";
        String url = CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/DoBrowse";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<databrowse_BrowseRequest opType=\"0\"><entity subclientId=\"");
        stringBuilder.append(subClientID);
        stringBuilder.append("\" applicationId=\"");
        stringBuilder.append(applicationId);
        stringBuilder.append("\"/><paths path=\"\\\\");
        stringBuilder.append(guid);
        stringBuilder.append("\"/><timeRange toTime=\"");
        stringBuilder.append(time);
        stringBuilder.append("\"/><options showDeletedFiles=\"1\" vsDiskBrowse=\"1\"/><mode mode=\"6\"/></databrowse_BrowseRequest>");
        String token = headers.get("Authtoken");
        result = httpUtil.doPostXML(url, token, stringBuilder.toString());
        return result;
    }


    public Map<String, String> getParams(JSONObject jsonObject) throws Exception {
        if (null == jsonObject) {
            throw new Exception("jsonObject is NULL: error ");
        }
        return null;
    }
}

