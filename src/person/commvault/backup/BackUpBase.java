package person.commvault.backup;

import java.util.Map;

/**
 * Created by daijitao on 2018/10/12.
 */
public abstract class BackUpBase {
    public HTTPUtil httpUtil = HTTPUtil.getInstance();
    public Map<String, String> headers = Common.getInstance().initHeaders();
}
