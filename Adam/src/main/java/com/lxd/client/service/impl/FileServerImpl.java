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

package com.lxd.client.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.dao.FileDao;
import com.lxd.client.dao.HDDFileDao;
import com.lxd.client.dao.impl.FileDaoImpl;
import com.lxd.client.dao.impl.HDDFileDaoImpl;
import com.lxd.client.entity.File;
import com.lxd.client.jdbc.Util;
import com.lxd.client.service.FileServer;


/**
 * 文件服务实现
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月8日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileServerImpl implements FileServer {
    private static final Logger log = LogManager.getLogger(FileServerImpl.class);
    
    private static FileDao fileDao = new FileDaoImpl();
    private static HDDFileDao hddFileDao = new HDDFileDaoImpl();

    @Override
    public void addFile(File file) {
        ///< 上锁原因: sqlite多线程解决不完美
        synchronized (fileDao) {
            ///< 创建链接
            Connection connection = Util.getConnection();
            ///< 新增文件信息
            fileDao.addFile(file, connection);
            try {
                ///< 提交
                connection.commit();
                log.debug("新增文件记录");
            } catch (SQLException e) {
                log.error("新增文件记录时出错, 错误信息为:" + e.getMessage());
                e.printStackTrace();
            }
            ///< 关闭连接
            Util.closeConnection(connection);
        }       
    }

    @Override
    public void deleteFile(File file) {
        ///< 上锁原因: sqlite多线程解决不完美
        synchronized (fileDao) {
            ///< 创建链接
            Connection connection = Util.getConnection();
            ///< 新增文件信息
            fileDao.deleteFile(file, connection);
            try {
                ///< 提交
                connection.commit();
                log.debug("删除一条文件记录");
            } catch (SQLException e) {
                log.error("删除文件记录时出错, 错误信息为:" + e.getMessage());
                e.printStackTrace();
            }
            ///< 关闭连接
            Util.closeConnection(connection);
        }       
    }

    @Override
    public void updateFile(File file) {
        ///< 上锁原因: sqlite多线程解决不完美
        synchronized (fileDao) {
          ///< 创建链接
            Connection connection = Util.getConnection();
            ///< 新增文件信息
            fileDao.updateFile(file, connection);
            try {
                ///< 提交
                connection.commit();
                log.debug("更新一条文件记录");
            } catch (SQLException e) {
                log.error("更新文件记录时出错, 错误信息为:" + e.getMessage());
                e.printStackTrace();
            }
            ///< 关闭连接
            Util.closeConnection(connection);
        }        
    }

    @Override
    public List<File> searchFile(String user_name) {
        ///< 返回的结果
        List<File> fileList = null;
      ///< 上锁原因: sqlite多线程解决不完美
        synchronized (fileDao) {
          ///< 创建链接
            Connection connection = Util.getConnection();
            ///< 新增文件信息
            fileList = fileDao.queryByUser(user_name, connection);
            try {
                ///< 提交
                connection.commit();
            } catch (SQLException e) {
                log.error("查找文件记录时出错, 错误信息为:" + e.getMessage());
                e.printStackTrace();
            }
            ///< 关闭连接
            Util.closeConnection(connection);
            return fileList;
        }        
    }

    @Override
    public void deleteHDDFile(String path) {
        hddFileDao.delete(path);
        log.info("删除硬盘文件" + path);
    }

    @Override
    public java.io.File renameHddFile(String path) {
        log.info("更名文件" + path);
        return hddFileDao.rename(path);
    }  
    
}
