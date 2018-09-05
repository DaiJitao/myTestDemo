package person.demo.jvm;

/**
 * Created by daijitao on 2018/9/5.
 */
public class Singleton {

    public static int counter1;
    public static int counter2 = 0;
    private static Singleton singleton = new Singleton();
    private Singleton() {
        counter1++;
        counter2++;
    }
    public static Singleton getSingleton() {
        return singleton;
    }

}
