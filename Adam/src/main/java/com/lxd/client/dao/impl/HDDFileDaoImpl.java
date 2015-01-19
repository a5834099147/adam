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

package com.lxd.client.dao.impl;

import java.io.File;

import com.lxd.client.dao.HDDFileDao;
import com.lxd.client.view.control.UiSingleton;


/**
 * 硬盘数据访问接口
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月19日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class HDDFileDaoImpl implements HDDFileDao {

    @Override
    public File rename(String path) {
        ///< 重命名
        File file = new File(path);
        file.renameTo(new File(path + "_" + UiSingleton.getSingleton().getUser()));
        return file;
    }

    @Override
    public void delete(String path) {
        ///< 非空删除
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }        
    }
    
}
