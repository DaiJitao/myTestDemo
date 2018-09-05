package person.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Created by daijitao on 2018/8/13.
 */
public class Demo1 {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty id ok", utf8);
        System.out.println((char)buf.getByte(0));
        int readerIndex = buf.readerIndex();
        System.out.println("readerIndex::" + readerIndex);
        int writeIndex = buf.writerIndex();
        System.out.println("writeIndex::" + writeIndex);
        buf.setByte(0,(byte)'B');
        System.out.println("Set::"+ (char)buf.getByte(0));

        for (int i = 0; i < buf.readableBytes(); i++) {
            System.out.print((char)buf.readByte());

        }
        System.out.println("可写字节数" + buf.writableBytes());
        System.out.println(buf.capacity());
        System.out.println("调用buf.writableBytes()之后" + buf.writableBytes());

        ByteBuf buf1 = Unpooled.buffer(12, 330);
        System.out.println("readableBytes() buf1::" + buf1.readableBytes());
        System.out.println(buf1.capacity());
        ByteBuf buf2 = Unpooled.directBuffer();
        Boolean isE = ByteBufUtil.equals(buf1, buf2);
        System.out.println(isE);



    }
}
