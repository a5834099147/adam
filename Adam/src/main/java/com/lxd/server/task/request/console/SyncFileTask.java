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

package com.lxd.server.task.request.console;

import java.util.List;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.console.Console.Console_;
import com.lxd.protobuf.msg.result.console.SyncFile.Info;
import com.lxd.protobuf.msg.result.console.SyncFile.SyncFile_;
import com.lxd.server.entity.File;
import com.lxd.server.service.FileService;
import com.lxd.server.service.impl.FileServiceImpl;


/**
 * 同步文件信息请求任务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月12日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class SyncFileTask extends ConsoleTask {

    private static final FileService fileService = new FileServiceImpl();
    
    @Override
    public Msg_ taskExecute() {
        List<File> files = fileService.searchFile(getUser_name());
        Msg_.Builder msg = Msg_.newBuilder();
        Result_.Builder result = Result_.newBuilder();
        Console_.Builder console = Console_.newBuilder();
        SyncFile_.Builder syncFile = SyncFile_.newBuilder();        
        
        ///< 封装文件信息
        for (File file : files) {
            Info.Builder info = Info.newBuilder();
            info.setPath(file.getPath());
            info.setEdition(file.getEdition());
            syncFile.addInfos(info);
        }
        ///< 结果为正确
        syncFile.setSuccess(true);
        
        console.setSycnFile(syncFile);
        result.setConsole(console);
        msg.setResult(result);
        msg.setJobId(getJobId());
        return msg.build();
    }

}
