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

package com.lxd.client.task.result.console;

import com.lxd.client.handle.HandleResource;
import com.lxd.client.resource.property.ServerUpdateFile;
import com.lxd.client.task.ClientTask;
import com.lxd.resource.Resource;


/**
 * 更新文件结果
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月9日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class UpdateFileResultTask extends ClientTask {
    // /< 结果
    private boolean success;
    // /< 错误消息
    private String  error_msg;
    // /< 文件版本号
    private Integer edition;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Override
    public void execute() {
        if (success) {
            ///< 如果正确完成, 将由远端传来的版本号传给任务属性
            ServerUpdateFile updateFile = (ServerUpdateFile) Resource.getSingleton().getJobStatus().getProperty(getJobId());
            updateFile.setEdition(edition);
            
            HandleResource.getSingleton().getUpdateFile().updateFileSuccess(getJobId());
        } else {
            HandleResource.getSingleton().getUpdateFile().updateFileError(getJobId(), error_msg);
        }
    }
}
