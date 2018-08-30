package com.wpx.Myjt808;

import com.wpx.Myjt808.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by GM-KF007 on 2018/5/8.
 */
public class BusinessHandler extends ChannelInboundHandlerAdapter {

    //�ͻ�������
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("�ͻ���--" + ctx.channel().remoteAddress() + "--����");
    }

    //�ͻ��˶Ͽ�����
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("�ͻ���--" + ctx.channel().remoteAddress() + "--�Ͽ�����");
    }

    //�յ��ͻ�����Ϣ
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf src = (ByteBuf) msg;

        //δ�յ��κ�����ֱ���˳�
        if(src.readableBytes()<=0){
            return;
        }


        byte[] buf = new byte[src.readableBytes()];
        src.readBytes(buf);

        System.out.println(ByteUtil.bytesToHexString(buf));

    }

    //�ͻ���������ʱ ÿ30S����һ��
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("�ͻ���������ʱ:" + ctx.channel().remoteAddress());
    }

    //�����쳣
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught:" + ctx.channel().remoteAddress() + "�쳣ԭ��:");
        cause.printStackTrace();
    }

}
