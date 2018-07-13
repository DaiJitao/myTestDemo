package person.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * Created by daijitao on 2018/7/13.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, CharsetUtil.UTF_8);
        System.out.println("时间服务器接收到的order： " + body);
        String currentTime = new Date(System.currentTimeMillis()).toString();
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        context.write(resp);
    }

    public void channelReadComplete(ChannelHandlerContext context) throws Exception {
        context.flush();
    }

    public void exceptionCaught(ChannelHandlerContext context, Throwable cause){
        context.close();
    }
}
