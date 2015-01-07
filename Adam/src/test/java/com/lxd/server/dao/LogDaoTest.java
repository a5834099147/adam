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

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lxd.server.dao.impl.LogDaoImpl;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.Log;
import com.lxd.utils.Grnerate;


/**
 * 日志数据操作接口测试
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月24日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class LogDaoTest {
    private static final Logger loger = LogManager.getLogger(LogDaoTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        loger.info("开始测试日志数据操作接口");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        loger.info("日志数据操作接口测试结束");       
    }
    
    LogDao logDao = new LogDaoImpl();

    @Test
    public void addLog() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Long id = Grnerate.getTaskId();
        Boolean state = true;
        String user_name = "li_xd";
        
        Log log = new Log();
        log.setId(id);
        log.setState(state);
        log.setUser_name(user_name);
        
        logDao.addLog(log);
        
        Log tmp = logDao.queryById(id);
        assertEquals(tmp.getId(), log.getId());
        assertEquals(tmp.getUser_name(), log.getUser_name());
        assertEquals(tmp.isState(), log.isState());
        
        tmp = null;
        List<Log> logs = logDao.queryByName(user_name);
        for (Log logTemp : logs) {
            if (logTemp.getId() == log.getId()) {
                tmp = logTemp;
            }
        }
        
        session.getTransaction().rollback();
        assertNotEquals(tmp, null);
        assertEquals(tmp.getId(), log.getId());
        assertEquals(tmp.getUser_name(), log.getUser_name());
        assertEquals(tmp.isState(), log.isState());        
    }

}
