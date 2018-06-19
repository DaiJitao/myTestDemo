package test.thread.nolock;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19,0);

    public static void main(String[] args) {
        // 摸你多个线程同时更新后台数据库，为用户充值
        for (int i = 0; i < 3; i++) {
            final int timeStamp = money.getStamp();
            new Thread(){
                @Override
                public void run() {
                    while (true)
                    {
                        while (true)
                        {
                            Integer m = money.getReference();
                            if (m < 20){
                                if (money.compareAndSet(m, m+20, timeStamp, timeStamp + 1)){
                                    System.out.println("充值成功！" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }.start();
        }


    }
}
