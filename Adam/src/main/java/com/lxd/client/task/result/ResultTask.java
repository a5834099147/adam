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

package com.lxd.client.task.result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.resource.property.ServerLanding;
import com.lxd.client.resource.property.ServerRegiest;
import com.lxd.client.task.ClientTask;
import com.lxd.handle.HandleResource;
import com.lxd.resource.Resource;


/**
 * 结果处理任务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ResultTask extends ClientTask {
    private static final Logger log = LogManager.getLogger(ResultTask.class); 
    ///< 结果
    private Boolean success;
    ///< 错误消息
    private String error_msg;
    ///< 结果中的块号
    private int block;    
    
    public int getBlock() {
        return block;
    }

    
    public void setBlock(int block) {
        this.block = block;
    }

    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public String getError_msg() {
        return error_msg;
    }
    
    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @Override
    public void execute() {
       Object object = Resource.getSingleton().getJobStatus().getProperty(getJobId());
       ///< 如果是注册消息
       if (object instanceof ServerRegiest) {
           ///< 如果注册成功
           if (success) {
               HandleResource.getSingleton().getReg().regSuccess();
           } else {
               ///< 如果注册失败
               HandleResource.getSingleton().getReg().regFail(error_msg);
           }           
       } else if (object instanceof ServerLanding) {
           ///< 登陆的处理逻辑
           if (success) {
               ///< 从任务附加信息中获取用户名
               ServerLanding msg = (ServerLanding) Resource.getSingleton().getJobStatus().getProperty(getJobId());
               ///< 将用户名传递给登陆成功处理方法
               HandleResource.getSingleton().getLogin().loginSuccess(msg.getUser_name());
           } else {
               HandleResource.getSingleton().getLogin().loginFail(error_msg);
           }
       } 
       
       if (success) {
           if (block != -1) {
               log.info("任务编号:" + getJobId() + " 块号:" + block + "正确完成");
           } else {
               Resource.getSingleton().getJobStatus().setDone(getJobId());
               log.info("任务编号: " + getJobId() + "正确完成");
           }           
       } else {
           if (block != -1) {
               //TODO 重试块
               log.error("任务编号:" + getJobId() + " 块号:" + block + "错误, 错误信息:" + error_msg);
           } else {
               //TODO 重试任务
               log.info("任务编号: " + getJobId() + "错误, 错误信息:" + error_msg);
           }      
       }
    }    
}
