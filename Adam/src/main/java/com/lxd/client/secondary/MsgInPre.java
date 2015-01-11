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
import com.lxd.client.task.job.server.DownloadFileTask;
import com.lxd.client.task.job.server.UpdateFileTask;
import com.lxd.client.task.result.console.AddFileResultPartTask;
import com.lxd.client.task.result.console.AddFileResultTask;
import com.lxd.client.task.result.console.DeleteFileResultTask;
import com.lxd.client.task.result.console.DownloadFileResultTask;
import com.lxd.client.task.result.console.UpdateFileResultPartTask;
import com.lxd.client.task.result.console.UpdateFileResultTask;
import com.lxd.client.task.result.user.LandingResultTask;
import com.lxd.client.task.result.user.RegisterResultTask;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.server.Server.Server_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.console.Console.Console_;
import com.lxd.protobuf.msg.result.user.User.User_;
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
    private ClientTask parseResult(Result_ msg) {
       ///< 返回的任务
        ClientTask result = null;
        
        if (msg.hasConsole()) {
            ///< 如果是发往控制台的结果消息
            result = parseRConsole(msg.getConsole());
        } else if (msg.hasUser()) {
            ///< 如果是发给用户的结果消息
            result = parseRUser(msg.getUser());
        } else {
            ///< 如果无法解析消息
            log.error("无法解析结果消息");
        }
        
        return result;
    }

    private ClientTask parseRUser(User_ msg) {
        ///< 返回得消息
        ClientTask result = null;
        
        if (msg.hasLanding()) {
            ///< 新建登陆结果任务
            LandingResultTask landing = new LandingResultTask();
            landing.setError_msg(msg.getLanding().getErrorMsg());
            landing.setSuccess(msg.getLanding().getSuccess());
            landing.setUser_name(msg.getLanding().getUser());
            result = landing;
        } else if (msg.hasRegister()) {
            ///< 注册结果任务
            RegisterResultTask register = new RegisterResultTask();
            register.setError_msg(msg.getRegister().getErrorMsg());
            register.setSuccess(msg.getRegister().getSuccess());
            result = register;
        } else {
            log.error("无法解析的用户结果消息");
        }
        return result;
    }

    private ClientTask parseRConsole(Console_ msg) {
        ///< 返回的消息
        ClientTask result = null;
        
        if (msg.hasAddFile()) {
            ///< 新建文件消息
            AddFileResultTask addFile = new AddFileResultTask();
            addFile.setSuccess(msg.getAddFile().getSuccess());            
            addFile.setEdition(msg.getAddFile().getEdition());
            addFile.setError_msg(msg.getAddFile().getErrorMsg());
            result = addFile;            
        } else if (msg.hasAddFilePart()) {
            ///< 新建文件片段结果消息
            AddFileResultPartTask addFilePart = new AddFileResultPartTask();
            addFilePart.setSuccess(msg.getAddFilePart().getSuccess());
            addFilePart.setCurrent(msg.getAddFilePart().getNumber());
            addFilePart.setError_msg(msg.getAddFilePart().getErrorMsg());
            result = addFilePart;
        } else if (msg.hasDeleteFile()) {
            ///< 删除文件结果消息
            DeleteFileResultTask deleteFile = new DeleteFileResultTask();
            deleteFile.setSuccess(msg.getDeleteFile().getSuccess());
            deleteFile.setError_msg(msg.getDeleteFile().getErrorMsg());
            result = deleteFile;
        } else if (msg.hasUpdateFile()) {
            ///< 更新文件结果消息
            UpdateFileResultTask updateFile = new UpdateFileResultTask();
            updateFile.setEdition(msg.getUpdateFile().getEdition());
            updateFile.setError_msg(msg.getUpdateFile().getErrorMsg());
            updateFile.setSuccess(msg.getUpdateFile().getSuccess());
            result = updateFile;
        } else if (msg.hasUpdateFilePart()) {
            ///< 更新文件片段结果消息
            UpdateFileResultPartTask updateFilePart = new UpdateFileResultPartTask();
            updateFilePart.setCurrent(msg.getUpdateFilePart().getNumber());
            updateFilePart.setError_msg(msg.getUpdateFilePart().getErrorMsg());
            updateFilePart.setSuccess(msg.getUpdateFilePart().getSuccess());
            result = updateFilePart;
        } else if (msg.hasDownloadFile()) {
            ///< 下载文件结果消息
            DownloadFileResultTask task = new DownloadFileResultTask();
            task.setSuccess(msg.getDownloadFile().getSuccess());
            task.setError_msg(msg.getDownloadFile().getErrorMsg());
            task.setEdition(msg.getDownloadFile().getEdition());
            result = task;
        } else {
            log.error("无法处理的控制台结果消息");
        }
        
        return result;       
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
        } else if (msg.hasDownloadFile()) {
            ///< 如果是删除文件
            DownloadFileTask task = new DownloadFileTask();
            task.setDownloadUrl(msg.getDownloadFile().getHttpPath());
            result = task;
        } else {
            // /< 如果无法解析消息
            log.error("无法解析该来自控制台的任务消息 ");
        }

        return result;
    }
}
