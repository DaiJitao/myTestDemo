package test.thread.duo.xian.chenghexinjishu.chapter2.section1;

/**
 * Created by daijitao on 2018/5/29.
 */
public class ThreadB extends Thread {
    private RefNum numRef;
    public ThreadB(RefNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }
}
