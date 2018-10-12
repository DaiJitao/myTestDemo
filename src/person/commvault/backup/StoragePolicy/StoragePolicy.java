package person.commvault.backup.StoragePolicy;

import person.commvault.backup.BackUpBase;
import person.commvault.backup.HTTPUtil;
import person.commvault.backup.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/12.
 */
public class StoragePolicy  extends BackUpBase implements StoragePolicyBase{
    private static final String token = Token.getToken();
    private Map<String, String> headers = new HashMap<>();

    private void initHeaders() {
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authtoken", token);
    }

    public static void main(String[] args) throws Exception {
        StoragePolicy sp = new StoragePolicy();
        //System.out.println(sp.getStoragePolicy());
        System.out.println(sp.deleteStoragePolicyByName("test_sp1"));

    }

    /**
     * 只列出 {"storagePolicyName":"Default Virtualization plan","storagePolicyId":11}
     *
     * @return
     */
    @Override
    public String getStoragePolicy() {
        initHeaders();
        String content = HTTPUtil.doGet(GETSPUrl, headers, null);
        return content;
    }

    @Override
    public String createStoragePolicyByID(String id) {
        return null;
    }

    public String getStoragePolicyById(String id) throws Exception {
        if (id.length() == 0 || id == null) {
            throw new Exception("传入的存储策略id不合法");
        }
        initHeaders();
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/StoragePolicy/" + id;
        String content = HTTPUtil.doGet(url, headers, null);
        return content;
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
        initHeaders();
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/StoragePolicy/" + name;
        String content = HTTPUtil.doDelete(url, headers, null);
        System.out.println(content);
        if (content.contains("errorMessage")) {
            return false;
        }
        return true;
    }


}
