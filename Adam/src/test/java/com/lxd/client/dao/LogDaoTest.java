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

package com.lxd.client.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lxd.client.dao.impl.LogDaoImpl;
import com.lxd.client.entity.Log;
import com.lxd.client.jdbc.Util;


/**
 * 测试客户端日志数据操作接口
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class LogDaoTest {
    private static final Logger logger = LogManager.getLogger(LogDaoTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:adam.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists Log");
            statement.executeUpdate("create table log(id bigint primary key, state bit, user_name varchar(32))");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        
        logger.info("开始测试客户端日志数据操作接口");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        logger.info("结束测试客户端日志数据操作接口");
    }
    
    LogDao logDao = new LogDaoImpl();
    
    @Test
    public void test() {
        Long id = 15L;
        String user_name = "li_xd";
        Boolean state = true;
        
        
        Log log = new Log();
        log.setId(id);
        log.setUser_name(user_name);
        log.setState(state);
        
        Connection connection = Util.getConnection();
        logDao.addLog(log, connection);
        
        log = null;
        List<Log> logs = logDao.queryByName(user_name, connection);
        for (Log tmp : logs) {
            if (tmp.getId().equals(id)) {
                log = tmp;
            }
        }
        
        assertNotEquals(log, null);
        
        log = logDao.queryById(id, connection);
        assertEquals(log.getId(), id);
        assertEquals(log.getUser_name(), user_name);
        assertEquals(log.isState(), state);
        
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
