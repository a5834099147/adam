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

import java.util.LinkedList;
import java.util.List;

import com.lxd.server.threadpool.ThreadPoolInterface;
import com.lxd.server.threadpool.Worker;


/**
 * 服务器任务线程池
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月18日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class TaskThreadPool implements ThreadPoolInterface{
    ///< 工作线程
    private List<Worker> workers = null;
    ///< 工作线程数量(初始化)
    private final int NUM = 20; 

    @Override
    public void start() {
        workers = new LinkedList<Worker>();
        ///< 生成并开启工作线程
        for (int i = 0; i < NUM; ++i) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    @Override
    public void stop() {
        ///< 关闭线程池数组中的所有线程
        for (Worker worker : workers) {
            worker.clolse();
        }
    }

}
