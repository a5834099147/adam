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

import com.lxd.client.dao.impl.FileDaoImpl;
import com.lxd.client.entity.File;
import com.lxd.client.jdbc.Util;
/**
 * 测试客户端文件数据操作接口
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class FileDaoTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:adam.db");
            Statement statement = connection.createStatement();

            statement.executeUpdate("drop table if exists File");
            
            statement.executeUpdate("create table file(id bigint primary key, user_name varchar(32), md5 varchar(32), length bigint, path varchar(64))");
            
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        
        log.info("开始测试客户端文件数据操作接口");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        log.info("结束测试客户端文件数据操作接口");
    }

    private static final Logger log = LogManager.getLogger(FileDaoTest.class);
    private FileDao fileDao = new FileDaoImpl();
    
    @Test
    public void test() {
        ///< 创建链接
        Connection connection = Util.getConnection();
        
        ///< 测试数据
        String user_name = "li_xd";
        String path = "/Math/addFile.java_删除测试";
        String md5 = "6485A362D20F80A6021053C72DCF4F9F";
        Long length = 15L;
        
        File file = new File();
        file.setLength(length);
        file.setMd5(md5);
        file.setPath(path);
        file.setUser_name(user_name);
        
        fileDao.addFile(file, connection);
        file = null;
        
        List<File> files = fileDao.queryByUser(user_name, connection);
        for (File tmp : files) {
            if (tmp.getLength().equals(length) && tmp.getMd5().equals(md5) && tmp.getPath().equals(path)) {
                file = tmp;
            }
        }
        
        assertNotEquals(file, null);
        
        assertEquals(file.getLength(), length);
        assertEquals(file.getMd5(), md5);
        assertEquals(file.getPath(), path);
        assertEquals(file.getUser_name(), user_name);
        
        String newMd5 = "6485A362D20F80A6021053C72DCF4F9D";
        file.setMd5(newMd5);
        fileDao.updateFile(file, connection);
        files = fileDao.queryByUser(user_name, connection);
        for (File tmp : files) {
            if (tmp.getLength().equals(length) && tmp.getMd5().equals(newMd5) && tmp.getPath().equals(path)) {
                file = tmp;
            }
        }
        
        assertNotEquals(file, null);
        
        assertEquals(file.getLength(), length);
        assertEquals(file.getMd5(), newMd5);
        assertEquals(file.getPath(), path);
        assertEquals(file.getUser_name(), user_name);
        
        fileDao.deleteFile(file, connection);
        file = null;
        files = fileDao.queryByUser(user_name, connection);
        for (File tmp : files) {
            if (tmp.getLength().equals(length) && tmp.getMd5().equals(newMd5) && tmp.getPath().equals(path)) {
                file = tmp;
            }
        }
        
        assertEquals(file, null);    
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
