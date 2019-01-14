package access.vcenter.mob;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by daijitao on 2019/1/10.
 */
public class RefStudent {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] test = new String[]{"mm", "dd"};
        Class cls = Class.forName("access.vcenter.mob.Student");
        Method mainMethod = cls.getMethod("main", String[].class);
        mainMethod.invoke(null, (Object)test);
    }
}
