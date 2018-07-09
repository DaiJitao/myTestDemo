package person.test.nio;

import java.io.IOException;

/**
 * Created by daijitao on 2018/7/9.
 */
public class TimeServer {

    public TimeServer() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-oo1").start();
    }
}
