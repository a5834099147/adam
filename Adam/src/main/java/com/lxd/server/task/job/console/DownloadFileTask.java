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

import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.console.Console.Console_;
import com.lxd.protobuf.msg.result.console.DownloadFile.DownloadFile_;
import com.lxd.resource.Resource;
import com.lxd.server.resource.property.ConsoleDownloadFile;
import com.lxd.server.task.job.JobTask;


/**
 * 下载文件处理
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月11日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class DownloadFileTask extends JobTask {
    
    private String md5;
    
    public String getMd5() {
        return md5;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public Result_ jobExecute() {
        ConsoleDownloadFile download = (ConsoleDownloadFile) Resource.getSingleton().getJobStatus().getProperty(getJobId());
        Result_.Builder result = Result_.newBuilder();
        Console_.Builder console = Console_.newBuilder();
        DownloadFile_.Builder downloadFile = DownloadFile_.newBuilder();
        if (!download.getMd5().equals(md5)) {
            downloadFile.setSuccess(false);
            downloadFile.setErrorMsg("下载后的文件校验和与服务器上不同");
        } else {
            downloadFile.setSuccess(true);
            downloadFile.setEdition(download.getEdition());
        }
        console.setDownloadFile(downloadFile);
        result.setConsole(console);
        return result.build();
    }

}
