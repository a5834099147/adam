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

package com.lxd.server.resource.property;


/**
 * 控制台增加文件附加属性
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月22日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ConsoleAddFile {
    ///< 文件MD5
    private String md5;
    ///< 文件长度
    private Long length;
    ///< 文件相对路径
    private String path;
    ///< 用户信息
    private String user_name;   
    
    public String getUser_name() {
        return user_name;
    }
    
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }  

    public ConsoleAddFile(String md5, Long length, String path, String user_name){
        super();
        this.md5 = md5;
        this.length = length;
        this.path = path;
        this.user_name = user_name;
    }

    public String getMd5() {
        return md5;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }   
    
    public Long getLength() {
        return length;
    }
    
    public void setLength(Long length) {
        this.length = length;
    }

    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }    
}
