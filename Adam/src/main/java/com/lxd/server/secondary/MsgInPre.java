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

package com.lxd.server.secondary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.console.Console.Console_;
import com.lxd.protobuf.msg.request.user.User.User_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;
import com.lxd.server.task.IdTask;
import com.lxd.server.task.ServerTask;
import com.lxd.server.task.job.JobTask;
import com.lxd.server.task.job.console.AddFileTask;
import com.lxd.server.task.job.console.UpdateFileTask;
import com.lxd.server.task.request.console.ConsoleTask;
import com.lxd.server.task.request.console.DeleteFileTask;
import com.lxd.server.task.request.user.LadingTask;
import com.lxd.server.task.request.user.RegisterTask;
import com.lxd.server.task.request.user.UserTask;


/**
 * 读入消息预处理(将读入消息封装为不同的任务)
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月16日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class MsgInPre extends Thread  {
    Logger log = LogManager.getLogger(MsgInPre.class);
    
    @Override
    public void run() {
        while (true) {
            ///< 线程从入消息队列中拿到一个消息包
            DataPackage data = Resource.getSingleton().getMsgQueue().takeMsgInQueue();
            ///< 生成Task待后期访问
            ServerTask serverTask = null;
            if (data.getMsg_().getJobId() == -1) {
                serverTask = new IdTask();
            } else {
                ///< 如果是任务类消息
                serverTask = assignment(data.getMsg_());
            }
            
            if (serverTask != null) {
                serverTask.setJobId(data.getMsg_().getJobId());
                serverTask.setChannel(data.getChannel());
                Resource.getSingleton().getTaskQueue().submitTaskQueue(serverTask);
            }
        }
    }
    
    ///< 分析消息
    private ServerTask assignment(Msg_ msg) {
        ///< 返回的任务
        ServerTask result = null;
        
        if (msg.hasRequest()) {
            ///< 如果是请求消息
            result = parseRequest(msg.getRequest());
            
        } else if (msg.hasJob()) {
            ///< 如果是任务消息
            result = parseJob(msg.getJob());
            
        } else {
            ///< 如果是无法解析的消息
            log.error("无法解析的消息");            
        }
        
        return result;
    }
   
    ///< 解析请求
    private ServerTask parseRequest(Request_ msg) {
        ///< 返回的任务
        ServerTask result = null;
        
        if (msg.hasConsole()) {
            ///< 如果是来自控制台的消息
            result = parseRConsole(msg.getConsole());
        } else if (msg.hasUser()) {
            ///< 如果是来自用户的消息
            result = parseRUser(msg.getUser());
        } else {
            ///< 如果是无法解析的消息
            log.error("无法解析该请求消息");
        }
        return result;
    }
    
    ///< 解析控制台请求
    private ConsoleTask parseRConsole(Console_ msg) {
        ///< 返回的任务
        ConsoleTask result = null;
        
        if (msg.hasAddFile()) {
            ///< 如果是来自控制台的增加文件的请求消息
            com.lxd.server.task.request.console.AddFileTask task = new com.lxd.server.task.request.console.AddFileTask();
            task.setMd5(msg.getAddFile().getMd5());
            task.setLength(msg.getAddFile().getLength());
            task.setPath(msg.getAddFile().getPath());
            result = task;
            
        } else if (msg.hasDeleteFile()) {
            ///< 如果是来自控制台的删除文件的请求消息
            DeleteFileTask task = new DeleteFileTask();
            task.setPath(msg.getDeleteFile().getPath());
            result = task;
            
        } else if (msg.hasUpdateFile()) {
            ///< 如果是来自控制台的更新文件的请求消息
            com.lxd.server.task.request.console.UpdateFileTask task = new com.lxd.server.task.request.console.UpdateFileTask();
            task.setPath(msg.getUpdateFile().getPath());
            task.setMd5(msg.getUpdateFile().getMd5());
            task.setLength(msg.getUpdateFile().getLength());            
            result = task;
            
        } else {
            ///< 如果无法解析的消息
            log.error("无法解析该来自控制台的请求消息");
        }
        
        ///< 设置需要的附属值
        if (result != null) {
            result.setUser_name(msg.getUserName());
        }
        return result;
    }
    
    ///< 解析用户消息
    private UserTask parseRUser(User_ msg) {
        ///< 返回的任务
        UserTask result = null;
        
        if (msg.hasLanding()) {
            ///< 如果是来自用户的登陆消息
            LadingTask task = new LadingTask();
            task.setUser_name(msg.getLanding().getUserName());
            task.setUser_pwd(msg.getLanding().getUserPwd());
            result = task;
            
        } else if (msg.hasRegister()) {
            ///< 如果是来自用户的注册消息
            RegisterTask task = new RegisterTask();
            task.setUser_name(msg.getRegister().getUserName());
            task.setUser_pwd(msg.getRegister().getUserPwd());
            result = task;
            
        } else {
            ///< 如果无法解析消息
            log.error("无法解析该来自用户的消息");
        }
        
        return result;
    }
    
    ///< 解析任务消息
    private JobTask parseJob(Job_ msg) {
        ///< 返回的任务
        JobTask result = null;
        
        if (msg.hasConsole()) {
            ///< 如果是来自控制台的消息
            result = parseJConsole(msg.getConsole());
        } else {
            ///< 如果无法解析消息
            log.error("无法解析该任务消息");
        }
        
        return result;
    }
    
    ///< 解析控制台任务消息
    private JobTask parseJConsole(com.lxd.protobuf.msg.job.console.Console.Console_ msg) {
        ///< 返回的任务
        JobTask result = null;
        
        if (msg.hasAddFile()) {
            ///< 如果是来自控制台的增加文件任务
           AddFileTask task = new AddFileTask();
           task.setTotal_lump(msg.getAddFile().getTotalLump());
           task.setCurrent_lump(msg.getAddFile().getCurrentLump());
           task.setDatas(msg.getAddFile().getDatas().toByteArray());
           result = task;
            
        } else if (msg.hasUpdateFile()) {
            ///< 如果是来自控制台的更新文件任务
            UpdateFileTask task = new UpdateFileTask();
            task.setTotal_lump(msg.getUpdateFile().getTotalLump());
            task.setCurrent_lump(msg.getUpdateFile().getCurrentLump());
            task.setPatch(msg.getUpdateFile().getPatchList());
            result = task;
            
        } else {
            ///< 如果无法解析消息
            log.error("无法解析该来自控制台的任务消息 ");
        }
        
        return result;
    }
}
