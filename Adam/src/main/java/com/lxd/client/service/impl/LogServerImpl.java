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

package com.lxd.client.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.dao.LogDao;
import com.lxd.client.dao.impl.LogDaoImpl;
import com.lxd.client.entity.Log;
import com.lxd.client.jdbc.Util;
import com.lxd.client.service.LogServer;


/**
 * 日志服务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月8日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class LogServerImpl implements LogServer {
    ///< 日志数据操作实体
    private static final LogDao logDao = new LogDaoImpl();
    ///< 日志记录
    private static final Logger logger = LogManager.getLogger(LogServerImpl.class);    

    @Override
    public void addLog(Log log) {
        ///< 上锁原因: Sqlite 多线程下访问解决不完美
        synchronized (logDao) {
          ///< 创建链接
            Connection connection = Util.getConnection();
            ///< 新增文件信息
            logDao.addLog(log, connection);
            try {
                ///< 提交
                connection.commit();
                logger.debug("新增文件日志");
            } catch (SQLException e) {
                logger.error("新增文件日志时出错, 错误信息为:" + e.getMessage());
                e.printStackTrace();
            }
            ///< 关闭连接
            Util.closeConnection(connection);
        }        
    }

}
