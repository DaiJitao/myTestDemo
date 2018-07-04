package person.test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 8899));
        int count = 1;
        while (true){
            System.out.println("=====>> " + (count++));
            Socket socket = server.accept();
            ServiceServerTask task = new ServiceServerTask(socket);
            new Thread(task).start();
            System.out.println("县城完毕。。。");
        }
    }
}
