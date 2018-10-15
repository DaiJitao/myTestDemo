package person.commvault.backup.subclient;

import com.alibaba.fastjson.JSON;

/**
 * Created by daijitao on 2018/10/15.
 */
public enum BackupLevel {
    FULL("Full"),
    INCREMENTAL("Incremental");

    private String level;
    private BackupLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
