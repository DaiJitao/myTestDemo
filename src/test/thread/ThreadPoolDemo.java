package test.thread;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    // 没有返回值
    static class ThreadDemo implements Runnable{
        @Override
        public void run() {
            System.out.println("ThreadName is " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //
    static class CallableDemo implements Callable{

        @Override
        public Object call() throws Exception {
            Thread.sleep(5000);
            return "b-- name is " + Thread.currentThread().getName();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ThreadDemo threadDemo = new ThreadDemo();
        pool.execute(threadDemo);
        pool.shutdown();
        System.out.println("\n\n");

        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            CallableDemo demo = new CallableDemo();
            Future<String> submit = pool2.submit(demo);
            System.out.println(submit.get());
        }
        pool2.shutdown();
    }
}
