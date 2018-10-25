package person.commvault.backup.Storage.Resource;

import person.commvault.backup.BackUpBase;

/**
 * Created by daijitao on 2018/10/15.
 */
public class LibraryOp extends BackUpBase{

    // 获取所有库
    public String getLb() {
        String result = "";
        String url = CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/Library";
        result = httpUtil.doGet(url, headers, null);
        return result;
    }

    public  String getDetailLibraryById(String id) {
        String result = "";
        String url = CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/Library/" + id;
        result = httpUtil.doGet(url, headers, null);
        return result;
    }
}
