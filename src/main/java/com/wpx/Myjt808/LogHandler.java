package com.wpx.Myjt808;

import com.wpx.Myjt808.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by GM-KF007 on 2018/5/10.
 */
public class LogHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        byte[] log = new byte[in.readableBytes()];
        in.readBytes(log);
        System.out.println("收到客户端数据:"+channelHandlerContext.channel().id().asLongText()+ ByteUtil.bytesToHexString(log));
        ByteBuf sendBuf= Unpooled.buffer();
        sendBuf.writeBytes(log);
        out.add(sendBuf);
    }
}
