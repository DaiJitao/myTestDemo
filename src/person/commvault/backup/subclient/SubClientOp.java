package person.commvault.backup.subclient;

import access.vcenter.mob.Statistics2;
import person.commvault.backup.BackUpBase;
import person.commvault.backup.Common;
import person.commvault.backup.HTTPUtil;

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
}
