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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.monitor.MonitorMsg;
import com.lxd.client.monitor.MonitorMsg.Type;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;

import io.netty.channel.Channel;

/**
 * 客户端多余资源
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class ClientResource {

    private static final Logger           log     = LogManager.getLogger(ClientResource.class);
    // /< 与服务器通信通道
    private Channel                       channel;

    // /< 请求消息存放队列
    private BlockingQueue<RequestPackage> request = new LinkedBlockingQueue<>();

    // /< 请求提交
    public void submitRequest(RequestPackage reqPack) {
        try {
            request.offer(reqPack, 5, TimeUnit.SECONDS);
            log.info("提交了一个请求");
            Msg_.Builder request = Msg_.newBuilder();
            // /< 请求ID
            request.setJobId(-1);
            // /< 将请求信息发送出去
            Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(request.build(), channel));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // /< 请求读取
    public RequestPackage takeRequest() {
        try {
            return request.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // /< 预处理队列
    private BlockingQueue<MonitorMsg> monitorMsg = new LinkedBlockingQueue<>();

    // /< 预处理信息提交
    public void submitMonitorMsg(MonitorMsg msg) {
        try {
            monitorMsg.offer(msg, 5, TimeUnit.SECONDS);
            log.info("提交了一条预处理信息");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // /< 获取预处理信息
    public MonitorMsg takeMonitorMsg() {
        try {
            return monitorMsg.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // /< 查找预处理相同信息
    public MonitorMsg checkMsg(MonitorMsg msg) {
        for (MonitorMsg tmp : monitorMsg) {
            if (tmp.getFile().equals(msg.getFile())) {
                return msg;
            }
        }
        return null;
    }

    // /< 修正消息
    public MonitorMsg reviseMsg(MonitorMsg msg) {

        // /< 需要删除的元素链表
        List<MonitorMsg> msgs = new LinkedList<>();
        // /< 查找需要删除的项目
        if (msg.getType() != Type.UPDATE) {
            for (MonitorMsg tmp : monitorMsg) {
                if (tmp.getFile().equals(msg.getFile()) && tmp.getType() == Type.UPDATE) {
                    msgs.add(tmp);
                }
            }
            // /< 删除元素            
            monitorMsg.removeAll(msgs);
            log.info("删除修改文件信息" + msgs.size() + "条" + msg.getFile());
        } else {
            // /< 如果是修改文件就需要查找有无新增文件
            for (MonitorMsg tmp : monitorMsg) {
                if (tmp.getFile().equals(msg.getFile())) {
                    msgs.add(tmp);
                }
            }

            for (MonitorMsg tmp : msgs) {
                log.info("删除修改文件信息 1 条, 由于与新增或删除文件冲突" + msg.getFile());
                monitorMsg.remove(tmp);
                if (tmp.getType() != Type.UPDATE) {
                    return tmp;
                }
            }

        }
        return msg;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    private ClientResource(){
    }

    private static ClientResource singleton = new ClientResource();

    public static ClientResource getSingleton() {
        return singleton;
    }
}
