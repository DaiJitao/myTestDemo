package person.commvault.backup.StoragePolicy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import person.commvault.backup.Storage.Resource.Library;
import person.commvault.backup.Storage.Resource.MediaAgent;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/15.
 */
public class StoragePolicy {
    public static void main(String[] args) throws Exception {
        MediaAgent mediaAgent = new MediaAgent();
        mediaAgent.setMediaAgentId(2);
        Library library = new Library();
        library.setLibraryId(7);
        RetentionRule retentionRule = new RetentionRule(2, 12);

        StoragePolicyCopyInfo storagePolicyCopyInfo = new StoragePolicyCopyInfo();
        storagePolicyCopyInfo.setLibrary(library);
        storagePolicyCopyInfo.setMediaAgent(mediaAgent);
        storagePolicyCopyInfo.setRetentionRules(retentionRule);


        StoragePolicy storagePolicy = new StoragePolicy();
        storagePolicy.setStoragePolicyName("dai4");
        storagePolicy.setStoragePolicyCopyInfo(storagePolicyCopyInfo);




        StoragePolicyOP op = new StoragePolicyOP();
        //String test = op.createStoragePolicy(storagePolicy);
        // System.out.println(JSONObject.parseObject(test));
        boolean del = op.deleteStoragePolicyByName("dai3");
        System.out.println(del);

        String test = op.getStoragePolicy();
        System.out.println(test);

    }
    private String storagePolicyName;
    private StoragePolicyCopyInfo storagePolicyCopyInfo;

    public String getStoragePolicyName() {
        return storagePolicyName;
    }

    public void setStoragePolicyName(String storagePolicyName) {
        this.storagePolicyName = storagePolicyName;
    }

    public StoragePolicyCopyInfo getStoragePolicyCopyInfo() {
        return storagePolicyCopyInfo;
    }

    public void setStoragePolicyCopyInfo(StoragePolicyCopyInfo storagePolicyCopyInfo) {
        this.storagePolicyCopyInfo = storagePolicyCopyInfo;
    }
}

class StoragePolicyCopyInfo {
   private RetentionRule retentionRules;
   private Library library;
   private MediaAgent mediaAgent;

    public RetentionRule getRetentionRules() {
        return retentionRules;
    }

    public void setRetentionRules(RetentionRule retentionRules) {
        this.retentionRules = retentionRules;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public MediaAgent getMediaAgent() {
        return mediaAgent;
    }

    public void setMediaAgent(MediaAgent mediaAgent) {
        this.mediaAgent = mediaAgent;
    }
}

class RetentionRule{
    private int retainBackupDataForCycles;
    private int retainBackupDataForDays;

    public RetentionRule(int cycles, int days){
        this.retainBackupDataForCycles = cycles;
        this.retainBackupDataForDays = days;
    }

    public int getRetainBackupDataForCycles() {
        return retainBackupDataForCycles;
    }

    public void setRetainBackupDataForCycles(int retainBackupDataForCycles) {
        this.retainBackupDataForCycles = retainBackupDataForCycles;
    }

    public int getRetainBackupDataForDays() {
        return retainBackupDataForDays;
    }

    public void setRetainBackupDataForDays(int retainBackupDataForDays) {
        this.retainBackupDataForDays = retainBackupDataForDays;
    }
}
