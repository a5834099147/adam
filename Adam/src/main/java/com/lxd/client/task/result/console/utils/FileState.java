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

package com.lxd.client.task.result.console.utils;


/**
 * 文件状态, 为同步服务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月19日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileState {
    ///< 文件信息
    public enum State {
        ///< 上传
        UPLOAD,
        ///< 下载
        DOWNLOAD,
        ///< 更新
        UPDATE,
        ///< 更名
        RENAME,
        ///< 删除
        DELETE,
        ///< 删除表
        DELETE_TABLE,
        ///< 删除实体
        DELETE_ENTITY
    };
    ///< 文件路径
    private String path;
    
    ///< 文件状态
    private State state;

    public FileState(String path, State state){
        super();
        this.path = path;
        this.state = state;
    }

    
    public String getPath() {
        return path;
    }

    
    public State getState() {
        return state;
    } 
}
