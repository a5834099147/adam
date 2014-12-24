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
import com.lxd.protobuf.msg.job.server.Server.Server_;
import com.lxd.protobuf.msg.job.server.UpdateFile.UpdateFile_;
import com.lxd.resource.Resource;
import com.lxd.server.resource.property.ConsoleAddFile;

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

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Msg_ taskExecute() {
        // /< 创建文件信息trunk
        // TODO 数据库查询是否有该文件

        // /< 将文件信息录入到任务组中
        //TODO 得到原来文件的MD5和Length
        Resource.getSingleton().getJobStatus().addJob(getJobId(), new ConsoleAddFile(md5, length, path));

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
        //TODO 增加文件块信息
        server.setUpdateFile(updateFile);
        job.setServer(server);
        msg.setJob(job);

        // /< 返回消息
        return msg.build();
    }

}
