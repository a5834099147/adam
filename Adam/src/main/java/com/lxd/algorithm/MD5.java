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
package com.lxd.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.nio.ch.FileChannelImpl;

/**
 * 强校验和计算算法类
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月25日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class MD5 {

    private static char          hexDigits[] = {
                                             // 用来将字节转换成 16 进制表示的字符
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static MessageDigest md          = null;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getMD5(byte[] source) {
        md.update(source);
        return bufferToHex(md.digest());
    }

    public static String getFileMD5String(File file) throws Exception {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        md.update(byteBuffer);
        
        ///< 手动删除 unmap
        Method method = FileChannelImpl.class.getDeclaredMethod("unmap", MappedByteBuffer.class);
        method.setAccessible(true);
        method.invoke(FileChannelImpl.class, byteBuffer);
        ///< 关闭通道
        ch.close();
        ///< 关闭流
        in.close();
        return bufferToHex(md.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        String md5_string = null;

        try {
            // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2];
            // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {
                // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = bytes[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                // 取字节中高 4 位的数字转换为逻辑右移，将符号位一起右移
                // 取字节中低 4 位的数字转换
                str[k++] = hexDigits[byte0 & 0xf];
            }
            // 换后的结果转换为字符串
            md5_string = new String(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5_string;
    }
}
