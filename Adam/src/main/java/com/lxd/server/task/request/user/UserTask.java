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

package com.lxd.server.task.request.user;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.user.User.User_;
import com.lxd.server.task.ServerTask;


/**
 * 用户任务具体流程
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public abstract class UserTask extends ServerTask {

    @Override
    public Msg_ taskExecute() {
        ///<  结果构建器
        Result_.Builder result_ = Result_.newBuilder();
        ///< 由具体流程得到User消息
        User_ user_ = userExecute();
        ///< 将user消息设置给结果
        result_.setUser(user_);
        ///< 消息构建器
        Msg_.Builder msg = Msg_.newBuilder();
        ///< 设置结果
        msg.setResult(result_);
        ///< 设置任务编号
        msg.setJobId(getJobId());
        return msg.build();
    }
    
    ///< UserTask 具体实现流程
    public abstract User_ userExecute();
}
