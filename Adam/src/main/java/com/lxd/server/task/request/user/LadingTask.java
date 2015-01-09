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
import com.lxd.protobuf.msg.result.user.Landing.Landing_;
import com.lxd.protobuf.msg.result.user.User.User_;
import com.lxd.server.exception.LandingException;
import com.lxd.server.service.UserServer;
import com.lxd.server.service.impl.UserServerImpl;


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
    
    ///< 用户业务层
    private static UserServer userServer = new UserServerImpl();
    
    
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    @Override
    public User_ userExecute() {
        User_.Builder result = User_.newBuilder();
        Landing_.Builder landing = Landing_.newBuilder();
        try {
            ///< 用户注册
            userServer.landing(user_name, user_pwd);
            landing.setSuccess(true);
            landing.setUser(user_name);
        } catch (LandingException e) {
            ///< 注册过程中出现异常, 说明注册失败
            landing.setSuccess(false);
            landing.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }       
        
        result.setLanding(landing);
        return result.build();
    }

}
