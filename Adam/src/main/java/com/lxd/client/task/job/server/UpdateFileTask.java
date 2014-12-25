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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.protobuf.ByteString;
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


/**
 * 来自服务器的修改文件任务
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
    
    private Map<Long, List<Chunk>> getInfoMap() {
        Map<Long, List<Chunk>> result = new HashMap<>();
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
                result.put(new Long(adler32), chunks);
            }
        }
        
        return result;
    }

    @Override
    public void execute() {
        Map<Long, List<Chunk>> infoMap = getInfoMap();
        ///< 得到附加信息 文件路径
        String path = (String) Resource.getSingleton().getJobStatus().getProperty(getJobId());
        
        try {            
            Msg_.Builder msg = Msg_.newBuilder();
            msg.setJobId(getJobId());
            Job_.Builder job = Job_.newBuilder();
           Console_.Builder console = Console_.newBuilder();
           UpdateFile_.Builder update = UpdateFile_.newBuilder();
           update.setTotalLump(1);
           update.setCurrentLump(0);
            ///< 得到补丁信息
           List<PatchPart> parts = RsyncUtil.createPatch(new File(path), infoMap).getParts();
           for (PatchPart part : parts) {
               Patch.Builder patch = Patch.newBuilder();
               ///< 设置补丁信息到传输格式中
               if (part instanceof PatchPartChunk) {
                   ///< 传输编号
                   PatchPartChunk chunk = (PatchPartChunk) part;
                   patch.setInfoId(chunk.getIndex());
               } else if (part instanceof PatchPartData) {
                   ///< 传输数据
                   PatchPartData data = (PatchPartData) part;
                   patch.setDatas(ByteString.copyFrom(data.getDatas()));
               }
               update.addPatch(patch);
           }
            
            console.setUpdateFile(update);
            job.setConsole(console);
            msg.setJob(job);
            ///< 放入到输出消息队列中
            Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(msg.build(), getChannel()));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
