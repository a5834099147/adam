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

import java.util.List;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.server.Server.Server_;
import com.lxd.protobuf.msg.job.server.UpdateFile.Information;
import com.lxd.protobuf.msg.job.server.UpdateFile.UpdateFile_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.console.Console.Console_;
import com.lxd.resource.Resource;
import com.lxd.server.entity.File;
import com.lxd.server.resource.property.ConsoleUpdataFile;
import com.lxd.server.service.FileServer;
import com.lxd.server.service.impl.FileServerImpl;
import com.lxd.sync.Chunk;
import com.lxd.sync.RsyncUtil;
import com.lxd.utils.Grnerate;

/**
 * 客户端请求更新文件
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class UpdateFileTask extends ConsoleTask {

    // /< 请求文件路径
    private String path;    
    ///< 文件md5
    private String md5;
    //< 文件length;
    private Long length;
    ///< 文件最后修改文件
    private Long last;
    ///< 任务个数
    private Integer total;    
    
    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setLast(Long last) {
        this.last = last;
    }

    public void setPath(String path) {
        this.path = path;
    }    
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    public void setLength(Long length) {
        this.length = length;
    }
    
    private FileServer fileServer = new FileServerImpl();

    @Override
    public Msg_ taskExecute() {
        ///< 查找到修改前的文件信息
        File oldFile = fileServer.searchFile(getUser_name(), path);
        
        ///< 查找是否存在更新后的文件信息
        if (fileServer.havaFile(md5, length)) {
            ///< 更新表信息
            Integer edition = fileServer.updateFile(oldFile, md5, length, last);
            ///< 回复结果消息
            Msg_.Builder msg = Msg_.newBuilder();
            msg.setJobId(getJobId());
            Result_.Builder result = Result_.newBuilder();
            ///< 控制台结果消息创建
            Console_.Builder console = Console_.newBuilder();
            ///< 更新文件结果消息创建
            com.lxd.protobuf.msg.result.console.UpdateFile.UpdateFile_.Builder updateFile = com.lxd.protobuf.msg.result.console.UpdateFile.UpdateFile_.newBuilder();
            ///< 设置成功标志位
            updateFile.setSuccess(true);
            ///< 设置文件版本号
            updateFile.setEdition(edition);
            ///< 向控台台结果追加更新文件结果
            console.setUpdateFile(updateFile);
            ///< 向结果消息追加控制台结果消息
            result.setConsole(console);      
            ///< 向消息中追加结果消息
            msg.setResult(result);
            
            return msg.build();
        }      
       
        Resource.getSingleton().getJobStatus().addJob(getJobId(), new ConsoleUpdataFile(md5, length, path, getUser_name(), last));
        ///< 初始化
        Resource.getSingleton().getJobStatus().init(getJobId(), total);
        
        ///< 创建更新文件
        String file_path = Grnerate.getPath(md5, length);
        fileServer.addFile(file_path, length);

        // /< 创建返回消息
        Msg_.Builder msg = Msg_.newBuilder();
        // /< 设置任务编号
        msg.setJobId(getJobId());
        // /< 创建任务消息
        Job_.Builder job = Job_.newBuilder();
        // /< 创建服务器任务消息
        Server_.Builder server = Server_.newBuilder();
        // /< 创建修改文件服务器任务消息
        UpdateFile_.Builder updateFile = UpdateFile_.newBuilder();
        ///< 得到文件信息
        List<Chunk> chunks = RsyncUtil.getFileCheckInfo(Grnerate.getPath(oldFile.getMd5(), oldFile.getLength()));
        
        for (Chunk chunk : chunks) {
          ///< 文件信息存储模块
            Information.Builder info = Information.newBuilder();
            ///< 向文件信息块中增加弱校验和
            info.setAdler32(chunk.getAdler32());
            ///< 向文件信息块中追加强校验和
            info.setMd5(chunk.getMd5());
            ///< 向文件信息块中追加块编号
            info.setInfoId(chunk.getIndex());
            ///< 向文件信息块中追加块大小
            info.setLength(chunk.getLength());
            ///< 将文件块信息加入到文件快信息列表中
            updateFile.addInformations(info);
        }
        server.setUpdateFile(updateFile);
        job.setServer(server);
        msg.setJob(job);

        // /< 返回消息
        return msg.build();
    }
}
