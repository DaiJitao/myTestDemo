package person.test.socket;

import java.io.*;
import java.net.Socket;

public class ServiceClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8899);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("hello");
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String result = reader.readLine();
        System.out.println("client:" + result);

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
