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

package com.lxd.server.resource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * 服务器资源文件
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月7日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ServerResource {
    ///< 线程安全的 HashSet
    private Set<String> pathSet = Collections.synchronizedSet(new HashSet<String>());
    
    ///< 检查目录
    public boolean checkPath(String path) {
        ///< 如果包含了该目录
        if (pathSet.contains(path)) {
            return false;
        } else {
            pathSet.add(path);
            return true;
        }
    }

    private ServerResource(){
    }

    private static ServerResource singleton = new ServerResource();

    public static ServerResource getSingleton() {
        return singleton;
    }
}
