package person.commvault.backup.Storage.Resource;

import person.commvault.backup.Common;
import person.commvault.backup.HTTPUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/15.
 */
public class MediaAgentOp {
    private HTTPUtil httpUtil = null;
    public MediaAgentOp () {
        httpUtil = HTTPUtil.getInstance();
    }

    public static void main(String[] args) {
        MediaAgentOp ma = new MediaAgentOp();
        System.out.println(ma.getMABylibraryId("7"));
    }
    // 获取所有MA
    public String getMA(){
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/MediaAgent";
        result = httpUtil.doGet(url, Common.getInstance().initHeaders(), null);
        return result;
    }
    // 获取单个库下的MA
    public String getMABylibraryId(String libraryId) {
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/MediaAgent";
        Map<String, String> params = new HashMap<>();
        params.put("libraryId", libraryId);
        result = httpUtil.doGet(url, Common.getInstance().initHeaders(), params);
        return result;
    }
}
