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

import com.lxd.task.Task;


/**
 * 任务队列
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月17日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class TaskQueue {
    ///< 日志
    private static final Logger log = LogManager.getLogger(TaskQueue.class);
    ///< 任务队列
    private static BlockingQueue<Task> taskQueue = new LinkedBlockingDeque<>();
    
    /*
     * 将任务放入到队列中
     */
    public void submitTaskQueue(Task serverTask) {
        try {
            ///< 将任务放入到队列中, 如果队列已满, 则等待5秒钟, 如果放入成功返回true, 否则返回 false
            boolean ret = taskQueue.offer(serverTask, 5, TimeUnit.SECONDS);
            if (ret) {
                log.info("向 taskQueue 中投递任务成功");
            } else {
                log.error("向 taskQueue 中投递任务失败");
            }
            
            log.debug("当前 taskQueue 中存在的任务数量为:" + taskQueue.size() + "个");
        } catch (InterruptedException e) {
            log.error("向 taskQueue 中提交任务失败");
            e.printStackTrace();
        }
    }
    
    public Task takeTaskQueue() {
        try {
            log.debug("从 taskQueue 任务队列中获取任务, 当前存在 " + taskQueue.size() + "个任务");
            ///< 将任务从队列中取出, 如果队列中没有任务, 则在此阻塞
            return taskQueue.take();
        } catch (InterruptedException e) {
            log.error("从 taskQueue 获取任务时失败");
            e.printStackTrace();
        }
        return null;
    }
    
}
