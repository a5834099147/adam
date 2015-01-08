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

package com.lxd.client.monitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.algorithm.MD5;
import com.lxd.client.monitor.MonitorMsg.Type;
import com.lxd.client.resource.ClientResource;
import com.lxd.client.resource.RequestPackage;
import com.lxd.client.resource.property.ServerAddFile;
import com.lxd.client.resource.property.ServerDeleteFile;
import com.lxd.client.resource.property.ServerUpdateFile;
import com.lxd.client.view.control.UiSingleton;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.console.AddFile.AddFile_;
import com.lxd.protobuf.msg.request.console.Console.Console_;
import com.lxd.protobuf.msg.request.console.DeleteFile.DeleteFile_;
import com.lxd.protobuf.msg.request.console.UpdateFile.UpdateFile_;
import com.lxd.utils.Define;


/**
 * 客户端目录事件监听
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月4日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class MonitorDir extends Thread{
    private static final Logger log = LogManager.getLogger(MonitorDir.class);
    
    ///< 监听文件目录
    private String filePath = null;    
    
    public MonitorDir(String filePath){
        this.filePath = filePath;
    }   
    
    public void run() {
        fileWatch();
    }
    
    private void fileWatch() {
        //文件变更器  
        FileAlterationMonitor  monitor = new FileAlterationMonitor(60000); //一分钟扫描一次
        //目录观察者  
        FileAlterationObserver observer = new FileAlterationObserver(new File(filePath));
        
        ///< 监听器接口
        observer.addListener(new AdamFileListener());
        ///< 设置处理接口
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ///< 预处理信息请求
        MonitorMsg msg = null;
        while ((msg = ClientResource.getSingleton().takeMonitorMsg()) != null) {
            if (msg.getStart() == 0 || msg.getStart() <= System.currentTimeMillis()) {
                ///< 处理判定
                if (msg.getStart() == 0 && msg.getType() == Type.UPDATE) {
                    ///< 如果是修改, 由于删除文件会导致发生修改信息, 所以这样处理
                    msg.setStart(System.currentTimeMillis() + Define.UPDATETIME);
                    ClientResource.getSingleton().submitMonitorMsg(msg);
                } else {
                    ///< 判断文件是否可写
                    if (msg.getType() == Type.DELETE || fileCanRead(msg.getFile())) {
                        ///< 删除多余修改文件
                        msg = ClientResource.getSingleton().reviseMsg(msg);
                        msgPacking(msg);                        
                    } else {

                        log.info("文件" + msg.getFile() + "无法访问, 被重置");
                        msg.setStart(System.currentTimeMillis() + Define.ERRORTIME);
                        ClientResource.getSingleton().submitMonitorMsg(msg);
                    }
                }
            }
            else {
                ///< 将消息重新放入到队列中
                ClientResource.getSingleton().submitMonitorMsg(msg);
                try {
                    ///< 不在运行时间, 则等待, 并将信息返回到队列中
                    log.info("文件" + msg.getFile() + "未到开始点, 被重置");
                    sleep(Define.SLEEPTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void msgPacking(MonitorMsg msg) {
        switch (msg.getType()) {
            case DELETE : 
                deleteFilePacking(msg.getFile());
            break;
            case ADD : 
                addFilePacking(msg.getFile());
            break;
            case UPDATE :   
                updateFilePacking(msg.getFile());
            break;            
        }        
    }
    
    private void deleteFilePacking(File file) {
        ///< 控制台请求信息构建器
        Console_.Builder console = Console_.newBuilder();
        ///< 添加用户信息
        console.setUserName(UiSingleton.getSingleton().getUser());
        ///< 控制台请求信息删除文件构造器
        DeleteFile_.Builder deleteFile = DeleteFile_.newBuilder();
        ///< 在删除文件信息中加入被删除文件的相对路径
        deleteFile.setPath(file.getAbsolutePath().substring(Define.CLIENT.length()));
        ///< 将删除文件信息设置到控制台请求信息构建器中
        console.setDeleteFile(deleteFile);
        ///< 交给函数最终打包
        Msg_ msg_ = packing(console.build());
        
        ClientResource.getSingleton().submitRequest(new RequestPackage(msg_, new ServerDeleteFile(file.getAbsolutePath())));
    }
    
    /*
     *  打包新增文件信息
     */
    private void addFilePacking(File file) {  
        ///< 控制台请求信息构建器
        Console_.Builder console = Console_.newBuilder();
      ///< 添加用户信息
        console.setUserName(UiSingleton.getSingleton().getUser());
        ///< 控制台请求信息增加文件构造器
        AddFile_.Builder addFile = AddFile_.newBuilder();
        
        ///< 文件md5
        String md5 = null;
        try {
            ///< 计算文件的MD5值
            md5 = MD5.getFileMD5String(file);
            addFile.setMd5(md5);
        } catch (Exception e) {
            ///< 写入值出错
            log.error(e.getMessage());
            return;
        }
        ///< 将新增文件长度加入到构建器中
        addFile.setLength(file.length());
        ///< 将新增文件的最后修改时间将入到构建器中
        addFile.setLast(file.lastModified());
        ///< 将新增文件相对路径加入到构建器中
        addFile.setPath(file.getAbsolutePath().substring(Define.CLIENT.length()));
        ///< 将新增文件信息设置到控制台请求信息构建器中
        console.setAddFile(addFile);     
        ///< 交给函数最终打包
        Msg_ msg_ = packing(console.build());
        
        ClientResource.getSingleton().submitRequest(new RequestPackage(msg_, new ServerAddFile(file.getAbsolutePath(), md5, file.length(), file.lastModified())));
    }
    
    /*
     *  打包更新文件信息
     */
    private void updateFilePacking(File file) {
        ///< 控制台请求信息构建器
        Console_.Builder console = Console_.newBuilder();
      ///< 添加用户信息
        console.setUserName(UiSingleton.getSingleton().getUser());
        ///< 控制台请求信息修改文件信息构建器
        UpdateFile_.Builder updateFile = UpdateFile_.newBuilder();
        ///< 将修改文件的新长度加入到构建器中
        updateFile.setLength(file.length());
        ///< 将修改文件的最后修改时间加入到构建器中
        updateFile.setLast(file.lastModified());
        ///< 文件 md5
        String md5 = null;
        try {
            ///< 将修改文件的MD5值加入到构建器中
            md5 = MD5.getFileMD5String(file);
            updateFile.setMd5(md5);
        } catch (Exception e) {
            ///< 写入值, 计算时出现IO错误
            log.error(e.getMessage());
            return;
        }
        ///< 将修改文件的相对路径加入到修改文件信息中
        updateFile.setPath(file.getAbsolutePath().substring(Define.CLIENT.length()));
        ///< 将修改文件信息加入到控制台请求信息构建器中
        console.setUpdateFile(updateFile);
        ///< 交给函数最终打包并返回
        Msg_ msg_ = packing(console.build());
        
        ClientResource.getSingleton().submitRequest(new RequestPackage(msg_, new ServerUpdateFile(file.getAbsolutePath(), md5, file.length(), file.lastModified())));
    }
    
    /*
     * 打包最终消息
     */
    private Msg_ packing(Console_ console) {
        Msg_.Builder msg = Msg_.newBuilder();
        Request_.Builder request = Request_.newBuilder();        
        request.setConsole(console);
        msg.setRequest(request);
        msg.setJobId(-1L);
        return msg.build();
    }
    
    /*
     * 判断文件是否可读
     */
    private boolean fileCanRead(File file) {
        if (file.renameTo(file)) {
            return true;
        } else {
            return false;
        }
    }
}
