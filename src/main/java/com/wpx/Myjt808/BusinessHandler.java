package com.wpx.Myjt808;

import com.wpx.Myjt808.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by GM-KF007 on 2018/5/8.
 */
public class BusinessHandler extends ChannelInboundHandlerAdapter {

    //客户端连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端--" + ctx.channel().remoteAddress() + "--连接");
    }

    //客户端断开连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端--" + ctx.channel().remoteAddress() + "--断开连接");
    }

    //收到客户端信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf src = (ByteBuf) msg;

        //未收到任何数据直接退出
        if(src.readableBytes()<=0){
            return;
        }


        byte[] buf = new byte[src.readableBytes()];
        src.readBytes(buf);

        System.out.println(ByteUtil.bytesToHexString(buf));

    }

    //客户端心跳超时 每30S触发一次
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("客户端心跳超时:" + ctx.channel().remoteAddress());
    }

    //发生异常
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught:" + ctx.channel().remoteAddress() + "异常原因:");
        cause.printStackTrace();
    }

}
