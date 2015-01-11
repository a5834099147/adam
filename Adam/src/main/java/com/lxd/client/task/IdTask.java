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

package com.lxd.client.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.resource.ClientResource;
import com.lxd.client.resource.RequestPackage;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;


/**
 * 分配ID的Task
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class IdTask extends ClientTask {
    private static final Logger log = LogManager.getLogger(IdTask.class);

    @Override
    public void execute() {
        ///< 得到队列中的请求
        RequestPackage reqPack = ClientResource.getSingleton().takeRequest();
        Msg_.Builder msg = Msg_.newBuilder(reqPack.getMsg());
        ///< 设置新的ID
        msg.setJobId(getJobId());
        log.info("分配的ID为 " + msg.getJobId());
        ///< 设置任务参数
        Resource.getSingleton().getJobStatus().addJob(getJobId(), reqPack.getProperty());
        ///< 初始化任务
        Resource.getSingleton().getJobStatus().init(getJobId(), reqPack.getProperty().getTotal());
        ///< 发送新的请求
        Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(msg.build(), getChannel()));
    }

}
