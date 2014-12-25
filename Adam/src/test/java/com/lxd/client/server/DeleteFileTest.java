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
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.console.Console.Console_;
import com.lxd.protobuf.msg.request.console.DeleteFile.DeleteFile_;


/**
 * 测试删除文件
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月25日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class DeleteFileTest {

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
                DeleteFile_.Builder deleteFile = DeleteFile_.newBuilder();
                deleteFile.setPath("C:\\Users\\li__\\Desktop\\android-studio-bundle-135.1641136.exe");
                console.setDeleteFile(deleteFile);
                request.setConsole(console);
                msg.setRequest(request);
                msg.setJobId(-1L);
                ClientResource.getSingleton().submitRequest(new RequestPackage(msg.build(), new String("")));
            }
        }).start();
        
        new AdamClient().initServer();   
    }

}
