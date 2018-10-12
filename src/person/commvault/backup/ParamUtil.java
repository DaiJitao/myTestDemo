package person.commvault.backup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/10.
 */
public class ParamUtil {

    public static String getParam( Map<String, String> headers) {
        if (headers == null || headers.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object[] keys = (Object[]) headers.keySet().toArray();
        Object[] values = (Object[])headers.values().toArray();
        int len = keys.length;
        for (int i = 0; i < len - 1; i++) {
            stringBuilder.append(keys[i].toString()).append("=").append(values[i].toString()).append("&");
        }
        stringBuilder.append(keys[len - 1].toString()).append("=").append(values[len - 1].toString());
       return stringBuilder.toString();
    }
}
