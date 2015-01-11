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

package com.lxd.server.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.lxd.server.dao.WebFileDao;
import com.lxd.server.dao.util.AliUtil;


/**
 * 阿里云网络文件操作
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月7日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ALiFileDao implements WebFileDao {

    @Override
    public void addFile(File file) {
        ///< 初始化 OSSClient
        OSSClient client = AliUtil.getClient();
        InputStream content = null;;
        try {
            content = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ///< 上传Object 的 Metadata
        ObjectMetadata meta = new ObjectMetadata();
        ///< 设置上传文件快大小
        meta.setContentLength(file.length());
        
        ///< 上传 Object
        client.putObject(AliUtil.BUCKK_STRING, file.getName(), content, meta);
    }

    @Override
    public String downloadFile(String path) {
        // TODO Auto-generated method stub
        return null;
    }

}
