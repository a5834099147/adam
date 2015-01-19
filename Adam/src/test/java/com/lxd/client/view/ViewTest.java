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

package com.lxd.client.view;

import org.junit.Test;

import com.lxd.client.AdamClient;
import com.lxd.client.resource.ClientResource;
import com.lxd.client.resource.RequestPackage;
import com.lxd.client.resource.property.ServerDownloadFile;
import com.lxd.client.view.control.UiSingleton;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.console.Console.Console_;
import com.lxd.protobuf.msg.request.console.DownloadFile.DownloadFile_;
import com.lxd.utils.Grnerate;

/**
 * 描述功能
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月4日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class ViewTest {

    @Test
    public void test() {
        // EventQueue.invokeLater(new Runnable() {
        //
        // // @Override
        // // public void run() {
        // // UiSingleton.getSingleton().BamInit();
        // // }
        // });

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Msg_.Builder msg_ = Msg_.newBuilder();
                    Request_.Builder request = Request_.newBuilder();
                    // /< 控制台请求信息构建器
                    Console_.Builder console = Console_.newBuilder();
                    // /< 添加用户信息
                    console.setUserName(UiSingleton.getSingleton().getUser());
                    // /< 控制台请求信息文件下载构造器
                    DownloadFile_.Builder download = DownloadFile_.newBuilder();

                    download.setPath("\\03 - Addiction.mp3");
                    // /< 将新增文件信息设置到控制台请求信息构建器中
                    console.setDownloadFile(download);
                    request.setConsole(console);
                    msg_.setRequest(request);
                    msg_.setJobId(-1);

                    ClientResource.getSingleton().submitRequest(new RequestPackage(
                                                                                   msg_.build(),
                                                                                   new ServerDownloadFile(
                                                                                                          Grnerate.getClientAbsPath("\\03 - Addiction.mp3"))));
                } catch (InterruptedException e) {
                }
            }
        }.start();

        new AdamClient().initServer();
    }

}
