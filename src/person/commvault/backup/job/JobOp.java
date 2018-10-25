package person.commvault.backup.job;

import person.commvault.backup.BackUpBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/17.
 */
public class JobOp extends BackUpBase {
    public static void main(String[] args) throws Exception{
        JobOp jobOp = new JobOp();
        System.out.println(jobOp.queryJob("4", null, JobCategory.FINISHED, JobFilter.BACKUP));
        System.out.println(jobOp.getJobById("2250"));

    }

    public String getJobById(String jobId) throws Exception{
        if ( jobId == null || jobId.trim().length() == 0){
            throw new Exception("jobID错误");
        }
        String url = CommVault_SERVER_URL + "/SearchSvc/CVWebService.svc/Job/" + jobId;
        String result = httpUtil.doGet(url, headers, null);
        return result;
    }

    public String queryJob(String clientID, String time, JobCategory category, JobFilter filter) {
        String result = "";
        Map<String, String> params = new HashMap<>();
        if ( (null != clientID ) && (clientID.length() != 0)){
            params.put("clientId", clientID);
        }
        if (null != time && time.length() != 0){
            params.put("completedJobLookupTime", time);
        }
        if (null != category){
            params.put("jobCategory", category.getParam());
        }
        if (null != filter){
            params.put("jobFilter", filter.getParam());
        }
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Job";
        result = httpUtil.doGet(url, headers, params);

        return result;
    }



}
