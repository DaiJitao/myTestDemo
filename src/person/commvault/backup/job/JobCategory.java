package person.commvault.backup.job;

/**
 * Created by daijitao on 2018/10/17.
 */
enum JobCategory {
    ACTIVE("Active"),
    FINISHED("Finished"),
    ALL("All");
    private String param;
    private JobCategory(String content){
        this.param = content;
    }

    public String getParam() {
        return param;
    }
}
