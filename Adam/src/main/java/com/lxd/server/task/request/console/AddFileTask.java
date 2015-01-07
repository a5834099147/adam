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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.server.AddFile.AddFile_;
import com.lxd.protobuf.msg.job.server.Server.Server_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.resource.Resource;
import com.lxd.server.entity.File;
import com.lxd.server.resource.ServerResource;
import com.lxd.server.resource.property.ConsoleAddFile;
import com.lxd.server.service.FileServer;
import com.lxd.server.service.impl.FileServerImpl;
import com.lxd.utils.Grnerate;


/**
 * 客户端请求增加文件任务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class AddFileTask extends ConsoleTask {
    private static final Logger log = LogManager.getLogger(AddFileTask.class);
    
    ///< 文件业务
    private FileServer fileServer = new FileServerImpl();
    
    ///< 文件MD5值
    private String md5;
    ///< 文件长度
    private long length;
    ///< 文件路径
    private String path;
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    public void setLength(long length) {
        this.length = length;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Msg_ taskExecute() {
        ///< 数据库查询是否有该文件 以下路径为没有的情况
        if (!ServerResource.getSingleton().checkPath(Grnerate.getPath(md5, length)) || fileServer.havaFile(md5, length)) {
            File file = new File();
            file.setLength(length);
            file.setMd5(md5);
            file.setPath(path);
            file.setUser_name(getUser_name());
            
            fileServer.addFile(file);
            log.info("文件快传信息建立, 文件路径:" + path + ", 文件大小:" + length);
            
            ///< 返回结果信息
            Msg_.Builder msg = Msg_.newBuilder();
            Result_.Builder result = Result_.newBuilder();
            result.setSuccess(true);           
            msg.setResult(result);
            msg.setJobId(getJobId());
            return msg.build();
            
        }
        
        
        ///< 将文件信息录入到任务组中
        Resource.getSingleton().getJobStatus().addJob(getJobId(), new ConsoleAddFile(md5, length, path, getUser_name()));
        
        String file_path = Grnerate.getPath(md5, length);
        fileServer.addFile(file_path, length);
        
        ///< 创建返回消息
        Msg_.Builder msg = Msg_.newBuilder();
        ///< 设置任务编号
        msg.setJobId(getJobId());
        ///< 创建任务消息
        Job_.Builder job = Job_.newBuilder();
        ///< 创建服务器任务消息
        Server_.Builder server = Server_.newBuilder();
        ///< 创建增加文件服务器任务消息
        AddFile_.Builder addFile = AddFile_.newBuilder(); 
        server.setAddFile(addFile);        
        job.setServer(server);
        msg.setJob(job);
        
        ///< 返回消息
        return msg.build();        
    }
}
