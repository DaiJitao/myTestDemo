package person.demo;

/**
 * Created by daijitao on 2018/8/31.
 */
public class htmlparser extends A{
    public htmlparser(A a){
        System.out.println("B");
    }

    public static void main(String[] args) {
        A a = new A();
        htmlparser htmlparser = new htmlparser(a);
    }
}

class A {
    public A(){
        System.out.println("A");
    }
}
