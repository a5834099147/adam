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

package com.lxd.sync;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.algorithm.Adler32;
import com.lxd.algorithm.MD5;
import com.lxd.utils.Define;

/**
 * RSYNC生成工具
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月25日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class RsyncUtil {

    private static final Logger log = LogManager.getLogger(RsyncUtil.class);

    /**
     * 计算文件的CheckSum 列表
     * 
     * @param file_path 文件名
     * @return checksum 集合
     */
    public static List<Chunk> getFileCheckInfo(String file_path) {
        log.debug("正在生成文件校验信息");
        List<Chunk> result = new LinkedList<Chunk>();

        try {
            File file = new File(file_path);
            long length = file.length();

            // /< 块编号
            int index = 0;
            RandomAccessFile raf = new RandomAccessFile(file_path, "r");
            // /< 读取整个文件
            while (length > 0) {
                // /< 取得数据块大小
                int read = (length < Define.CHUNK_SIZE) ? (int) length : Define.CHUNK_SIZE;
                // /< 创建读取数组
                byte[] buffer = new byte[read];
                // /< 读取文件信息到数组中
                raf.read(buffer);
                // /< 计算校验信息
                Chunk chunk = getCheckInfo(buffer, read);
                chunk.setIndex(index++);
                result.add(chunk);
//                // 判断chunks中是否存在该弱校验和链表
//                if (chunkMaps.containsKey(chunk.getAdler32())) {
//                    // 如果存在，则加入链表中
//                    chunkMaps.get(chunk.getAdler32()).add(chunk);
//                } else {
//                    // 如果不存在，则创建链表加入其中
//                    List<Chunk> chunks = new ArrayList<Chunk>();
//                    chunks.add(chunk);
//                    chunkMaps.put(new Long(chunk.getAdler32()), chunks);
//                }
                
                length -= read;
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建" + file_path + "校验和时失败");
        }
        
        return result;
    }

    /**
     * 计算每块的checksum
     * 
     * @param buffer
     * @param length
     * @return
     */
    private static Chunk getCheckInfo(byte[] buffer, int length) {
        ///< 新建块信息
        Chunk chunk = new Chunk();
        ///< 设置弱校验和
        chunk.setAdler32(Adler32.adler32(buffer, 0, length));
        ///< 设置新校验和
        chunk.setMd5(MD5.getMD5(buffer));
        ///< 设置块大小
        chunk.setLength(length);
        return chunk;
    }

    /**
     * 根据传入的字节数组去匹配，如果匹配到则返回该Chunk ，否者为空
     * 
     * @param chunkMaps
     * @param datas
     * @return
     */
    public static Chunk match(Map<Long, List<Chunk>> chunkMaps, byte[] datas) {
        Long adler32 = new Long(Adler32.adler32(datas));
        // 先比较弱校验和 再比较强校验和，如果相等则返回这块数据
        if (chunkMaps.containsKey(adler32)) {
            for (Chunk chunk : chunkMaps.get(adler32)) {
                if (MD5.getMD5(datas).equals(chunk.getMd5())) {
                    return chunk;
                }
            }
        }
        return null;
    }

    /**
     * 读取下一个block
     * 
     * @param raf
     * @param length
     * @return
     * @throws Exception
     */
    public static byte[] readNextBlock(RandomAccessFile raf, long length) throws Exception {
        int bufferSize = (length < Define.CHUNK_SIZE) ? (int) length : Define.CHUNK_SIZE;
        byte[] buffer = new byte[bufferSize];
        raf.read(buffer);
        return buffer;
    }

    /**
     * 读取下一个byte
     * 
     * @param raf
     * @param block
     * @return
     * @throws Exception
     */
    public static byte[] readNextByte(RandomAccessFile raf, byte[] block) throws Exception {
        byte[] next = new byte[1];
        raf.read(next);
        for (int i = 0; i < block.length - 1; i++) {
            block[i] = block[i + 1];
        }
        block[block.length - 1] = next[0];
        return block;
    }

    /**
     * 创建补丁
     * 
     * @param localFile
     * @return
     * @throws Exception
     */
    public static Patch createPatch(File localFile, Map<Long, List<Chunk>> chunkMaps) throws Exception {
        log.debug("正在创建Patch");
        // 分块计算文件的弱校验与强校验，并且设置块号
        Patch patch = new Patch();
        
        RandomAccessFile raf = new RandomAccessFile(localFile, "r");
        long length = localFile.length();
        byte[] bytes = {};
        ///< 块信息
        Chunk chunk = null;
        ///< 数组信息
        List<Byte> diffBytes = new ArrayList<Byte>();
        
        boolean nextBlock = true;

        while (length > 0) {
            if (nextBlock) {
                bytes = readNextBlock(raf, length);
                length -= bytes.length;
            } else {
                bytes = readNextByte(raf, bytes);
                length--;
            }
            // 判断是否匹配
            chunk = match(chunkMaps, bytes);
            if (chunk != null) {
                // 匹配的chunk之前存在differ data ,先将它加进patch
                if (diffBytes.size() > 0) {
                    patch.add(diffBytes);
                    diffBytes = new ArrayList<Byte>(); // 重新创建一个空的differ bytes
                }
                patch.add(chunk);
                nextBlock = true;
            } else {
                // 将最左的byte保存, 不能读block 只能读下一个byte
                diffBytes.add(bytes[0]);
                nextBlock = false;
            }
        }
        // 最后一个block没有匹配上 需要把bytes 中的剩余数据加入到diffBytes中，0 已经加入了。
        if (chunk == null) {
            for (int i = 1; i < bytes.length; i++) {
                diffBytes.add(bytes[i]);
            }
        }
        patch.add(diffBytes);
        
        
        raf.close();
        return patch;

    }

    public static void applyPatch(Patch patch, String old_file, String new_fIle) throws Exception {
        log.debug("正在应用Patch");
        RandomAccessFile oldFIle = new RandomAccessFile(old_file, "r");
        RandomAccessFile newFile = new RandomAccessFile(new_fIle, "rw");
        long srcFileLength = oldFIle.length();
        // 遍历补丁
        for (PatchPart part : patch.getParts()) {
            if (part instanceof PatchPartData) {
                ///< 需要写入数据
                newFile.write(((PatchPartData) part).getDatas());
            } else {
                PatchPartChunk chunkPart = (PatchPartChunk) part;
                long off = chunkPart.getIndex() * Define.CHUNK_SIZE;
                long read_length = srcFileLength - off;
                int length = read_length < Define.CHUNK_SIZE ? (int) read_length : Define.CHUNK_SIZE;
                byte[] bytes = new byte[length];
                oldFIle.seek(off);
                oldFIle.read(bytes);
                newFile.write(bytes);
            }
        }
        oldFIle.close();
        newFile.close();        
    }

//    public static Patch createNewFilePatch(File file) {
//        byte[] buffer = new byte[(int) file.length()];
//        Patch patch = new Patch();
//        RandomAccessFile raf = null;
//        try {
//            raf = new RandomAccessFile(file, "r");
//            raf.read(buffer);
//            List<Byte> list = new ArrayList<Byte>();
//            for (int i = 0; i < buffer.length; ++i) {
//                list.add(buffer[i]);
//            }
//            patch.add(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (null != raf) {
//                try {
//                    raf.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return patch;
//    }
}
