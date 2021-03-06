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

import java.util.ArrayList;
import java.util.List;


/**
 * 转换工具类
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月17日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class Convert {
    ///< 数组转为List
    public static List<Byte> byteArrayToList(byte[] array) {
        List<Byte> datas = new ArrayList<>(array.length);
        for (byte elem : array) {
            datas.add(elem);
        }
        return datas;
    }  
    
    ///< List转换为数组
    public static byte[] byteListToArray(List<Byte> list) {
        byte[] datas = new byte[list.size()];
        int i = 0;
        for (Byte elem : list) {
            datas[i++] = elem;
        }
        return datas;
    }
}
