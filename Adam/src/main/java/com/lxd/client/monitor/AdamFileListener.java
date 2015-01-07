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

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.monitor.MonitorMsg.Type;
import com.lxd.client.resource.ClientResource;

/**
 * 目录监听回调
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月4日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class AdamFileListener implements FileAlterationListener {

    // /< 日志
    private Logger log = LogManager.getLogger(AdamFileListener.class);

    @Override
    public void onStop(FileAlterationObserver observer) {
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    @Override
    public void onFileDelete(File file) {
        // /< 删除文件消息
        ClientResource.getSingleton().submitMonitorMsg(new MonitorMsg(file, Type.DELETE));
        log.info("文件删除, 详细地址" + file.getAbsolutePath());
    }

    @Override
    public void onFileCreate(File file) {
        // /< 新增文件信息
        ClientResource.getSingleton().submitMonitorMsg(new MonitorMsg(file, Type.ADD));
        log.info("新建文件, 详细地址" + file.getAbsolutePath());
    }

    @Override
    public void onFileChange(File file) {
        // /< 修改文件信息
        ClientResource.getSingleton().submitMonitorMsg(new MonitorMsg(file, Type.UPDATE));
        log.info("文件更改, 信息地址:" + file.getAbsolutePath());
    }

    @Override
    public void onDirectoryDelete(File file) {

        //log.info("文件目录删除, 详细地址" + file.getParent());
    }

    @Override
    public void onDirectoryCreate(File file) {

       // log.info("文件目录增加, 详细地址" + file.getParent());
    }

    @Override
    public void onDirectoryChange(File file) {

       // log.info("文件目录修改, 详细地址" + file.getParent());
    }
}
