package test.thread.nolock;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    final static AtomicReference<String> atomicStr = new AtomicReference<>("abc");
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int num = 1;
            new Thread(){
                public void run(){
                    try {
                        Thread.sleep(Math.abs((int)(Math.random() * 100)));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (atomicStr.compareAndSet("abc","def")){
                        System.out.println("Thread: " + Thread.currentThread().getId() + " change ok");
                    }else
                        System.out.println("Thread: " + Thread.currentThread().getId() + " change failed");
                }
            }.start();

        }
    }
}
