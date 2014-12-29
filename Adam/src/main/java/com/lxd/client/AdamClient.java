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

package com.lxd.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.link.ClientInitalizer;
import com.lxd.client.secondary.MsgInPre;
import com.lxd.secondary.MessageSend;
import com.lxd.threadpool.impl.TaskThreadPool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * CMD客户端入口
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月22日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class AdamClient {

    private static final Logger log  = LogManager.getLogger();
    private static final int    PORT = 8463;

    public static void main(String[] args) {
        new AdamClient().initServer();
    }

    public void initServer() {

        // /<开始线程池

        new TaskThreadPool().start();
        log.info("开启线程池成功");
        // /< 开始消息处理线程
        new MsgInPre().start();
        log.info("开启处理线程成功");
        // /< 开始消息发送线程
        new MessageSend().start();
        log.info("开启发送线程成功");
        if (!netStart(PORT)) {
            return;
        }
    }

    private boolean netStart(int prot) {
        // /< 其他事件循环组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            // /< 通过向导设置链接的参数
            bootstrap.group(workerGroup).channel(NioSocketChannel.class)
            // /< 开始日志, 日志输出最小类型限制为 INFO
            .handler(new ClientInitalizer());

            // /< 绑定端口并打开链接
            try {
                bootstrap.connect("localhost", prot).sync().channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        } finally {
            // /< 关闭时间循环组
            try {
                workerGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
}
