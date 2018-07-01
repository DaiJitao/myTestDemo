package person.test.java.project;

/**
 * Created by daijitao on 2017/6/29.
 */
public class TestDemo {
    public static void main(String[] args) {
        int a = 12,b = 12;
        System.out.println(String.valueOf(a) + " :" + String.valueOf(b));
        System.out.println("Hello World!");
        System.out.println("Hello World!");
        System.out.println("Hello World!");
        System.out.println("Hello World!");
        System.out.println("Hello World!");
        System.out.println(System.getProperty("java.version"));
        System.out.println((Integer)129 == (Integer)129);
        System.out.println(1L << 65L);

        int v = 'A' / 'a' > 1 ? 4:5;
        v += 'A';
        System.out.println("v " + v);
    }
}
