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

package com.lxd.client.task.job.server.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lxd.utils.Define;


/**
 * 文件工具类
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月11日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class FileUtil {
    private static final Logger log = LogManager.getLogger(FileUtil.class);

    public static byte[] read(File file, Long bolck) {
        // /< 读取的数组
        byte[] buffer = new byte[(int) (file.length() - bolck > Define.BLOCK_SIZE ? Define.BLOCK_SIZE : file.length()
                                                                                                        - bolck)];

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            // /< 定光标
            raf.seek(bolck);
            // /< 写文件
            raf.read(buffer);
            log.info("文件光标" + bolck + "读取完毕, 所属文件" + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("文件" + file.getAbsolutePath() + "文件无法找到:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件" + file.getAbsolutePath() + "IO错误" + e.getMessage());
        }
        return buffer;
    }
}
