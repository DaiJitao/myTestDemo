package person.commvault.backup.StoragePolicy;

import com.alibaba.fastjson.JSONObject;
import person.commvault.backup.BackUpBase;
import person.commvault.backup.Common;
import person.commvault.backup.HTTPUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/12.
 */
public class StoragePolicyOP extends BackUpBase implements StoragePolicyBase {
    HTTPUtil httpUtil = HTTPUtil.getInstance();
    Map<String, String> headers = Common.getInstance().initHeaders();

    public static void main(String[] args) {
        StoragePolicyOP op = new StoragePolicyOP();
        System.out.println(op.getStoragePolicy());
    }

    /**
     * 只列出 {"storagePolicyName":"Default Virtualization plan","storagePolicyId":11}
     *
     * @return
     */
    @Override
    public String getStoragePolicy() {
        String content = httpUtil.doGet(GETSPUrl, headers, null);
        return content;
    }


    public String getStoragePolicyById(String id) throws Exception {
        if (id.length() == 0 || id == null) {
            throw new Exception("传入的存储策略id不合法");
        }
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/StoragePolicy/" + id;
        String content = httpUtil.doGet(url, headers, null);
        return content;
    }

    /**
     * @param sp 库和MA一一对应
     * @return
     */
    public String createStoragePolicy(StoragePolicy sp) {
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/StoragePolicy";
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(sp);
        params.put("jsonType", jsonObject.toJSONString());
        result = httpUtil.doPostJson(url, headers, params);
        return result;
    }

    @Override
    public boolean updateStoragePolicy() {
        return false;
    }

    @Override
    public boolean deleteStoragePolicyByName(String name) throws Exception {
        if (name.length() == 0 || name == null) {
            throw new Exception("传入的存储策略name不合法");
        }

        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/StoragePolicy/" + name;
        String content = httpUtil.doDelete(url, headers, null);
        System.out.println(content);
        if (content.contains("errorMessage")) {
            return false;
        }
        return true;
    }


}
