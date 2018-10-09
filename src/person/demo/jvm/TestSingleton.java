package person.demo.jvm;

/**
 * Created by daijitao on 2018/9/5.
 */
// E:\intell_space\myTestDemo\out
public class TestSingleton {
    public static void main(String args[]){
        Singleton singleton = Singleton.getSingleton();
        System.out.println("counter1="+singleton.counter1);
        System.out.println("counter2="+singleton.counter2);
        System.out.println(singleton.getClass());
    }

}
