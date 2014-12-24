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
 * 控制台更新文件附加属性
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月22日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ConsoleUpdataFile {
    ///< 修改后文件MD5
    private String md5;
    ///< 修改后文件长度
    private Long length;
    ///< 修改文件路径
    private String patch;
    
    public ConsoleUpdataFile(String md5, Long length, String patch){
        super();
        this.md5 = md5;
        this.length = length;
        this.patch = patch;
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
    
    public String getPatch() {
        return patch;
    }
    
    public void setPatch(String patch) {
        this.patch = patch;
    }
}
