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

package com.lxd.server.resource;

/**
 * 资源单例
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月16日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class Resource {
    ///< 消息队列
    private MsgQueue msgQueue = null;   
    ///< 任务队列
    private TaskQueue taskQueue = null;
    
    ///< 任务状态列表
    private JobStatus jobStatus = null;
    
    public MsgQueue getMsgQueue() {
        return msgQueue;
    }
    
    public TaskQueue getTaskQueue() {
        return taskQueue;
    }
    
    public JobStatus getJobStatus() {
        return jobStatus;
    }

    ///< 单例构造函数
    private Resource() {
        msgQueue = new MsgQueue();
        taskQueue = new TaskQueue();
        jobStatus = new JobStatus();
    }
    
    ///< 单例实体
    private static Resource singleton = null;
    
    ///< 得到单例
    public static Resource getSingleton() {
        if (singleton == null) {
            synchronized (Resource.class) {
                singleton = new Resource();
            }
        }        
        return singleton;
    }
}
