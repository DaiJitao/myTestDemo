package test.thread.lock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyInterruptibly {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        MyInterruptibly test = new MyInterruptibly();
        MyThread thread = new MyThread(test);
        MyThread thread1 = new MyThread(test);
        thread.start();
        thread1.start();

        try {
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        System.out.println("================> ok");

    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        System.out.println(thread.getName() + "----------lock");
        long start = System.currentTimeMillis();
        for(;;) {
            if (System.currentTimeMillis() - start > Integer.MAX_VALUE) {
                break;
            }
        }
        lock.unlock();
    }
}


class MyThread extends Thread{
    private MyInterruptibly test = null;
    public MyThread(MyInterruptibly test) {
        this.test = test;
    }
    @Override
    public void run() {
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println("bei zhong duan");
        }
    }
}
