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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lxd.server.dao.FileHddDao;
import com.lxd.utils.Utils;


/**
 * 文件数据接口实现
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月25日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileHddDaoImpl implements FileHddDao {
    private static final Logger log= LogManager.getLogger(FileHddDaoImpl.class);

    @Override
    public void addFile(String file_name, Long length) {
        try {
            File file = new File(file_name);
            ///< 创建新的空白文件
            file.createNewFile();           
            RandomAccessFile raf = new RandomAccessFile(file_name, "rw");
            log.info("创建文件成功" + file_name);
            raf.setLength(length);
            log.info("给文件" + file_name + "分配的大小为 :" + length);
            Utils.closeConnection(raf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("文件" + file_name + "文件无法找到:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件" + file_name + "IO错误" + e.getMessage());
        }
    }

    @Override
    public void editFile(String file_name, Long seek, byte[] datas) {
        try (RandomAccessFile raf = new RandomAccessFile(file_name, "rw")) {
            ///< 定光标
            raf.seek(seek);
            ///< 写文件
            raf.write(datas);              
            log.info("文件光标" + seek + "写完, 所属文件" + file_name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("文件" + file_name + "文件无法找到:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件" + file_name + "IO错误" + e.getMessage());
        }        
    }

    @Override
    public void deleteFile(String file_name) {
      File file = new File(file_name);
      file.delete();       
    }

}
