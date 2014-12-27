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

package com.lxd.client.server;

import org.junit.Test;

import com.lxd.client.AdamClient;
import com.lxd.client.resource.ClientResource;
import com.lxd.client.resource.RequestPackage;
import com.lxd.client.resource.property.ServerAddFile;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.console.AddFile.AddFile_;
import com.lxd.protobuf.msg.request.console.Console.Console_;


/**
 * 测试增加文件
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class AddFileTest {

    @Test
    public void test() {           
            
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Msg_.Builder msg = Msg_.newBuilder();
                Request_.Builder request = Request_.newBuilder();
                Console_.Builder console = Console_.newBuilder();
                console.setUserName("li_xd");
                AddFile_.Builder addFile = AddFile_.newBuilder();
                addFile.setMd5("6485A364D20F80A6021053C72DCF4F9F");
                addFile.setLength(147172134);
                addFile.setPath("C:\\Users\\li__\\Desktop\\纯音乐.-.[贝多芬第五交响曲之《命运》(最佳版本)].专辑.(ape).rar");
                console.setAddFile(addFile);
                request.setConsole(console);
                msg.setRequest(request);
                msg.setJobId(-1L);
                ClientResource.getSingleton().submitRequest(new RequestPackage(msg.build(), new ServerAddFile("C:\\Users\\li__\\Desktop\\纯音乐.-.[贝多芬第五交响曲之《命运》(最佳版本)].专辑.(ape).rar")));
            }
        }).start();
        
        new AdamClient().initServer();   
    }

}
