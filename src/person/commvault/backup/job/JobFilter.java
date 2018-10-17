package person.commvault.backup.job;

/**
 * Created by daijitao on 2018/10/17.
 */
enum JobFilter {
    BACKUP("backup"),
    RESTORE("restore");
    private String param;
    private JobFilter(String content){
        this.param = content;
    }

    public String getParam() {
        return param;
    }
}
