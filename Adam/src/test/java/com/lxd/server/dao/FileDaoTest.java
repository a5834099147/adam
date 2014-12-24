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

package com.lxd.server.dao;

import java.util.List;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Test;

import com.lxd.server.dao.impl.FileDaoImpl;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.File;


/**
 * 文件数据操作接口测试
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileDaoTest {
    private static final Logger log = LogManager.getLogger(FileDaoTest.class);
    @org.junit.AfterClass
    static public void AfterClass() {
        log.info("文件操作类测试结束");
    }
    
    @org.junit.BeforeClass
    static public void BeforeClass() {
        log.info("文件操作类测试开始");
    }
    
    private FileDao fileDao = new FileDaoImpl();
    
    @Test
    ///< 测试添加文件
    public void testAddFile() {        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        File file = new File();
        ///< 测试数据
       String user_name = "li_xd";
       String path = "/Math/addFile.java";
       String md5 = "6485A362D20F80A6021053C72DCF4F9F";
       Long length = 15L;
       ///< 设置值
       file.setLength(length);
       file.setMd5(md5);
       file.setPath(path);
       file.setUser_name(user_name);       
       
        fileDao.addFile(file);
        
        Long id = file.getId();        
        assertNotEquals(id, new Long(0));        
        List<File> files = fileDao.queryByUser(file.getUser_name());
        assertNotEquals(files.size(), 0);
        file = null;
        
        for (File tmp : files) {
            if (tmp.getId().equals(id)) {
                file = tmp;
            }
        }
        
        session.getTransaction().rollback();
        assertNotEquals(file, null);
        
        assertEquals(file.getLength(), length);
        assertEquals(file.getMd5(), md5);
        assertEquals(file.getPath(), path);
        assertEquals(file.getUser_name(), user_name);
    }
    
    @Test
    ///< 更新文件
    public void updateFile() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        File file = new File();
        ///< 测试数据
       String user_name = "li_xd";
       String path = "/Math/addFile.java";
       String md5 = "6485A362D20F80A6021053C72DCF4F9F";
       Long length = 15L;
       ///< 设置值
       file.setLength(length);
       file.setMd5(md5);
       file.setPath(path);
       file.setUser_name(user_name);       
       
        fileDao.addFile(file);
        
        Long id = file.getId();        
        assertNotEquals(id, new Long(0));       
        
        String newMd5 = "6485A362D20F80B6021053C72DCF4F9F";
        Long newLength = 18L;
        file.setMd5(newMd5);
        file.setLength(newLength);
        fileDao.updateFile(file);
        
        List<File> files = fileDao.queryByUser(file.getUser_name());
        assertNotEquals(files.size(), 0);
        file = null;
        
        for (File tmp : files) {
            if (tmp.getId().equals(id)) {
                file = tmp;
            }
        }
        session.getTransaction().rollback();
        assertNotEquals(file, null);
        
        assertEquals(file.getLength(), newLength);
        assertEquals(file.getMd5(), newMd5);
        assertEquals(file.getPath(), path);
        assertEquals(file.getUser_name(), user_name);
    }
    
    @Test
    public void deleteFile() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        File file = new File();
        ///< 测试数据
       String user_name = "li_xd";
       String path = "/Math/addFile.java_删除测试";
       String md5 = "6485A362D20F80A6021053C72DCF4F9F";
       Long length = 15L;
       ///< 设置值
       file.setLength(length);
       file.setMd5(md5);
       file.setPath(path);
       file.setUser_name(user_name);       
       
        fileDao.addFile(file);
        
        Long id = file.getId();        
        assertNotEquals(id, new Long(0));         
        
        fileDao.deleteFile(file);
        
        List<File> files = fileDao.queryByUser(file.getUser_name());
        file = null;
        
        for (File tmp : files) {
            if (tmp.getId().equals(id)) {
                file = tmp;
            }
        }
        session.getTransaction().rollback();
        assertEquals(file, null);
    }
}
