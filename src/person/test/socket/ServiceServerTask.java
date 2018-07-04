package person.test.socket;

import java.io.*;
import java.net.Socket;

public class ServiceServerTask implements Runnable {
    Socket socket = null;
    public ServiceServerTask(Socket socket){
        System.out.println("初始化");
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println("====>>run()");
        InputStream in = null;
        OutputStream outputStream = null;
        try {
            in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = "";
            //while ()
            String param = br.readLine();
            //调用业务方法
            String result = "ok " + param;

            //


            outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            System.out.println("yewui============");
            writer.println(result);
            writer.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
