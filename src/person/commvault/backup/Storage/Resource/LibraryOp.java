package person.commvault.backup.Storage.Resource;

import person.commvault.backup.BackUpBase;
import person.commvault.backup.Common;
import person.commvault.backup.HTTPUtil;

/**
 * Created by daijitao on 2018/10/15.
 */
public class LibraryOp extends BackUpBase{

    // 获取所有库
    public String getLb() {
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Library";
        result = httpUtil.doGet(url, headers, null);
        return result;
    }

    public  String getDetailLibraryById(String id) {
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Library/" + id;
        result = httpUtil.doGet(url, headers, null);
        return result;
    }
}
