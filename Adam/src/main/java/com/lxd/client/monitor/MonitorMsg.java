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


/**
 * 文件监听消息
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月6日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class MonitorMsg {
    ///< 操作类型
    public enum Type {
        ADD, 
        DELETE,
        UPDATE
    };
    
    ///< 新增文件逻辑类型
    public enum State {
        BEGIN, ///< 开始
        ACCESS///< 可以被访问        
    };
    
    ///< 文件全路径
    private File file;
    ///< 操作类型
    private Type type;
    ///< 文件状态
    private State state = State.BEGIN;   
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }

    ///< 任务开启时间(默认为0, 表示当前时间)
    private Long start = 0L;   
    
    public Long getStart() {
        return start;
    }
    
    public void setStart(Long start) {
        this.start = start;
    }
    
    public MonitorMsg(File file, Type type){
        super();
        this.file = file;
        this.type = type;
    }

    public File getFile() {
        return file;
    }
    
    public Type getType() {
        return type;
    }   
}
