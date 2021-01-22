package com.shadow.chat.bean;

import cn.hutool.core.util.NumberUtil;
import com.shadow.chat.bean.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import javax.jnlp.ClipboardService;
import java.util.List;

/**
 * @author Shadow
 * @date 2021/01/22 15:55:34
 */
public class MessageDecoder extends ByteToMessageDecoder {


    int length = 0;

    /**
     * 0 0 0 2 1 1     length = 0
     * 1 1      length = 2
     * read
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= 4) {
            if (length == 0) {
                length = in.readInt();
            }
            if (in.readableBytes() < length) {
                return;
            }
            byte[] data = new byte[length];
            in.readBytes(data);
            Message message = new Message();
            message.setLength(length);
            message.setContent(data);
            out.add(message);
            length = 0;
        }
    }
}
