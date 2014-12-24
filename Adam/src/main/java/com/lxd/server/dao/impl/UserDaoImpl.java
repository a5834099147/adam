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

import com.lxd.server.dao.UserDao;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.User;


/**
 * 服务器用户数据操作
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class UserDaoImpl implements UserDao {
    
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(User user) {
        log.info("向数据库中插入一条用户信息");
        
        HibernateUtil.getSessionFactory().getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        log.info("更新一条用户信息");
        
        HibernateUtil.getSessionFactory().getCurrentSession().update(user);        
    }

    @Override
    public void deleteUser(User user) {
        log.info("从数据库中删除一条用户信息");
        
        HibernateUtil.getSessionFactory().getCurrentSession().delete(user);        
    }

    @Override
    public User queryByName(String user_name) {
        return (User) HibernateUtil.getSessionFactory().getCurrentSession().get(User.class, user_name);
    }

}
