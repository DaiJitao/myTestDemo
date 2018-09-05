package person.demo.jvm;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by daijitao on 2018/9/5.
 */
public class MyClassLoader {
    public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream inputStream = getClass().getResourceAsStream(fileName);
                if (inputStream==null)
                    return super.loadClass(name);
                try {
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name,bytes,0,bytes.length);

                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }

        };
        Object object = loader.loadClass("jvm.classloader.MyClassLoader").newInstance();


    }
}

