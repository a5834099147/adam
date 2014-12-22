/*
 * Copyright (C) 2014 a5834099147(lxd) <a5834099147@126.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.lxd.server;

import com.lxd.server.link.ServerInitalizer;
import com.lxd.server.secondary.MessageSend;
import com.lxd.server.secondary.MsgInPre;
import com.lxd.server.threadpool.impl.TaskThreadPool;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


/**
 * 服务器的入口
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月16日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class AdamServer {
    private static final int PORT = 8463;
    
    public static void main(String[] args) {        
        new AdamServer().initServer();
    }
    
    public void initServer() {
        ///< 开始网络事务
        if (netStart(PORT)) {
            ///<开始线程池
            new TaskThreadPool().start();
            ///< 开始消息处理线程
            new MsgInPre().start();
            ///< 开始消息发送线程
            new MessageSend().start();
        }        
    }
    
    private boolean netStart(int prot) {
      ///< 监听事件循环组
        EventLoopGroup listenGroup = new NioEventLoopGroup(1);
        ///< 其他事件循环组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ///< 通过向导设置链接的参数
            bootstrap.group(listenGroup, workerGroup)
                           .channel(NioServerSocketChannel.class)
                           ///< 开始日志, 日志输出最小类型限制为 INFO
                           .handler(new LoggingHandler(LogLevel.INFO))
                           .childHandler(new ServerInitalizer());
            
            ///< 绑定端口并打开链接
            try {
                bootstrap.bind(prot).sync().channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        } finally {
            ///< 关闭时间循环组
            try {
                listenGroup.shutdownGracefully().sync();
                workerGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        return true;
    }
}
