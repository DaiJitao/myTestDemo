package test.thread.duo.xian.chenghexinjishu.chapter3.section1;

/**
 * Created by daijitao on 2018/5/31.
 */
public class ThreadB extends Thread {
    private MyList list;
    public ThreadB(MyList list){
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (list.size() == 5) {
                    System.out.println("==5了");
                    throw new InterruptedException("==5了");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
