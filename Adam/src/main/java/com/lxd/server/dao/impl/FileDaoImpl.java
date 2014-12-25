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

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;

import com.lxd.server.dao.FileDao;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.File;


/**
 * 服务器文件数据操作
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月23日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileDaoImpl implements FileDao {
    private static final Logger log = LogManager.getLogger(FileDaoImpl.class);
    
    @Override
    public void addFile(File file) {
        log.info("向数据库中加入文件信息");
        
        HibernateUtil.getSessionFactory().getCurrentSession().save(file);
    }

    @Override
    public void deleteFile(File file) {
        log.info("从数据库中删除一条文件信息");
        
        HibernateUtil.getSessionFactory().getCurrentSession().delete(file);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<File> queryByUser(String user_name) {
       String hsql = "from File as file where file.user_name = :user_name";
       Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hsql);
       query.setString("user_name", user_name);
       
       return query.list();
    }

    @Override
    public void updateFile(File file) {
       HibernateUtil.getSessionFactory().getCurrentSession().update(file);
    }

    @Override
    public File queryByUserAndPath(String user_name, String path) {
        String hsql = "from File as file where file.user_name = :user_name and file.path = :path";
        Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hsql);
        query.setString("user_name", user_name);
        query.setString("path", path);
        
        return (File) query.list().get(0);
    }

    @Override
    public boolean queryByMd5AndLength(String md5, Long length) {
        String hsql = "from File as file where file.md5 = :md5 and file.length = :length";
        Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hsql);
        query.setString("md5", md5);
        query.setLong("length", length);
        return !query.list().isEmpty();
    }
}
