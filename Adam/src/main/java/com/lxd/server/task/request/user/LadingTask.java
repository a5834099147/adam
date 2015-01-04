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

package com.lxd.server.task.request.user;

import com.lxd.protobuf.msg.result.Result.Result_;
import com.lxd.server.dao.UserDao;
import com.lxd.server.dao.impl.UserDaoImpl;
import com.lxd.server.entity.User;


/**
 * 用户登录请求
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月20日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class LadingTask extends UserTask {
    ///< 用户账号
    private String user_name;
    ///< 用户密码
    private String user_pwd;
    
    ///< 用户数据操作层
    private UserDao userDao = new UserDaoImpl();
    
    
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    @Override
    public Result_ userExecute() {
        Result_.Builder result = Result_.newBuilder();
        ///< 查找用户
        User user = userDao.queryByName(user_name);
        if (user == null) {
            result.setSuccess(false);
            result.setErrorMessage("不存在您输入的用户名称");
        } else if (!user.getUser_pwd().equals(user_pwd)) {
            result.setSuccess(false);
            result.setErrorMessage("您输入的用户名与密码不匹配");
        } else {
            result.setSuccess(true);
        }
        
        return result.build();
    }

}
