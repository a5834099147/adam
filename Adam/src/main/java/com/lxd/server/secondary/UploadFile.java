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

package com.lxd.server.secondary;

import java.io.File;
import com.lxd.server.dao.WebFileDao;
import com.lxd.server.dao.impl.QiNiuFileDao;
import com.lxd.server.resource.ServerResource;


/**
 * 上传文件处理线程
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月7日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class UploadFile extends Thread {
    private WebFileDao fileDao = new QiNiuFileDao();
    @Override
    public void run() {
        while (true) {
            ///< 获取上传文件
            File file = ServerResource.getSingleton().takeFile();
            ///< 新增文件
            fileDao.AddFile(file);
        }
    }
}
