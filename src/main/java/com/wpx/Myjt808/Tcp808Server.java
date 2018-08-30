package com.wpx.Myjt808;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by GM-KF007 on 2018/5/8.
 */
public class Tcp808Server {

    private static final int PORT = 5000;

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bind(PORT);
                } catch (Exception e) {
                    System.out.println("�������쳣:" + e.getMessage());
                }
            }
        }).start();
    }

    public void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ServerFilter());
            //�󶨶˿�,��ʼ���ս���������
            ChannelFuture f = b.bind(port).sync();
            System.out.println("����������");
            // �ȴ��������ر�
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("�������ر�");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
