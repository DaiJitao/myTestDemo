package person.commvault.backup.subclient;

import access.vcenter.mob.Statistics2;
import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.Login;
import person.commvault.backup.BackUpBase;
import person.commvault.backup.Common;
import person.commvault.backup.HTTPUtil;
import person.commvault.backup.Token;
import person.commvault.backup.utils.LoginUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/15.
 */
public class SubClientOp extends BackUpBase {

    public static void main(String[] args) throws Exception {

        System.out.println("所有的虚机");
        Statistics2.printAllVms();
        SubClientOp subClientOp = new SubClientOp();
        String result = subClientOp.getSubclientByClientName("192.168.56.128");

        System.out.println("所有的子客户端 " + result);
        String result2 = subClientOp.getVmBySubClientID("73");
        System.out.println("子客户端关联的虚机 " + result2);
        result2 = subClientOp.getSubclientBackupHistory("4", "73");
        System.out.println(JSONObject.parseObject(result2));

    }

    public String getSubclientByClientId(String clientId) throws Exception {
        if (null == clientId || clientId.length() == 0) {
            throw new Exception("传入的clientID错误");
        }
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient";
        Map<String, String> params = new HashMap<>();
        params.put("clientId", clientId);
        result = httpUtil.doGet(url, headers, params);
        return result;
    }

    public String getSubclientByClientName(String clientName) throws Exception {
        if (null == clientName || clientName.length() == 0) {
            throw new Exception("传入的clientID错误");
        }
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient";
        Map<String, String> params = new HashMap<>();
        params.put("clientName", clientName);
        result = httpUtil.doGet(url, headers, params);
        return result;
    }

    public String runBackUp(String subclientId, BackupLevel level) throws Exception {
        if (null == subclientId || subclientId.length() == 0) {
            throw new Exception("传入的subclientId错误");
        }

        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient/" + subclientId + "/action/backup?backupLevel=" + level.getLevel();

        String result = "";
        result = httpUtil.doPostJson(url, headers, null);
        return result;
    }


    //查询子客户端关联的某一个虚机
    public String getVmBySubClientID(String subclientId) {
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient/"+ subclientId +"/Browse";
        Map<String, String> params = new HashMap<>();
        params.put("path", "%5c" + "503c4f69-5bb7-9f4b-57fb-d0d98114f837");
        params.put("showDeletedFiles", "true");
        params.put("vsDiskBrowse", "true");
        String result = httpUtil.doGet(url, headers, params);
        return result;
    }

    public String createSubClient() {
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient";
        return "";
    }

    public String getSubclientBackupHistory(String clientId, String subclientId) throws Exception{
        if (null == clientId || clientId.length() == 0){
            throw new Exception("error clientId传入参数为空");
        }
        if (null == subclientId || subclientId.length() == 0) {
            throw new Exception("error subclientId传入参数为空");
        }
        String path = "E:\\intell_space\\myTestDemo\\src\\person\\commvault\\backup\\subclient\\subclientBackupHistory.xml";
        String xml = LoginUtil.getXml(path);
        xml = LoginUtil.replaceXmlValue(xml, "clientId", clientId);
        xml = LoginUtil.replaceXmlValue(xml, "subclientId", subclientId);
        String content = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/jobs";
        content = httpUtil.doPostXML(url, headers.get("Authtoken"), xml);
        return content;
    }
}
