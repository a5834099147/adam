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

package com.lxd.server.threadpool.impl;

import com.lxd.server.resource.Resource;
import com.lxd.task.Task;
import com.lxd.threadpool.Worker;


/**
 * 工作线程
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月18日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ServerWorker extends Worker {
    private boolean isRun = true;
    
    @Override
    public void run() {
        while (isRun) {
            ///< 从线程队列中得到工作任务
            Task task = Resource.getSingleton().getTaskQueue().takeTaskQueue();
            ///< 运行任务
            task.execute();
        }       
    }

    @Override
    public void close() {
        isRun = false;        
    }
}
