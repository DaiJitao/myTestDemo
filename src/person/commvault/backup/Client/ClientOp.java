package person.commvault.backup.Client;

import person.commvault.backup.BackUpBase;
import person.commvault.backup.Common;
import person.commvault.backup.HTTPUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daijitao on 2018/10/15.
 */
public class ClientOp extends BackUpBase {

    public static void main(String[] args) {
        System.out.println(new ClientOp().getClients());
        System.out.println(new ClientOp().getClientsByVSPseudo(true));
        System.out.println(new ClientOp().getClientsByVSPseudo(false));
    }

    public String getClients() {
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Client";
        result = httpUtil.doGet(url, headers, null);
        return result;
    }

    public String getClientsByVSPseudo(boolean isVSPseudo) {
        String result = "";
        String url = "http://192.168.20.53:81/SearchSvc/CVWebService.svc/Client";
        Map<String, String> params = new HashMap<>();
        params.put("VSPseudo", isVSPseudo ? "VSPseudo" : "CloudApps");
        result = httpUtil.doGet(url, headers, params);
        return result;
    }
}
