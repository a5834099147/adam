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

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lxd.server.dao.impl.UserDaoImpl;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.User;


/**
 * 用户数据操作接口测试
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class UserDaoTest {
    
    private static final Logger log = LogManager.getLogger(UserDaoTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        log.info("开始测试用户数据操作接口");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        log.info("完成测试用户数据操作接口");        
    }
    
    private UserDao userDao = new UserDaoImpl();
    
    @Test
    public void test() {
        ///< 增加
        String user_name = "li_xd";
        String user_pwd = "li_xd";
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        User user = new User();
        user.setUser_name(user_name);
        user.setUser_pwd(user_pwd);
        
        userDao.addUser(user);
        user = userDao.queryByName(user_name);
        
        assertNotEquals(user, null);
        assertEquals(user.getUser_pwd(), user_pwd);
        
        String pwd = "lixd";
        user.setUser_pwd(pwd);
        userDao.updateUser(user);
        user = userDao.queryByName(user_name);
        assertNotEquals(user, null);
        assertEquals(user.getUser_pwd(), pwd);
        
        userDao.deleteUser(user);
        user = userDao.queryByName(user_name);
        session.getTransaction().rollback();
        assertEquals(user, null);
    }

}
