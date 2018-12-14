package person.commvault.backup.subclient;

import person.commvault.backup.utils.XMLUtil;

/**
 * Created by daijitao on 2018/11/15.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        String path = "E:\\intell_space\\myTestDemo\\src\\person\\commvault\\backup\\subclient\\CreateSubclient.xml";
        String xml = XMLUtil.getXml(path);
        String xml_1 = XMLUtil.replaceXmlValue(xml, "appName", "dai");
        String data = "daijitao";
        String data1 = data.replaceAll("dai", "li");
        System.out.println(data1);
    }
}
