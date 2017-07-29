package test.thread.nolock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo implements Runnable {
    static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] t = new Thread[10];
        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(new AtomicIntegerDemo());
        }
        for (int i = 0; i < 10; i++) {
            t[i].start();
            //t[i].join();
        }
        for (int i = 0; i < 10; i++) {
            t[i].join();
        }
        System.out.println(atomicInteger);
    }
}
