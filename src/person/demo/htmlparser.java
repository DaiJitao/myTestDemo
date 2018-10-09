package person.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by daijitao on 2018/8/31.
 */
public class htmlparser {
    List<String> a = new ArrayList<>();
    public static void main(String[] args) {

        Runnable arss = () -> System.out.println("Hello world");
        Thread thread = new Thread(arss);
        thread.start();
        System.out.println("test");




    }
}

class Artist<E> extends ArrayList{

}
