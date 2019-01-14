package person.demo;

import java.util.Scanner;

/**
 * Created by daijitao on 2018/11/22.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println(Double.MIN_NORMAL);
    }
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while (true){
            str = scanner.next();
            str = str.replace("吗", "");
            str = str.replace("?", "!");
            str = str.replace("?", "!");
            System.out.println(str);
        }
    }

    public static void main1(String[] args) {
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
