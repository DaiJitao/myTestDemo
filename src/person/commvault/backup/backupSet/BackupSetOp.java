package person.commvault.backup.backupSet;

import person.commvault.backup.BackUpBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/16.
 */
public class BackupSetOp extends BackUpBase {
    public static void main(String[] args) throws Exception {
        System.out.println(new BackupSetOp().getBackupSetByClientId("4"));
    }

    // 通过客户端Id 查询所有的备份集
    public String getBackupSetByClientId(String clientId) throws Exception {
        if (clientId.length() == 0 || clientId == null) {
            throw new Exception("传入的clienId不合法");
        }
        String url = CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/Backupset";

        Map<String, String> params = new HashMap<>();
        params.put("clientId", clientId);
        String content = httpUtil.doGet(url, headers, params);
        return content;
    }
}
