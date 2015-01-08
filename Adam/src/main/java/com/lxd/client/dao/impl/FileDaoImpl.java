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

import com.lxd.client.dao.FileDao;
import com.lxd.client.entity.File;
import com.lxd.client.jdbc.Util;


/**
 * 控制台文件操作数据操作层数据库实现
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileDaoImpl implements FileDao {

    @Override
    public void addFile(File file, Connection connection) {
        PreparedStatement  statement = null;
        try {
            ///< 创建 SQL
            String sql = "insert into File(user_name, md5, length, path, last) values(?, ?, ?, ?, ?)";
            ///< 创建事务
            statement = connection.prepareStatement(sql);
            
            ///< 设置值
            statement.setString(1, file.getUser_name());
            statement.setString(2, file.getMd5());
            statement.setLong(3, file.getLength());
            statement.setString(4, file.getPath());
            statement.setLong(5, file.getLast());
            
            ///< 运行
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(statement);
        }
    }

    @Override
    public void deleteFile(File file, Connection connection) {
        PreparedStatement  statement = null;
        try {
            ///< 创建 SQL
            String sql = "delete from File where user_name = ? and path = ? ";
            ///< 创建事务
            statement = connection.prepareStatement(sql);
            
            ///< 设置值
            statement.setString(1, file.getUser_name());
            statement.setString(2, file.getPath());
            
            ///< 运行
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(statement);
        }
    }

    @Override
    public List<File> queryByUser(String user_name, Connection connection) {
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        List<File> files = new LinkedList<>();
        try {
            ///< 创建 SQL
            String sql = "select * from File where user_name = ?";
            ///< 创建事务
            statement = connection.prepareStatement(sql);
            
            ///< 设置值
            statement.setString(1, user_name);
            
            ///< 运行
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                File file = new File();
                file.setId(resultSet.getLong(1));
                file.setUser_name(resultSet.getString(2));
                file.setMd5(resultSet.getString(3));
                file.setLength(resultSet.getLong(4));
                file.setPath(resultSet.getString(5));
                file.setLast(resultSet.getLong(6));
                files.add(file);
            }         
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(statement);
        }
        
        return files;
    }

    @Override
    public void updateFile(File file, Connection connection) {
        PreparedStatement  statement = null;
        try {
            ///< 创建 SQL
            String sql = "update File set md5 = ?, length = ?, last = ? where user_name = ? and path = ? ";
            ///< 创建事务
            statement = connection.prepareStatement(sql);
            
            ///< 设置值
            statement.setString(1, file.getMd5());
            statement.setLong(2, file.getLength());
            statement.setLong(3, file.getLast());
            statement.setString(4, file.getUser_name());
            statement.setString(5, file.getPath());
            
            ///< 运行
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeConnection(statement);
        }
    }

}
