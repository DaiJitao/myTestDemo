package test.thread.duo.xian.chenghexinjishu.chapter2.section1;

/**
 * Created by daijitao on 2018/5/29.
 */
public class HasSelfPrivateNum implements RefNum{
    private int num = 0;
    public void addI(String username){
        try
        {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            }
            else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num = " + num);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
