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

package com.lxd.server.dao;

import com.lxd.server.entity.User;


/**
 * 用户数据操作接口
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public interface UserDao {
    ///< 增加用户
    void addUser(User user);
    
    ///< 更新用户
    void updateUser(User user);
    
    ///< 删除用户
    void deleteUser(User user);
    
    ///< 查找用户通过用户名
    User queryByName(String user_name);
    
}
