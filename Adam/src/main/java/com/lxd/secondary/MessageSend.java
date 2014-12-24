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

package com.lxd.secondary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;


/**
 * 发送消息线程
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月18日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class MessageSend extends Thread {
    private static final Logger log = LogManager.getLogger(MessageSend.class);
    
    @Override 
    public void run() {
        while (true) {
            ///< 得到数据包
            DataPackage dataPackage = Resource.getSingleton().getMsgQueue().takeMsgOutQueue();
            ///< 发送数据包
            dataPackage.getChannel().write(dataPackage.getMsg_());
            dataPackage.getChannel().flush();
            log.info("发送线程发送一条消息");
        }
    }
}
