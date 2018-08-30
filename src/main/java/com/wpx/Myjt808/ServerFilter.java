package com.wpx.Myjt808;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by GM-KF007 on 2018/5/8.
 */
public class ServerFilter extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new IdleStateHandler(60 * 2, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024 * 1, Unpooled.copiedBuffer(new byte[]{0x7e}), Unpooled.copiedBuffer(new byte[]{0x7e})));
        pipeline.addLast(new LogHandler());
        pipeline.addLast(new BusinessHandler());
    }
}
