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
import java.io.IOException;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.algorithm.MD5;
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
 * 目录监听回调
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月4日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class AdamFileListener implements FileAlterationListener {
    ///< 日志
    private Logger log = LogManager.getLogger(AdamFileListener.class);
    
    @Override  
    public void onStop(FileAlterationObserver observer) {         
    }  
      
    @Override  
    public void onStart(FileAlterationObserver observer) {  
    }  
      
    @Override  
    public void onFileDelete(File file) {    
        ///< 删除文件消息        
        Msg_.Builder msg = Msg_.newBuilder();
        Request_.Builder request = Request_.newBuilder();
        Console_.Builder console = Console_.newBuilder();
        console.setUserName(UiSingleton.getSingleton().getUser());
        DeleteFile_.Builder deleteFile = DeleteFile_.newBuilder();
        deleteFile.setPath(file.getAbsolutePath().substring(Define.CLIENT.length()));
        console.setDeleteFile(deleteFile);
        request.setConsole(console);
        msg.setRequest(request);
        msg.setJobId(-1L);
        ClientResource.getSingleton().submitRequest(new RequestPackage(msg.build(), new ServerDeleteFile(file.getAbsolutePath())));
        
         log.info("文件删除, 详细地址" + file.getParent());
    }  
      
    @Override  
    public void onFileCreate(File file) { 
        Msg_.Builder msg = Msg_.newBuilder();
        Request_.Builder request = Request_.newBuilder();
        Console_.Builder console = Console_.newBuilder();
        console.setUserName(UiSingleton.getSingleton().getUser());
        AddFile_.Builder addFile = AddFile_.newBuilder();
        //TODO
        try {
            addFile.setMd5(MD5.getFileMD5String(file));
        } catch (IOException e) {
            log.error(e.getMessage());
            return;
        }
        addFile.setLength(file.length());
        addFile.setPath(file.getAbsolutePath().substring(Define.CLIENT.length()));
        console.setAddFile(addFile);
        request.setConsole(console);
        msg.setRequest(request);
        msg.setJobId(-1L);
        ClientResource.getSingleton().submitRequest(new RequestPackage(msg.build(), new ServerAddFile(file.getAbsolutePath())));
         log.info("新建文件, 详细地址" + file.getParent());
    }  
      
    @Override  
    public void onFileChange(File file) {  
        Msg_.Builder msg = Msg_.newBuilder();
        Request_.Builder request = Request_.newBuilder();
        Console_.Builder console = Console_.newBuilder();
        console.setUserName(UiSingleton.getSingleton().getUser());
        UpdateFile_.Builder updateFile = UpdateFile_.newBuilder();
        updateFile.setLength(file.length());
        //TODO
        try {
            updateFile.setMd5(MD5.getFileMD5String(file));
        } catch (IOException e) {
            log.error(e.getMessage());
            return;
        }
        updateFile.setPath(file.getAbsolutePath().substring(Define.CLIENT.length()));
        console.setUpdateFile(updateFile);
        request.setConsole(console);
        msg.setRequest(request);
        msg.setJobId(-1L);
        ClientResource.getSingleton().submitRequest(new RequestPackage(msg.build(), new ServerUpdateFile(file.getAbsolutePath())));
        log.info("文件更改, 信息地址:" + file.getParent());
    }  
      
    @Override  
    public void onDirectoryDelete(File file) {             

        log.info("文件目录删除, 详细地址" + file.getParent());
    }  
      
    @Override  
    public void onDirectoryCreate(File file) {  
        
        log.info("文件目录增加, 详细地址" + file.getParent());  
    }  
      
    @Override  
    public void onDirectoryChange(File file) {  

        log.info("文件目录修改, 详细地址" + file.getParent());
    }      
}
