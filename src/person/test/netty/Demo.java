package person.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by daijitao on 2018/7/12.
 */
public class Demo {
    static private ServerBootstrap bootstrap = new ServerBootstrap();

    public static void main(String[] args) {
        SimpleChannelInboundHandler<ByteBuf> handler = new SimpleChannelInboundHandler<ByteBuf>() {
            ChannelFuture connectFuture;

            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

            }

            public void channelActive(ChannelHandlerContext ctx) {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.channel(NioSocketChannel.class)
                        .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
                                System.out.println("接收到数据");
                            }
                        });
            }
        };

    }
}
