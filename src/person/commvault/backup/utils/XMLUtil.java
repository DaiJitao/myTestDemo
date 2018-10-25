package person.commvault.backup.utils;


import java.io.*;

public class XMLUtil {

    public static void main(String[] args) throws Exception {
        String result = XMLUtil.getXml("");
        System.out.println(result);
        System.out.println(XMLUtil.replaceXmlValue(result, "toTime", "1234567890-"));
    }

    /**
     * 获取xml
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getXml(String path) throws Exception {
        try {
            String xmlPath = "E:\\intell_space\\myTestDemo\\src\\person\\commvault\\backup\\utils\\getDisks.xml";
            if (path.length() == 0) {
                throw new Exception("传入的xml文件参数不可为空");
            }
            FileReader fileReader = new FileReader(path);
            BufferedReader buff = null;
            try {
                buff = new BufferedReader(fileReader);
                String xml = "";
                String line = "";
                while ((line = buff.readLine()) != null) {
                    xml += line;
                }
                return xml;
            } finally {
                fileReader.close();
                if (buff != null) {
                    buff.close();
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static String replaceXmlValue(String xml, String marker, String value) {
        marker = "\\s*%" + marker + "%\\s*";
        if (value == null) {
            value = "";
        }
        return xml.replaceAll(marker, value);
    }

}
