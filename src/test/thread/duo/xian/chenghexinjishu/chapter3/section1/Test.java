package test.thread.duo.xian.chenghexinjishu.chapter3.section1;

/**
 * Created by daijitao on 2018/5/31.
 */
public class Test {
    public static void main(String[] args) {
        MyList list = new MyList();
        ThreadA a = new ThreadA(list);
        a.setName("A线程");
        a.start();
        ThreadB b = new ThreadB(list);
        b.setName("B线程");
        b.start();
    }
}
