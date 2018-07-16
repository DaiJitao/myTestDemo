package person.test.netty.echoDemo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * Created by daijitao on 2018/7/16.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // ChannelHandlerContext mm = ctx.fireChannelRead(msg);
        System.out.println("server正在读取数据......");
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, CharsetUtil.UTF_8);
        System.out.println("客户端接收到的数据：" + body);
        // 向客户端写数据
        System.out.println("server 向 client 发送数据");
        String currentTime = new Date(System.currentTimeMillis()).toString();
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        System.out.println("server 读取数据完毕..");
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
