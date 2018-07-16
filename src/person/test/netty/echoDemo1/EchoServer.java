package person.test.netty.echoDemo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Created by daijitao on 2018/7/16.
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup eventLoopGroup = null;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            eventLoopGroup = new NioEventLoopGroup();
            serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress("localhost", port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast( new EchoServerHandler())
                            .addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("服务器开始监听，端口为：" + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new EchoServer(20000).start();
    }
}
