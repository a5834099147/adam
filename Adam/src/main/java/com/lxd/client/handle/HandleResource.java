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

package com.lxd.client.handle;

import com.lxd.client.handle.impl.AddFileImpl;
import com.lxd.client.handle.impl.DeleteFileImpl;
import com.lxd.client.handle.impl.UpdateFileImpl;
import com.lxd.client.view.control.UiSingleton;


/**
 * 句柄处理单例
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月5日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class HandleResource {
  ///< 单例变量
    private static HandleResource singleton = new HandleResource();
        
    private HandleResource() {
    }   
    
    public static HandleResource getSingleton() {
        return singleton;
    }     
    
    ///< 新增文件处理句柄
    private AddFileHandle addFile = new AddFileImpl();
    ///< 修改文件处理句柄
    private UpdateFileHandle updateFile = new UpdateFileImpl();
    ///< 删除文件处理句柄
    private DeleteFileHandle deleteFile = new DeleteFileImpl();

    ///< 获得登陆处理句柄
    public LoginHandle getLogin() {
        return UiSingleton.getSingleton().getLoginPanel();
    }
    
    ///< 获得注册处理句柄
    public RegHandle getReg() {
        return UiSingleton.getSingleton().getRegPanel();
    }
    
    ///< 获得新增文件处理句柄
    public AddFileHandle getAddFile() {
        return addFile;
    }
    
    ///< 获得修改文件处理句柄
    public UpdateFileHandle getUpdateFile() {
        return updateFile;
    }
    
    ///< 获取删除文件处理句柄
    public DeleteFileHandle getDeleteFile() {
        return deleteFile;
    }
}
