package person.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;

/**
 * Created by daijitao on 2018/7/13.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private final ByteBuf firstMessage;

    public TimeClientHandler() {
        firstMessage = Unpooled.buffer();
    }
}
