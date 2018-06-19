package test.thread.nioANDaio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDemo {
    private static void nioCopyFile(){
        String pathName = "E:\\data\\m.txt";
        String destinationName = "E:\\test.txt";
        try {
            FileOutputStream fos = new FileOutputStream(new File(destinationName));
            FileInputStream inputStream = new FileInputStream(new File(pathName));
            FileChannel inchannel = inputStream.getChannel();
            FileChannel outChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
