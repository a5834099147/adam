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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 消息队列
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月16日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class MsgQueue {
    
    ///< 日志
    private static final Logger log = LogManager.getLogger(MsgQueue.class);
    ///< 数据流入队列(从外界到本系统)
    private static BlockingQueue<DataPackage> msgInQueue = new LinkedBlockingDeque<>();
    ///< 数据流出队列(从本系统发往外界)
    private static BlockingQueue<DataPackage> msgOutQueue = new LinkedBlockingDeque<>();
    
    /*
     * 将数据加入到流入队列中
     */
    public void submitMsgInQueue(DataPackage data) {
        try {
            ///< 将消息放入到队列中, 如果队列满, 则等待5秒钟, 如果放入成功返回 true, 否则返回 false
            boolean ret = msgInQueue.offer(data, 5, TimeUnit.SECONDS);
            if (ret) {
                log.info("向 msgInQueue 中投递消息成功");
            } else {
                log.error("向 msgInQueue 中投递消息失败");
            }
            
            log.debug("当前 msgInQueue 中存在的消息为: " + msgInQueue.size() + "个");
        } catch (InterruptedException e) {
            log.error("向 msgInQueue 中提交消息失败");
            e.printStackTrace();
        }
    }
    
    /*
     * 将数据加入到流出队列
     */
    public void submitMsgOutQueue(DataPackage data) {
        try {
            ///< 将消息放入到队列中, 如果队列满, 则等待5秒钟, 如果放入成功返回 true, 否则返回 false
            boolean ret = msgOutQueue.offer(data, 5, TimeUnit.SECONDS);
            if (ret) {
                log.info("向 msgOutQueue 中投递消息成功");
            } else {
                log.error("向 msgOutQueue 中投递消息失败");
            }
            
            log.debug("当前 msgOutQueue 中存在的消息为: " + msgOutQueue.size() + "个");
        } catch (InterruptedException e) {
            log.error("向 msgOutQueue 中提交消息失败");
            e.printStackTrace();
        }
    }
    
    /*
     * 从流入队列中取出消息
     */
    public DataPackage takeMsgInQueue() {
        try {
            log.debug("从 msgInQueue 消息队列中获取消息, 当前存在 " +msgInQueue.size() +" 个消息");
            ///< 将消息从队列中取出, 如果队列中无消息, 则在此阻塞
            return msgInQueue.take();
        } catch (InterruptedException e) {
            log.error("从 msgInQueue 获取消息时失败");
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * 从流出队列中取出消息
     */
    public DataPackage takeMsgOutQueue() {
        try {
            log.debug("从 msgOutQueue 消息队列中获取消息, 当前存在 " +msgOutQueue.size() +" 个消息");
            ///< 将消息从队列中取出, 如果队列中无消息, 则在此阻塞
            return msgOutQueue.take();
        } catch (InterruptedException e) {
            log.error("从 msgOutQueue 获取消息时失败");
            e.printStackTrace();
        }
        return null;
    }
}
