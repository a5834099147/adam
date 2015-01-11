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

import org.apache.commons.codec.EncoderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.server.dao.WebFileDao;
import com.lxd.server.dao.util.QiNiuUtil;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;


/**
 * 七牛网络文件管理
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月7日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class QiNiuFileDao implements WebFileDao {
    private static final Logger log = LogManager.getLogger(QiNiuFileDao.class);

    @Override
    public void addFile(File file) {
        try {
            String uptoken = QiNiuUtil.getPutString();
            PutExtra extra = new PutExtra();
            IoApi.putFile(uptoken, file.getName(), file.getAbsoluteFile(), extra); 
            log.info("上传文件" + file.getAbsolutePath() + "到七牛服务器成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传文件" + file.getAbsolutePath() + "到七牛服务器失败, 原因是:" + e.getMessage());
        } 
    }

    @Override
    public String downloadFile(String path) throws EncoderException, AuthException {        
       return QiNiuUtil.getDownloadPath(path);
    }

}
