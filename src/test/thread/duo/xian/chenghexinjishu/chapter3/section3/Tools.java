package test.thread.duo.xian.chenghexinjishu.chapter3.section3;

/**
 * Created by daijitao on 2018/6/6.
 */
public class Tools {
    public static ThreadLocal t1 = new ThreadLocal();

    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("从未放过值");
            t1.set("我的值");
        }
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.toString());
    }
}