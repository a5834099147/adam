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

package com.lxd.server.dao.util;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;


/**
 * 七牛网络存储工具类
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月7日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class QiNiuUtil {
    private static final String AK = "5umMfoEUmaYbKIThcIqG4kZvGcbOUcnRNesSqWh-";
    private static final String SK = "GDTwCMTRICa-Un1AoGfg-Ic1lZkz2NReNGcWwP_t";
    private static final String BUCKET = "adam";
    
    ///< 得到上传标识
    public static String getPutString() throws AuthException, JSONException {
        ///< 配置 access_key
        Config.ACCESS_KEY = AK;
        ///< 配置 secret_key
        Config.SECRET_KEY = SK;
        ///< 得到授信操作
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        ///< 加载操作域
        PutPolicy putPolicy = new PutPolicy(BUCKET);
        return putPolicy.token(mac);       
    }
    
    
}
