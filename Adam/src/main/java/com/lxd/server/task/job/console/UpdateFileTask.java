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

package com.lxd.server.task.job.console;

import java.util.List;

import com.lxd.protobuf.msg.job.console.UpdateFile.Patch;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.server.task.job.JobTask;


/**
 * 控制台更新文件
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class UpdateFileTask extends JobTask {
    ///< 总块数
    private int total_lump;
    ///< 当前块数
    private int current_lump;
    ///< 块信息
    private List<Patch> patch;    
    
    public void setTotal_lump(int total_lump) {
        this.total_lump = total_lump;
    }
    
    public void setCurrent_lump(int current_lump) {
        this.current_lump = current_lump;
    }
    
    public void setPatch(List<Patch> patch) {
        this.patch = patch;
    }

    @Override
    public Result_ jobExecute() {
        // TODO Auto-generated method stub
        return null;
    }

}
