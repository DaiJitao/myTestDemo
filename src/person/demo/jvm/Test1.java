package person.demo.jvm;

/**
 * Created by daijitao on 2018/9/5.
 */
public class Test1 {
    public static void main(String args[]){
        FinalTest test = new FinalTest();
        System.out.println(FinalTest.x);
    }
}

class FinalTest{
    public static final int x =6/3;
    static {
        System.out.println("FinalTest static block");
    }
}

