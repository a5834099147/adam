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

package com.lxd.server.service;

import java.util.List;

import com.lxd.server.entity.File;


/**
 * 文件业务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月25日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public interface FileService {
    ///< 查询文件是否存在
    boolean havaFile(String md5, Long length);
    
    ///< 用户通过路径查询文件
    File searchFile(String user_name, String path);
    
    ///< 用户添加文件信息
    void addFile(File file);
    
    ///< 用户修改文件信息, 返回结果为版本号
    Integer updateFile(File file, String md5, Long length, Long last);
    
    ///< 用户删除文件信息
    void deleteFile(File file);
    
    ///< 添加文件
    void addFile(String file_name, Long length);
    
    ///< 写入文件信息
    void editFile(String file_name, Long seek, byte[] datas);
    
    ///< 查询用户当前所有文件
    List<File> searchFile(String user_name);
}
