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

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 服务器资源文件
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月7日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ServerResource {
    ///< 日志
    private static final Logger log = LogManager.getLogger(ServerResource.class);
    
    ///< 线程安全的 HashSet
    private Set<String> pathSet = Collections.synchronizedSet(new HashSet<String>());
    
    ///< 网络服务器上传队列
    private BlockingQueue<File> fileQueue = new LinkedBlockingQueue<File>();
    
    ///< 提交文件到上传队列
    public void submitFile(File file) {
        try {
            ///< 将消息放入到队列中, 如果队列满, 则等待5秒钟, 如果放入成功返回 true, 否则返回 false
            boolean ret = fileQueue.offer(file, 5, TimeUnit.SECONDS);
            if (ret) {
                log.debug("向 fileQueue 中投递消息成功");
            } else {
                log.debug("向 fileQueue 中投递消息失败");
            }
            
            log.debug("当前 fileQueue 中存在的消息为: " + fileQueue.size() + "个");
        } catch (InterruptedException e) {
            log.error("向 fileQueue 中提交消息失败");
            e.printStackTrace();
        }
    }
    
    ///< 获取文件从上传队列中
    public File takeFile() {
        try {
            log.debug("从 fileQueue 消息队列中获取消息, 当前存在 " +fileQueue.size() +" 个消息");
            ///< 将消息从队列中取出, 如果队列中无消息, 则在此阻塞
            return fileQueue.take();
        } catch (InterruptedException e) {
            log.error("从 fileQueue 获取消息时失败");
            e.printStackTrace();
        }
        return null;
    }
    
    ///< 检查目录
    public boolean checkPath(String path) {
        ///< 如果包含了该目录
        if (pathSet.contains(path)) {
            return false;
        } else {
            pathSet.add(path);
            return true;
        }
    }

    private ServerResource(){
    }

    private static ServerResource singleton = new ServerResource();

    public static ServerResource getSingleton() {
        return singleton;
    }
}
