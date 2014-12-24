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

package com.lxd.resource;

import io.netty.channel.Channel;

import com.lxd.protobuf.msg.Msg.Msg_;


/**
 * 数据包装类
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月16日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class DataPackage {
    ///< 封装的消息
    private Msg_ msg_;
    ///< 发送或发出的通道
    private Channel channel;
    
    public DataPackage(Msg_ msg, Channel channel) {
        this.msg_ = msg;
        this.channel = channel;
    }
    
    public Msg_ getMsg_() {
        return msg_;
    }
    
    public void setMsg_(Msg_ msg_) {
        this.msg_ = msg_;
    }
    
    public Channel getChannel() {
        return channel;
    }
    
    public void setChannel(Channel channel) {
        this.channel = channel;
    }    
}
