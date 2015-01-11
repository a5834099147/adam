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

package com.lxd.client.task.job.server;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.ByteString;
import com.lxd.client.resource.property.ServerAddFile;
import com.lxd.client.task.ClientTask;
import com.lxd.client.task.job.server.util.FileUtil;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.console.AddFile.AddFile_;
import com.lxd.protobuf.msg.job.console.Console.Console_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;
import com.lxd.utils.Define;

/**
 * 来自服务器的增加文件任务
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class AddFileTask extends ClientTask {

    private static final Logger log = LogManager.getLogger(AddFileTask.class);

    @Override
    public void execute() {
        // /< 从 文件中得到现有的文件属性
        ServerAddFile obj = (ServerAddFile) Resource.getSingleton().getJobStatus().getProperty(getJobId());
        // /< 得到增加文件的属性
        String path = obj.getPath();
        // /< 得到文件的大小
        File file = new File(path);

        // /< 获得的任务编号
        int current = 0;

        if (Resource.getSingleton().getJobStatus().getNotStart(getJobId()).size() != 0) {
            current = Resource.getSingleton().getJobStatus().getNotStart(getJobId()).get(0);
        } else {
            log.info("该任务没有做任何事情");
            return;
        }

        // /< 检查进度
       if ( Resource.getSingleton().getJobStatus().checkToDo(getJobId(), obj.getTotal(), current)) {
           byte[] datas = FileUtil.read(file, current * Define.BLOCK_SIZE);

           Msg_.Builder msg = Msg_.newBuilder();
           msg.setJobId(getJobId());
           // /< 设置任务消息
           Job_.Builder job = Job_.newBuilder();
           // /< 设置来自控制台的任务消息
           Console_.Builder console = Console_.newBuilder();
           // /< 设置来自控制台的增加文件任务消息
           AddFile_.Builder addFile = AddFile_.newBuilder();
           // /< 当前是第几块数据
           addFile.setCurrentLump(current);
           // /< 该任务数据总块数
           addFile.setTotalLump(obj.getTotal());
           // /< 设置该块数据
           addFile.setDatas(ByteString.copyFrom(datas));

           console.setAddFile(addFile);
           job.setConsole(console);
           msg.setJob(job);
           Resource.getSingleton().getJobStatus().setDoing(getJobId(), current);

           // /< 将消息放入到发送队列中
           Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(msg.build(), getChannel()));
           return;
       }
       
       log.info("分配了错误的任务块编号");
       
    }

}
