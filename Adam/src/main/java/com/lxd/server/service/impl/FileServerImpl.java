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

import org.hibernate.Session;

import com.lxd.server.dao.FileDao;
import com.lxd.server.dao.FileHddDao;
import com.lxd.server.dao.impl.FileDaoImpl;
import com.lxd.server.dao.impl.FileHddDaoImpl;
import com.lxd.server.dao.util.HibernateUtil;
import com.lxd.server.entity.File;
import com.lxd.server.service.FileServer;
import com.lxd.utils.Grnerate;


/**
 * 文件业务的实现
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月25日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileServerImpl implements FileServer {
    private static FileDao fileDao = new FileDaoImpl();
    private static FileHddDao hddDao = new FileHddDaoImpl();

    @Override
    public boolean havaFile(String md5, Long length) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        ///< 查找是否含有与条件相符的文件
        boolean result = fileDao.queryByMd5AndLength(md5, length);
        session.getTransaction().commit();
        
        return result;
    }

    @Override
    public File searchFile(String user_name, String path) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        ///< 查找给定用户目录的文件信息
        File result = fileDao.queryByUserAndPath(user_name, path);
        session.getTransaction().commit();
        
        return result;
    }

    @Override
    public void addFile(File file) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        ///< 增加文件信息
        fileDao.addFile(file);
        session.getTransaction().commit();        
    }

    @Override
    public void updateFile(File file) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        ///< 查找未更新前文件信息
        File tmp = fileDao.queryByUserAndPath(file.getUser_name(), file.getPath());
        ///< 更新文件
        fileDao.updateFile(file);
        ///< 查找是否存在被更新文件相同的文件
        boolean result = fileDao.queryByMd5AndLength(tmp.getMd5(), tmp.getLength());        
        session.getTransaction().commit();         
        
        if (!result) {
            ///< 如果不存在则删除原来的文件
            hddDao.deleteFile(Grnerate.getPath(tmp.getMd5(), tmp.getLength()));
        }
    }

    @Override
    public void deleteFile(File file) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        fileDao.deleteFile(file);
        boolean result = fileDao.queryByMd5AndLength(file.getMd5(), file.getLength());
        session.getTransaction().commit();
        
        if (!result) {
            ///< 如果不存在则删除原来的文件
            hddDao.deleteFile(Grnerate.getPath(file.getMd5(), file.getLength()));
        }
    }
    
    

    @Override
    public void addFile(String file_name, Long length) {
        hddDao.addFile(file_name, length);        
    }

    @Override
    public void editFile(String file_name, Long seek, byte[] datas) {
        hddDao.editFile(file_name, seek, datas);        
    }

}
