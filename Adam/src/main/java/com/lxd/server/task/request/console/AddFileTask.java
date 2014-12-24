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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.server.AddFile.AddFile_;
import com.lxd.protobuf.msg.job.server.Server.Server_;
import com.lxd.resource.Resource;
import com.lxd.server.resource.property.ConsoleAddFile;
import com.lxd.utils.Grnerate;
import com.lxd.utils.Utils;


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
        //TODO 数据库查询是否有该文件
        
        ///< 将文件信息录入到任务组中
        Resource.getSingleton().getJobStatus().addJob(getJobId(), new ConsoleAddFile(md5, length, path));
        
        try {
            ///< 得到文件路径
            String fileString = Grnerate.getPath(md5, length);
            ///< 创建文件
            File file = new File(fileString);
            file.createNewFile();
            RandomAccessFile raf = new RandomAccessFile(fileString, "rw");
            log.info("创建文件成功" + fileString);
            raf.setLength(length);
            log.info("给文件" + fileString + "分配的大小为 :" + length);
            Utils.closeConnection(raf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
