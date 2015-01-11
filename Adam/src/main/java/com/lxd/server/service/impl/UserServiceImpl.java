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

package com.lxd.server.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.lxd.server.dao.UserDao;
import com.lxd.server.dao.impl.UserDaoImpl;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.User;
import com.lxd.server.exception.LandingException;
import com.lxd.server.exception.RegisterException;
import com.lxd.server.service.UserSservice;


/**
 * 用户服务
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月5日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class UserServiceImpl implements UserSservice {
    ///< 日志
    private static Logger log = LogManager.getLogger(UserServiceImpl.class);
    ///< 用户数据访问层
    private static UserDao userDao = new UserDaoImpl();

    @Override
    public void landing(String user_name, String user_pwd) throws LandingException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        ///< 查找用户
        User user = userDao.queryByName(user_name);
        if (user == null) {
            log.debug("不存在输入账号为 " + user_name + "的账号");
            session.getTransaction().rollback();
            throw new LandingException("不存在输入账号");
        } else if (!user.getUser_pwd().equals(user_pwd)) {
            log.debug("账号与密码不匹配 " + user_name + "的账号");
            session.getTransaction().rollback();
            throw new LandingException("用户名与键入密码不匹配");
        } 
        session.getTransaction().commit();
    }

    @Override
    public void register(String user_name, String user_pwd) throws RegisterException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
      ///< 查找是否有当前相同名称的用户名
        User user = userDao.queryByName(user_name);
        if (user != null) {
            log.debug("该用户名已经存在" + user_name );
            session.getTransaction().rollback();
            throw new RegisterException("该用户名已经存在");
        }      
        user = new User();
        user.setUser_name(user_name);
        user.setUser_pwd(user_pwd);
        userDao.addUser(user);
        session.getTransaction().commit();
    }

}
