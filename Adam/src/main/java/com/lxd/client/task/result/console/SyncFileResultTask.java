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

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.monitor.MonitorDir;
import com.lxd.client.monitor.MonitorMsg;
import com.lxd.client.monitor.MonitorMsg.Type;
import com.lxd.client.resource.ClientResource;
import com.lxd.client.resource.RequestPackage;
import com.lxd.client.resource.property.ServerDownloadFile;
import com.lxd.client.service.FileServer;
import com.lxd.client.service.impl.FileServerImpl;
import com.lxd.client.task.ClientTask;
import com.lxd.client.task.result.console.utils.FileChanged;
import com.lxd.client.task.result.console.utils.FileState;
import com.lxd.client.view.control.UiSingleton;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.console.Console.Console_;
import com.lxd.protobuf.msg.request.console.DownloadFile.DownloadFile_;
import com.lxd.protobuf.msg.result.console.SyncFile.Info;
import com.lxd.utils.Define;
import com.lxd.utils.Grnerate;

/**
 * 同步文件结果任务
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月12日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class SyncFileResultTask extends ClientTask {
    
    
    /*********************************************************************************************
     *  本地文件         |          数据库            |        服务器
     *      有      <-- 一致 --> 有                                         没有                  删除本地文件
     *      有      <-- 一致 --> 有        <-- 一致 -->              有                     不做
     *      有      <-- 一致 --> 有        <-- 不一致 -->          有                      下载更新
     *      
     *      有      <-- 不一致 --> 有                                    没有                  更名上传[冲突]
     *      有      <-- 不一致 --> 有       <-- 一致 -->           有                    更新上传
     *      有      <-- 不一致 --> 有     <-- 不一致 -->         有                   重名上传[冲突], 下载更新
     *      
     *      有                          没有                                    没有                       上传
     *      有                          没有                                     有                     重名上传[冲突], 下载更新
     *      
     *     没有                           有                                       没有                     删除数据库记录
     *     没有                           没有                                     有                   下载文件
     *     没有                           有        <-- 一致 -->            有                     删除本地文件
     *    没有                           有        <-- 不一致 -->         有                   下载文件
     */

    // /< 结果
    private boolean    success;
    // /< 错误消息
    private String     error_msg;
    // /< 文件版本号
    private List<Info> infos;
    
    private static final Logger log = LogManager.getLogger(SyncFileResultTask.class);
    
    ///< 文件服务
    FileServer fileServer = new FileServerImpl();

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;        
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    @Override
    public void execute() {
        FileChanged fileChanged = new FileChanged();
        if(success) {
            List<FileState> fileStates = fileChanged.getFileInfo(Define.CLIENT, infos);
            for (FileState state : fileStates) {
                switch(state.getState()) {
                    ///< 上传文件
                    case UPLOAD:
                        uploadFile(state.getPath());
                        break;
                        ///< 更新文件
                    case UPDATE:
                        updateFile(state.getPath());                        
                        break;
                        ///< 重命名上传文件
                    case RENAME:
                        renameFile(state.getPath());
                        break;
                        ///< 删除文件
                    case DELETE:
                        deleteFile(state.getPath());
                        break;
                    case DOWNLOAD:
                        downloadFile(state.getPath());
                        break;
                    case DELETE_TABLE:
                        deleteTable(state.getPath());
                        break;
                    case DELETE_ENTITY:
                        deleteEntity(state.getPath());
                        break;
                }
            }            
        } else {
            log.error("请求同步消息出错, 错误信息为:" + error_msg);
        }
        
        ///< 开启监听服务
        new MonitorDir(Define.CLIENT).start();
    }
    
    private void deleteEntity(String path) {
        fileServer.deleteHDDFile(path);
        com.lxd.client.entity.File deleteFile = new com.lxd.client.entity.File();
        deleteFile.setUser_name(UiSingleton.getSingleton().getUser());
        deleteFile.setPath(Grnerate.getClientRelativePath(path));
        fileServer.deleteFile(deleteFile);                        
    }

    private void deleteTable(String path) {
        com.lxd.client.entity.File deleteFile = new com.lxd.client.entity.File();
        deleteFile.setUser_name(UiSingleton.getSingleton().getUser());
        deleteFile.setPath(Grnerate.getClientRelativePath(path));
        fileServer.deleteFile(deleteFile);                
    }

    /*
     *  下载文件
     */
    private void downloadFile(String path) {

        Msg_.Builder msg_ = Msg_.newBuilder();
        Request_.Builder request = Request_.newBuilder();
        // /< 控制台请求信息构建器
        Console_.Builder console = Console_.newBuilder();
        // /< 添加用户信息
        console.setUserName(UiSingleton.getSingleton().getUser());
        // /< 控制台请求信息文件下载构造器
        DownloadFile_.Builder download = DownloadFile_.newBuilder();

        download.setPath(Grnerate.getClientRelativePath(path));
        // /< 将新增文件信息设置到控制台请求信息构建器中
        console.setDownloadFile(download);
        request.setConsole(console);
        msg_.setRequest(request);
        msg_.setJobId(-1);

        ClientResource.getSingleton().submitRequest(new RequestPackage(
                                                                       msg_.build(),
                                                                       new ServerDownloadFile(
                                                                                              path)));        
    }

    /*
     *  删除本地文件
     */
    private void deleteFile(String path) {
        ClientResource.getSingleton().submitMonitorMsg(new MonitorMsg(new File(path), Type.DELETE));        
    }

    /*
     *  重命名文件
     */
    private void renameFile(String path) {
        File file = fileServer.renameHddFile(path);
        ClientResource.getSingleton().submitMonitorMsg(new MonitorMsg(file, Type.ADD));        
    }

    /*
     *  更新文件
     */
    private void updateFile(String path) {
        ClientResource.getSingleton().submitMonitorMsg(new MonitorMsg(new File(path), Type.UPDATE));        
    }

    /*
     *  上传文件
     */
    private void uploadFile(String path) {
        ClientResource.getSingleton().submitMonitorMsg(new MonitorMsg(new File(path), Type.ADD));        
    }
}
