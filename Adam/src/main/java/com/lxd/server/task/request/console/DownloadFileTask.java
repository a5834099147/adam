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
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.server.DownloadFile.DownloadFile_;
import com.lxd.protobuf.msg.job.server.Server.Server_;
import com.lxd.resource.Resource;
import com.lxd.server.entity.File;
import com.lxd.server.resource.property.ConsoleDownloadFile;
import com.lxd.server.service.FileService;
import com.lxd.server.service.RayFileService;
import com.lxd.server.service.impl.FileServiceImpl;
import com.lxd.server.service.impl.RayFileServiceImpl;
import com.lxd.utils.Grnerate;


/**
 * 下载文件任务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月11日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class DownloadFileTask extends ConsoleTask {
    ///< 下载文件需求地址
    private String path;
    
    public void setPath(String path) {
        this.path = path;
    }
    
    private static final FileService fileService = new FileServiceImpl();
    private static final RayFileService rayFileService = new RayFileServiceImpl();
    
    @Override
    public Msg_ taskExecute() {        
        File file = fileService.searchFile(getUser_name(), path);    
        
        ///< 将文件信息录入到任务组中
        Resource.getSingleton().getJobStatus().addJob(getJobId(), new ConsoleDownloadFile(path, file.getMd5(), file.getEdition()));
            
        
        String file_path = Grnerate.getRemotePath(file.getMd5(), file.getLength());
        
        ///< 创建返回消息
        Msg_.Builder msg = Msg_.newBuilder();
        ///< 设置任务编号
        msg.setJobId(getJobId());
        ///< 创建任务消息
        Job_.Builder job = Job_.newBuilder();
        ///< 创建服务器任务消息
        Server_.Builder server = Server_.newBuilder();
        ///< 创建下载文件服务器任务消息
        DownloadFile_.Builder downloadFile = DownloadFile_.newBuilder();
        downloadFile.setHttpPath(rayFileService.downloadFile(file_path));
        server.setDownloadFile(downloadFile);
        job.setServer(server);
        msg.setJob(job);
        
        ///< 返回消息
        return msg.build();        
    }
}
