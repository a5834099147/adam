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

package com.lxd.client.monitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;


/**
 * 客户端目录事件监听
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月4日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class MonitorDir{
    
    private String filePath = null;    
    
    public MonitorDir(String filePath){
        this.filePath = filePath;
        fileWatch();
    }   
    
    private void fileWatch() {
        //文件变更器  
        FileAlterationMonitor  monitor = new FileAlterationMonitor(100); //5s扫描一次  
        //目录观察者  
        FileAlterationObserver observer = new FileAlterationObserver(new File(filePath));
        
        ///< 监听器接口
        observer.addListener(new AdamFileListener());
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
