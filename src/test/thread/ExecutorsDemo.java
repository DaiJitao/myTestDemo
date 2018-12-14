package test.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by daijitao on 2018/11/8.
 */
public class ExecutorsDemo {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
        }
    }
}

class SubThread implements Runnable{

    int i = 0;
    @Override
    public void run() {
        i++;
        System.out.println(i);
        byte[] data = new byte[1024*1024*1024*1024];

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
