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

import java.io.BufferedInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.algorithm.MD5;
import com.lxd.client.resource.property.ServerDownloadFile;
import com.lxd.client.task.ClientTask;
import com.lxd.client.task.job.server.util.FileUtil;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.job.Job.Job_;
import com.lxd.protobuf.msg.job.console.Console.Console_;
import com.lxd.protobuf.msg.job.console.DownloadFile.DownloadFile_;
import com.lxd.resource.DataPackage;
import com.lxd.resource.Resource;

/**
 * 下载文件任务
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月11日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class DownloadFileTask extends ClientTask {

    // /< 下载链接地址
    private String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    private static Logger log = LogManager.getLogger(DownloadFileTask.class);

    @Override
    public void execute() {
        ServerDownloadFile downloadFile = (ServerDownloadFile) Resource.getSingleton().getJobStatus().getProperty(getJobId());
        try {
            // /< 将链接地址转换为HTTp链接
            HttpURLConnection httpURL = (HttpURLConnection) new URL(downloadUrl).openConnection();
            // /< 打开链接
            httpURL.connect();
            // /< 写入文件信息
            FileUtil.write(new BufferedInputStream(httpURL.getInputStream()), downloadFile.getPath());
            // /< 关闭连接
            httpURL.disconnect();

            Msg_.Builder msg = Msg_.newBuilder();
            msg.setJobId(getJobId());
            // /< 设置任务消息
            Job_.Builder job = Job_.newBuilder();
            // /< 设置来自控制台的任务消息
            Console_.Builder console = Console_.newBuilder();
            // /< 设置来自控制台的下载文件任务消息
            DownloadFile_.Builder downloadFileBuilder = DownloadFile_.newBuilder();
            // /< 设置下载后文件的MD5
            downloadFileBuilder.setMd5(MD5.getFileMD5String(new File(downloadFile.getPath())));

            console.setDownloadFile(downloadFileBuilder);
            job.setConsole(console);
            msg.setJob(job);

            // /< 将消息放入到发送队列中
            Resource.getSingleton().getMsgQueue().submitMsgOutQueue(new DataPackage(msg.build(), getChannel()));

        } catch (Exception e) {
            // /< 下载文件错误
            log.error("下载文件" + downloadFile.getPath() + "时出错");
            e.printStackTrace();
        }
    }

}
