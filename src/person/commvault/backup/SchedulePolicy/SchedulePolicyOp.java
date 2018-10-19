package person.commvault.backup.SchedulePolicy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import person.commvault.backup.BackUpBase;
import person.commvault.backup.backupSet.BackupSetOp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/16.
 */
public class SchedulePolicyOp extends BackUpBase {

    public static void main(String[] args) throws Exception {
        String result = new SchedulePolicyOp().getSchedulePolicy();
        System.out.println("所有的计划策略\n" + result);
        JSONObject object = JSONObject.parseObject(result);
        JSONArray array = object.getJSONArray("taskDetail");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject tmp = array.getJSONObject(i).getJSONObject("task");
            String taskId = tmp.getString("taskId");
            System.out.println(taskId);

        }
        result = new SchedulePolicyOp().getSchPlyDetailsByBackupsetId("28");
        System.out.println(result);
    }

    // 列举出console显示的计划策略，没有定时任务信息
    public String getSchedulePolicy() {
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/SchedulePolicy";
        String result = "";
        result = httpUtil.doGet(url, headers, null);
        return result;
    }

    public String getSchPolicyDetailsBySpolicyId(String spId) throws Exception {
        if (null == spId || spId.length() == 0) {
            throw new Exception("传入计划策略Id错误");
        }
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Schedules";
        Map<String, String> params = new HashMap<>();
        params.put("storagepolicyId", spId);
        String result = "";
        result = httpUtil.doGet(url, headers, params);
        return result;
    }

    // 根据子客户端查询
    public String getSchPolicyDetailsBySubclientId(String subclientId) throws Exception {
        if (null == subclientId || subclientId.length() == 0) {
            throw new Exception("传入计划策略Id错误");
        }
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Schedules";
        Map<String, String> params = new HashMap<>();
        params.put("subclientId", subclientId);
        String result = "";
        result = httpUtil.doGet(url, headers, params);
        return result;
    }


    // 根据备份集查询
    public String getSchPlyDetailsByBackupsetId(String backupsetId) throws Exception {
        if (null == backupsetId || backupsetId.length() == 0) {
            throw new Exception("传入backupsetId错误");
        }
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Schedules";
        Map<String, String> params = new HashMap<>();
        params.put("backupsetId", backupsetId);
        String result = "";
        result = httpUtil.doGet(url, headers, params);
        return result;
    }
}
