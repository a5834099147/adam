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

package com.lxd.server.task.job;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.server.task.Task;


/**
 * 描述功能
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public abstract class JobTask extends Task {

    @Override
    public Msg_ taskExecute() {        
        Result_ result_ = jobExecute();
        
        Msg_.Builder msg = Msg_.newBuilder();
        msg.setResult(result_);
        msg.setJobId(getJobId());
        return msg.build();
    }
    
    ///< JobTask 的具体流程
    public abstract Result_ jobExecute();
}
