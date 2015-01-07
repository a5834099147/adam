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

package com.lxd.client.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.lxd.client.dao.LogDao;
import com.lxd.client.entity.Log;
import com.lxd.client.jdbc.Util;


/**
 * 控制台日志操作数据操作层数据库实现
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class LogDaoImpl implements LogDao {

    @Override
    public void addLog(Log log, Connection connection) {
        PreparedStatement  statement = null;
        try {
            ///< 创建 SQL
            String sql = "insert into Log(id, state, user_name) values(?, ?, ?)";
            ///< 创建事务
            statement = connection.prepareStatement(sql);
            
            ///< 设置值
           statement.setLong(1, log.getId());
           statement.setBoolean(2, log.isState());
           statement.setString(3, log.getUser_name());
            
            ///< 运行
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(statement);
        }
    }   

    @Override
    public Log queryById(Long id, Connection connection) {
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        Log log = null;
        try {
            ///< 创建 SQL
            String sql = "select * from Log where id = ?";
            ///< 创建事务
            statement = connection.prepareStatement(sql);
            
            ///< 设置值
            statement.setLong(1, id);
            
            ///< 运行
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                log = new Log();
                log.setId(resultSet.getLong(1));
                log.setState(resultSet.getBoolean(2));
                log.setUser_name(resultSet.getString(3));
            }
            ///< 提交
            connection.commit();            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(statement);
            Util.closeConnection(resultSet);
        }
        
        return log;
    }

    @Override
    public List<Log> queryByName(String user_name, Connection connection) {
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        List<Log> logs = new LinkedList<>();
        try {
            ///< 创建 SQL
            String sql = "select * from Log where user_name = ?";
            ///< 创建事务
            statement = connection.prepareStatement(sql);
            
            ///< 设置值
            statement.setString(1, user_name);
            
            ///< 运行
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Log log = new Log();
                log.setId(resultSet.getLong(1));
                log.setState(resultSet.getBoolean(2));
                log.setUser_name(resultSet.getString(3));
                logs.add(log);
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(statement);
            Util.closeConnection(resultSet);
        }        
        return logs;
    }

}
