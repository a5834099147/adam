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

package com.lxd.client.resource.property;

/**
 * 下载文件附加属性
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月11日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class ServerDownloadFile extends Property {

    // /< 最后修改时间
    private Long    last;
    // /< 文件地址
    private String  path;
    // /< 文件的MD5编号
    private String  md5;
    // /< 文件的大小
    private Long    length;
    // /< 文件的版本号
    private Integer edition;

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

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Long getLast() {
        return last;
    }

    public void setLast(Long last) {
        this.last = last;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ServerDownloadFile(Long last, String path, String md5, Long length){
        this.last = last;
        this.path = path;
        this.md5 = md5;
        this.length = length;
    }
}
