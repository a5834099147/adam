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

package com.lxd.client.handle.impl;

import com.lxd.client.entity.File;
import com.lxd.client.entity.Log;
import com.lxd.client.handle.DeleteFileHandle;
import com.lxd.client.resource.property.ServerDeleteFile;
import com.lxd.client.service.FileServer;
import com.lxd.client.service.LogServer;
import com.lxd.client.service.impl.FileServerImpl;
import com.lxd.client.service.impl.LogServerImpl;
import com.lxd.client.view.control.UiSingleton;
import com.lxd.resource.Resource;
import com.lxd.utils.Grnerate;


/**
 * 删除文件结果处理
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月8日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class DeleteFileImpl implements DeleteFileHandle {    
    private LogServer logServer = new LogServerImpl();
    private FileServer fileServer = new FileServerImpl();

    @Override
    public void deleteFileSuccess(Long id) {
        ///< 得到任务信息
        ServerDeleteFile pro =  (ServerDeleteFile) Resource.getSingleton().getJobStatus().getProperty(id);
        ///< 添加日志
        Log log = new Log();
        log.setId(id);
        log.setState(true);
        ///< 得到用户名
        log.setUser_name(UiSingleton.getSingleton().getUser());
        ///< 保存日志
        logServer.addLog(log);
        
        ///< 添加文件
        File file = new File();
        file.setUser_name(UiSingleton.getSingleton().getUser());
        file.setPath(Grnerate.getClientPath(pro.getPath()));
        
        ///< 保存新增文件记录
        fileServer.deleteFile(file);
    }

    @Override
    public void deleteFileError(Long id) {
        ///< 添加日志
        Log log = new Log();
        log.setId(id);
        log.setState(false);
        ///< 得到用户名
        log.setUser_name(UiSingleton.getSingleton().getUser());
        ///< 保存日志
        logServer.addLog(log);        
    }

}
