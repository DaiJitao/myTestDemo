package person.commvault.backup.subclient;

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
        String result = new SubClientOp().getSubclientByClientName("192.168.56.128");
        result = new SubClientOp().runBackUp("12", BackupLevel.FULL);
        System.out.println(result);
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

        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Subclient/" + subclientId +"/action/backup?backupLevel=" + level.getLevel();

        String result = "";
        result = httpUtil.doPostJson(url, headers, null);
        return result;
    }
}
