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

package com.lxd.client.secondary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.task.ClientTask;
import com.lxd.client.task.IdTask;
import com.lxd.client.task.job.server.AddFileTask;
import com.lxd.client.task.job.server.UpdateFileTask;
import com.lxd.client.task.result.ResultTask;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.server.Server.Server_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;

/**
 * 读入消息预处理(将读入消息封装为不同的任务)
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class MsgInPre extends Thread {

    Logger log = LogManager.getLogger(MsgInPre.class);

    @Override
    public void run() {
        while (true) {
            // /< 线程从入消息队列中拿到一个消息包
            DataPackage data = Resource.getSingleton().getMsgQueue().takeMsgInQueue();
            // /< 生成Task待后期访问
            ClientTask task = null;

            // /< 消息处理
            task = assignment(data.getMsg_());

            if (task != null) {
                task.setJobId(data.getMsg_().getJobId());
                task.setChannel(data.getChannel());
                Resource.getSingleton().getTaskQueue().submitTaskQueue(task);
            }
        }
    }

    // /< 分析消息
    private ClientTask assignment(Msg_ msg) {
        // /< 返回的任务
        ClientTask result = null;

        if (msg.hasJob()) {
            // /< 如果是任务消息
            result = parseJob(msg.getJob());
        } else if (msg.hasResult()) {
            // /< 如果是结果消息
            result = parseResult(msg.getResult());
        } else {
            // /< 如果是无法解析的消息, 则为分配ID任务
            result = new IdTask();
        }

        return result;
    }

    // /< 解析结果消息
    private ResultTask parseResult(Result_ msg) {
        // /< 结果任务
        ResultTask task = new ResultTask();
        task.setSuccess(msg.getSuccess());
        task.setError_msg(msg.getErrorMessage());
        ///< 如果结果中存在块信息
        if (msg.hasRepleish()) {
            task.setBlock(msg.getRepleish().getBlock());
        } else {
            ///< 如果结果中不存在块信息
            task.setBlock(-1);
        }
        return task;
    }

    // /< 解析任务消息
    private ClientTask parseJob(Job_ msg) {
        // /< 返回的任务
        ClientTask result = null;

        if (msg.hasServer()) {
            // /< 如果是来自服务器的消息
            result = parseJServer(msg.getServer());
        } else {
            // /< 如果无法解析消息
            log.error("无法解析该任务消息");
        }

        return result;
    }

    // /< 解析控制台任务消息
    private ClientTask parseJServer(Server_ msg) {
        // /< 返回的任务
        ClientTask result = null;

        if (msg.hasAddFile()) {
            result = new AddFileTask();
        } else if (msg.hasUpdateFile()) {
            // /< 如果是修改文件
            UpdateFileTask task = new UpdateFileTask();
            task.setInfos(msg.getUpdateFile().getInformationsList());
            result = task;
        } else {
            // /< 如果无法解析消息
            log.error("无法解析该来自控制台的任务消息 ");
        }

        return result;
    }
}
