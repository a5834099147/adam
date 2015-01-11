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

package com.lxd.server.task.request.console;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.console.Console.Console_;
import com.lxd.protobuf.msg.result.console.DeleteFile.DeleteFile_;
import com.lxd.server.entity.File;
import com.lxd.server.service.FileService;
import com.lxd.server.service.impl.FileServiceImpl;


/**
 * 客户端请求删除文件
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class DeleteFileTask extends ConsoleTask {
    ///< 请求文件路径
    private String path;    
    
    ///< 文件服务
    private FileService fileService = new FileServiceImpl();
    
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Msg_ taskExecute() {
        File file = fileService.searchFile(getUser_name(), path);
        fileService.deleteFile(file);
        
        ///< 创建结果消息
        Result_.Builder result = Result_.newBuilder();
        //<创建控制台结果消息
        Console_.Builder console = Console_.newBuilder();
        ///< 创建删除文件结果消息
        DeleteFile_.Builder deleteFile = DeleteFile_.newBuilder();     
        ///< 设置成功标志
        deleteFile.setSuccess(true);
        ///< 在控制台结果中加入删除文件结果消息
        console.setDeleteFile(deleteFile);
        ///< 在结果消息中加入控制台结果消息
        result.setConsole(console);
        ///<创建消息
        Msg_.Builder msg = Msg_.newBuilder();
        ///< 在消息中加入结果消息
        msg.setResult(result);
        msg.setJobId(getJobId());
        
        ///< 返回消息
        return msg.build();
    }

}
