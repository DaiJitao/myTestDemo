package person.commvault.backup.StoragePolicy;

/**
 * Created by daijitao on 2018/10/12.
 */
public interface StoragePolicyBase {
    public static final String GETSPUrl = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/StoragePolicy";
    public String getStoragePolicy();
    public String createStoragePolicyByID(String id);
    public boolean updateStoragePolicy();
    public boolean deleteStoragePolicyByName(String name) throws Exception;
}
