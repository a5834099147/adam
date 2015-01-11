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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.console.UpdateFile.Patch;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.console.Console.Console_;
import com.lxd.protobuf.msg.result.console.UpdateFile.UpdateFile_;
import com.lxd.protobuf.msg.result.console.UpdateFilePart.UpdateFilePart_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;
import com.lxd.server.entity.File;
import com.lxd.server.resource.ServerResource;
import com.lxd.server.resource.property.ConsoleUpdataFile;
import com.lxd.server.service.FileService;
import com.lxd.server.service.impl.FileServiceImpl;
import com.lxd.server.task.job.JobTask;
import com.lxd.sync.RsyncUtil;
import com.lxd.utils.Define;
import com.lxd.utils.Grnerate;

/**
 * 控制台更新文件
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class UpdateFileTask extends JobTask {

    // /< 总块数
    private int                 total_lump;
    // /< 当前块数
    private int                 current_lump;
    // /< 块信息
    private List<Patch>         patch;
    // /< 文件服务实体
    private FileService          server = new FileServiceImpl();

    private static final Logger log    = LogManager.getLogger(UpdateFileTask.class);

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
        Result_.Builder resultPart = Result_.newBuilder();

        com.lxd.sync.Patch infos = new com.lxd.sync.Patch();
        // /< 解析传递过来的补丁信息
        for (Patch info : patch) {
            if (info.hasDatas()) {
                infos.add(info.getDatas().toByteArray());
            } else if (info.hasInfoId()) {
                infos.add(info.getInfoId());
            }
        }

        if (Resource.getSingleton().getJobStatus().checkToDo(getJobId(), total_lump, current_lump)) {
            // /< 设置文件正在进行
            Resource.getSingleton().getJobStatus().setDoing(getJobId(), current_lump);

            // /< 得到附加属性
            ConsoleUpdataFile pro = (ConsoleUpdataFile) Resource.getSingleton().getJobStatus().getProperty(getJobId());
            // /< 得到旧文件的详细
            File oldFile = server.searchFile(pro.getUser_name(), pro.getPatch());

            try {
                byte[] datas = RsyncUtil.applyPatch(infos, Grnerate.getPath(oldFile.getMd5(), oldFile.getLength()));
                server.editFile(Grnerate.getPath(pro.getMd5(), pro.getLength()), current_lump * Define.BLOCK_SIZE,
                                datas);

                Resource.getSingleton().getJobStatus().setDone(getJobId(), current_lump);

                Integer edition = server.updateFile(oldFile, pro.getMd5(), pro.getLength(), pro.getLast());
                ;

                if (Resource.getSingleton().getJobStatus().checkFinished(getJobId())) {
                    Msg_.Builder msg = Msg_.newBuilder();
                    Result_.Builder result = Result_.newBuilder();
                    Console_.Builder console = Console_.newBuilder();
                    UpdateFile_.Builder updateFile = UpdateFile_.newBuilder();
                    updateFile.setSuccess(true);
                    updateFile.setEdition(edition);
                    log.info("任务编号" + getJobId() + " 添加文件任务完成");
                    console.setUpdateFile(updateFile);
                    result.setConsole(console);
                    msg.setResult(result);
                    msg.setJobId(getJobId());
                    // /< 发送完成结果
                    Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(msg.build(), getChannel()));

                    log.info("任务编号" + getJobId() + " 修改文件任务完成");

                    // /< 向远端投递新修改文件
                    ServerResource.getSingleton().submitFile(new java.io.File(Grnerate.getPath(pro.getMd5(),
                                                                                               pro.getLength())));
                }

                Console_.Builder console = Console_.newBuilder();
                UpdateFilePart_.Builder updateFilePart = UpdateFilePart_.newBuilder();
                updateFilePart.setSuccess(true);
                updateFilePart.setNumber(current_lump);
                console.setUpdateFilePart(updateFilePart);
                resultPart.setConsole(console);
                log.info("任务编号" + getJobId() + " 模块" + current_lump + "修改文件任务完成");
            } catch (Exception e) {
                Resource.getSingleton().getJobStatus().setError(getJobId(), current_lump);
                Console_.Builder console = Console_.newBuilder();
                UpdateFilePart_.Builder updateFilePart = UpdateFilePart_.newBuilder();
                updateFilePart.setSuccess(false);
                updateFilePart.setErrorMsg(e.getMessage());
                updateFilePart.setNumber(current_lump);
                console.setUpdateFilePart(updateFilePart);
                resultPart.setConsole(console);
                log.error("任务编号" + getJobId() + " 模块" + current_lump + "修改文件任务完成");
                e.printStackTrace();
            }

        }
        return resultPart.build();
    }

}
