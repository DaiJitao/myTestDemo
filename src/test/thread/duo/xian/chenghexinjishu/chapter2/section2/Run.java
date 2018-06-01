package test.thread.duo.xian.chenghexinjishu.chapter2.section2;

import test.thread.duo.xian.chenghexinjishu.chapter2.section1.ThreadA;
import test.thread.duo.xian.chenghexinjishu.chapter2.section1.ThreadB;

/**
 * Created by daijitao on 2018/5/31.
 */
public class Run {
    public static void main(String[] args) {
        HasSelfPrivateNum[] tt = {new HasSelfPrivateNum(), new HasSelfPrivateNum()};

        ThreadA a = new ThreadA(tt[0]);
        ThreadB b = new ThreadB(tt[1]);
        a.start();
        b.start();

    }
}
