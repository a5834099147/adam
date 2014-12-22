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

package com.lxd.server.link;

import com.lxd.protobuf.msg.Msg;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;


/**
 * 服务器编码, 解码, 处理类的设置
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月16日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ServerInitalizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ///< 初始化通道
        ChannelPipeline pipeline = ch.pipeline();
        
        ///< 增加Protobuf 的 框架解码
        pipeline.addLast(new ProtobufVarint32FrameDecoder())
        ///< 增加 Protobuf 的 数据解码
                    .addLast(new ProtobufDecoder(Msg.Msg_.getDefaultInstance()))
                    ///< 增加 Protobuf 的数据长度编码器
                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                    ///< 增加 Protobuf 的数据编码器
                    .addLast(new ProtobufEncoder())
                    ///< 增加 该服务器的处理类
                    .addLast(new ServerHandler());
        
    }

}
