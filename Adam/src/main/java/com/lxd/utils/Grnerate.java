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

package com.lxd.utils;


/**
 * 生成类属工具
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月18日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class Grnerate {
    
    static private Long task_id = 1L;
    
    ///< 获取ID
    public static Long getTaskId() {
        synchronized (task_id) {
            return System.currentTimeMillis() + task_id++;
        }
    }
    
    public static String getRemotePath(String md5, Long length) {
        return md5 + "_" + length;
    }
    
    public static String getPath(String md5, Long length) {
        return Define.REMOTE + md5 + "_" + length;
    }
    
    public static String getClientRelativePath(String path) {
        return path.substring(Define.CLIENT.length());
    }
    
    public static String getClientAbsPath(String path) {
        return Define.CLIENT + path;
    }
}
