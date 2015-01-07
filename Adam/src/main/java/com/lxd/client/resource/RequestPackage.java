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

package com.lxd.client.resource;

import com.lxd.client.resource.property.Property;
import com.lxd.protobuf.msg.Msg.Msg_;


/**
 * 请求包
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class RequestPackage {
    ///< 消息包裹
    private Msg_ msg;
    ///< 请求附带参数
    private Property property;   

    public Msg_ getMsg() {
        return msg;
    }
    
    public void setMsg(Msg_ msg) {
        this.msg = msg;
    }

    
    public Property getProperty() {
        return property;
    }

    
    public void setProperty(Property property) {
        this.property = property;
    }

    public RequestPackage(Msg_ msg, Property property){
        super();
        this.msg = msg;
        this.property = property;
    }    
}
