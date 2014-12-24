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

package com.lxd.client.link;

import com.lxd.client.resource.ClientResource;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * 客户端处理类的设置
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月16日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ClientHandler extends SimpleChannelInboundHandler<Msg_> {

    @Override
    ///< 接收到信息
    protected void channelRead0(ChannelHandlerContext ctx, Msg_ msg) throws Exception {
        ///< 将接收的消息放入到收入队列
       Resource.getSingleton().getMsgQueue().submitMsgInQueue(new DataPackage(msg, ctx.channel())); 
    }

    @Override
    ///< 客户端与服务器链接后
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ClientResource.getSingleton().setChannel(ctx.channel());
    }
    
   
}
