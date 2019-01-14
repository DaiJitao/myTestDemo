package access.vcenter.mob;

/**
 * Created by daijitao on 2019/1/10.
 */
public class Student {
    public static void main(String[] args) {
        System.out.println("main");
        int size = args.length;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                System.out.println(args[i]);
            }
        }
    }
}
