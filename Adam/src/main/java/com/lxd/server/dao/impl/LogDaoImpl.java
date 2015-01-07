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

package com.lxd.server.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.server.dao.LogDao;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.Log;


/**
 * 服务器日志数据操作
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class LogDaoImpl implements LogDao{
    
    private static final Logger logger = LogManager.getLogger(LogDaoImpl.class);

    @Override
    public void addLog(Log log) {
        logger.info("增加文件信息");
        
        HibernateUtil.getSessionFactory().getCurrentSession().save(log);
    }
    
    @Override
    public Log queryByName(String user_name) {
        return (Log) HibernateUtil.getSessionFactory().getCurrentSession().get(Log.class, user_name);
    }

}
