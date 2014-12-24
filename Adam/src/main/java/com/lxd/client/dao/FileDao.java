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

package com.lxd.client.dao;

import java.sql.Connection;
import java.util.List;

import com.lxd.client.entity.File;


/**
 * 文件数据操作层
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public interface FileDao {
    ///< 增加文件信息
    void addFile(File file, Connection connection);
    
    ///< 删除文件信息
    void deleteFile(File file, Connection connection);
    
    ///< 查询用户文件信息
    List<File> queryByUser(String user_name, Connection connection);
    
    ///< 更新文件
    void updateFile(File file, Connection connection);
}
