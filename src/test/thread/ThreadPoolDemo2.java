package test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo2 {

    //
    static class CallableDemo implements Callable{

        @Override
        public Object call() throws Exception {
            Thread.sleep(5000);
            return "b-- name is " + Thread.currentThread().getName();
        }
    }
    static Future<String> submit = null;
    static List<Future<String>> list = new ArrayList<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool2 = Executors.newFixedThreadPool(35);
        for (int i = 0; i < 600; i++) {
            CallableDemo demo = new CallableDemo();
            submit = pool2.submit(demo);
            list.add(submit);
        }
        pool2.shutdown();
        for (Future<String> temp : list){
            boolean done = temp.isDone();
            System.out.println(done? "ok":"no ok");
            System.out.println(temp.get());
        }
    }
}
