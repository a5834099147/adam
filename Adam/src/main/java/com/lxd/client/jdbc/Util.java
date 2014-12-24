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

package com.lxd.client.jdbc;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 客户端 SQLITE 的工具类
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class Util {

    // /< 得到数据库链接
    static public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:adam.db");
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    // /< 关闭链接
    static public void closeConnection(Closeable... closes) {
        for (Closeable close : closes) {
            try {
                if (close != null) {
                    close.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // /< 关闭数据库链接
    public static void closeConnection(Connection... connections) {
        for (Connection close : connections) {
            if (close != null) {
                try {
                    close.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    ///< 关闭事务
    public static void closeConnection(PreparedStatement ... statement) {
        for (PreparedStatement close : statement) {
            if (close != null) {
                try {
                    close.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }  
    }

    public static void closeConnection(ResultSet ... resultSet) {
        for (ResultSet close : resultSet) {
            if (close != null) {
                try {
                    close.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }          
    }
}
