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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.protobuf.msg.result.Result.Result_.Repleish;
import com.lxd.resource.Resource;
import com.lxd.server.resource.property.ConsoleAddFile;
import com.lxd.server.task.job.JobTask;
import com.lxd.utils.Define;
import com.lxd.utils.Grnerate;


/**
 * 控制台增加文件任务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class AddFileTask extends JobTask {
    
    private static final Logger log = LogManager.getLogger(AddFileTask.class);
    ///< 总块数
    private int total_lump;
    ///< 当前块数
    private int current_lump;
    ///< 块信息
    private byte[] datas;
    
    public void setTotal_lump(int total_lump) {
        this.total_lump = total_lump;
    }
    
    public void setCurrent_lump(int current_lump) {
        this.current_lump = current_lump;
    }
    
    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    @Override
    public Result_ jobExecute() {
        Result_.Builder result = Result_.newBuilder();
        
        ///< 判断任务需要做
        if (Resource.getSingleton().getJobStatus().checkToDo(getJobId(), total_lump, current_lump)) {
            ///< 设置文件正在进行
            Resource.getSingleton().getJobStatus().setDoing(getJobId(), current_lump);
            
            ///< 得到任务附加属性
            ConsoleAddFile property = (ConsoleAddFile) Resource.getSingleton().getJobStatus().getProperty(getJobId());
            String fileString = Grnerate.getPath(property.getMd5(), property.getLength());         
            
            ///< 准备写文件
            try (RandomAccessFile raf = new RandomAccessFile(fileString, "rw")) {
                raf.seek(current_lump * Define.BLOCK_SIZE);
                raf.write(datas);              
                log.info("文件块" + current_lump + "写完, 所属文件" + fileString);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            Resource.getSingleton().getJobStatus().setDone(getJobId(), current_lump);
            if (Resource.getSingleton().getJobStatus().checkFinished(getJobId())) {
                ///< 完成
                result.setSuccess(true);
            } else {
                result.setSuccess(true);
                Repleish.Builder repleish = Repleish.newBuilder();
                repleish.setBlock(current_lump);
                result.setRepleish(repleish);
            }
        }
        
        return result.build();        
    }

}
