package test.thread.duo.xian.chenghexinjishu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by daijitao on 2018/11/8.
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 60L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("adcdfs");
            }
        };

        executorService.execute(runnable);
        executorService.shutdown();
    }
}
