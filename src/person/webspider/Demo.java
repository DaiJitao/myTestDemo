package person.webspider;

import person.commvault.backup.BackUpBase;

import java.util.Map;

/**
 * Created by daijitao on 2018/10/22.
 */
public class Demo extends BackUpBase {

    public static void main(String[] args) {
        String url = "https://book.douban.com/subject_search?search_text=%E6%83%85%E4%BA%BA&cat=1001";
    }



    public String get_html(String url, Map<String, String> headers, Map<String, String> params){
        String result = httpUtil.doGet(url, headers, params);
        return null;
    }
}
