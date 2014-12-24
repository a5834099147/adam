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

package com.lxd.client.resource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;

import io.netty.channel.Channel;


/**
 * 客户端多余资源
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ClientResource {
    private static final Logger log = LogManager.getLogger(ClientResource.class);
    ///< 与服务器通信通道
    private Channel channel;
    
    ///< 请求消息存放队列
    private BlockingQueue<RequestPackage> request = new LinkedBlockingQueue<>();
    
    public void submitRequest(RequestPackage reqPack) {
        try {
            request.offer(reqPack, 5, TimeUnit.SECONDS);
            log.info("提交了一个请求");
            Msg_.Builder request = Msg_.newBuilder();
            ///< 请求ID
            request.setJobId(-1);
            ///< 将请求信息发送出去
            Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(request.build(), channel));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public RequestPackage takeRequest() {
        try {
            return request.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }    
    
    public Channel getChannel() {
        return channel;
    }

    
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    private ClientResource() {}
    
    private static ClientResource singleton = new ClientResource();
    
    public static ClientResource getSingleton() {
        return singleton;
    }
}
