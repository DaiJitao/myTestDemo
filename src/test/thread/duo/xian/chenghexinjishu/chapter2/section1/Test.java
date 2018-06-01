package test.thread.duo.xian.chenghexinjishu.chapter2.section1;

/**
 * Created by daijitao on 2018/5/29.
 */
public class Test {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA a = new ThreadA(numRef);
        ThreadB b = new ThreadB(numRef);
        a.start();
        b.start();

    }
}
