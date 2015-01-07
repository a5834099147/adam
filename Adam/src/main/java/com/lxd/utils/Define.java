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
 * 描述功能
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class Define {
    ///< 网络数据块大小
    public static final Long BLOCK_SIZE = 1024 * 1024 * 4L;
    ///< 远端生成文件目录
    public static final String REMOTE = "D:\\remote\\";
    ///< 校验数据块大小
    public static final Integer CHUNK_SIZE = 512;
    ///< 客户端文件目录
    public static final String CLIENT="D:\\client";
    ///< 修改文件等待时间
    public static final long UPDATETIME = 3000;
    ///< 不可读文件等待时间
    public static final long ERRORTIME = 6000;
    ///< 等待时间
    public static final long SLEEPTIME = 300;
}
