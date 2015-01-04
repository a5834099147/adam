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

package com.lxd.client.task.job.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.protobuf.ByteString;
import com.lxd.client.resource.property.ServerUpdateFile;
import com.lxd.client.task.ClientTask;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.console.Console.Console_;
import com.lxd.protobuf.msg.job.console.UpdateFile.Patch;
import com.lxd.protobuf.msg.job.console.UpdateFile.UpdateFile_;
import com.lxd.protobuf.msg.job.server.UpdateFile.Information;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;
import com.lxd.sync.Chunk;
import com.lxd.sync.PatchPart;
import com.lxd.sync.PatchPartChunk;
import com.lxd.sync.PatchPartData;
import com.lxd.sync.RsyncUtil;
import com.lxd.utils.Define;
import com.lxd.utils.Utils;

/**
 * 来自服务器的修改文件任务
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class UpdateFileTask extends ClientTask {

    private List<Information> infos = null;

    public void setInfos(List<Information> infos) {
        this.infos = infos;
    }

    private Map<Integer, List<Chunk>> getInfoMap() {
        Map<Integer, List<Chunk>> result = new HashMap<>();
        for (Information info : infos) {
            int adler32 = info.getAdler32();
            String md5 = info.getMd5();
            int length = info.getLength();
            int id = info.getInfoId();

            Chunk chunk = new Chunk();
            chunk.setAdler32(adler32);
            chunk.setIndex(id);
            chunk.setLength(length);
            chunk.setMd5(md5);

            if (result.containsKey(adler32)) {
                result.get(adler32).add(chunk);
            } else {
                List<Chunk> chunks = new LinkedList<Chunk>();
                chunks.add(chunk);
                result.put(new Integer(adler32), chunks);
            }
        }

        return result;
    }

    @Override
    public void execute() {
        Map<Integer, List<Chunk>> infoMap = getInfoMap();
        // /< 得到附加信息 文件路径
        ServerUpdateFile update = (ServerUpdateFile) Resource.getSingleton().getJobStatus().getProperty(getJobId());

        // /< 得到文件的大小
        File file = new File(update.getPath());
        Long length = file.length();
        byte[] dates = null;
        int totle_block = (int) (length / Define.BLOCK_SIZE);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            for (int i = 0; i <= totle_block; ++i) {
                if (i == totle_block) {
                    dates = new byte[(int) (length - totle_block * Define.BLOCK_SIZE)];
                } else {
                    dates = new byte[(int) (Define.BLOCK_SIZE * 1)];
                }

                fis.read(dates);

                Msg_.Builder msg = Msg_.newBuilder();
                msg.setJobId(getJobId());
                // /< 设置任务消息
                Job_.Builder job = Job_.newBuilder();
                // /< 设置来自控制台的任务消息
                Console_.Builder console = Console_.newBuilder();
                // /< 设置来自控制台的增加文件任务消息
                UpdateFile_.Builder updateFIle = UpdateFile_.newBuilder();
                // /< 当前是第几块数据
                updateFIle.setCurrentLump(i);
                // /< 该任务数据总块数
                updateFIle.setTotalLump(totle_block + 1);
                // /< 设置该块数据
                List<PatchPart> parts = RsyncUtil.createPatch(dates, infoMap).getParts();
                for (PatchPart part : parts) {
                    Patch.Builder patch = Patch.newBuilder();
                    // /< 设置补丁信息到传输格式中
                    if (part instanceof PatchPartChunk) {
                        // /< 传输编号
                        PatchPartChunk chunk = (PatchPartChunk) part;
                        patch.setInfoId(chunk.getIndex());
                    } else if (part instanceof PatchPartData) {
                        // /< 传输数据
                        PatchPartData data = (PatchPartData) part;
                        patch.setDatas(ByteString.copyFrom(data.getDatas()));
                    }
                    updateFIle.addPatch(patch);                   
                }
                console.setUpdateFile(updateFIle);
                job.setConsole(console);
                msg.setJob(job);

                // /< 将消息放入到发送队列中
                Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(msg.build(), getChannel()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.closeConnection(fis);
        }      
    }

}
