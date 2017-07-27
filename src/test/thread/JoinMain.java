package test.thread;

public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for ( i = 0; i < 100000000; i++) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        addThread.join(); //主线程等待 addThread线程 执行完毕。
        // 假如没有写这句话，主线程就不会等待addThread线程执行完毕再执行。
        System.out.println(i);
    }
}
