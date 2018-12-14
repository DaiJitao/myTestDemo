package person.demo;

/**
 * Created by daijitao on 2018/11/22.
 */
public class Demo {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("abc");
        change(stringBuilder);
        System.out.println(stringBuilder);
    }

    static void change(StringBuilder stringBuilder) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("def");
    }
}
