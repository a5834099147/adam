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
 * 登陆附加信息
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月5日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class ServerLanding extends Property {
    ///< 保存用户名
    private String user_name = null;

    
    public String getUser_name() {
        return user_name;
    }

    
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public ServerLanding(String user_name){
        super();
        this.user_name = user_name;
    }    
}
