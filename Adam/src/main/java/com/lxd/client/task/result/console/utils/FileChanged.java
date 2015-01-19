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

package com.lxd.client.task.result.console.utils;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lxd.client.service.FileServer;
import com.lxd.client.service.impl.FileServerImpl;
import com.lxd.client.task.result.console.utils.FileState.State;
import com.lxd.client.view.control.UiSingleton;
import com.lxd.protobuf.msg.result.console.SyncFile.Info;
import com.lxd.utils.Grnerate;

/**
 * 文件更改处理类
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月19日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class FileChanged {

    private static final Logger                     log           = LogManager.getLogger(FileChanged.class);

    // /< 本地文件更改信息(本地文件与本地数据库对比)
    private Map<String, Type>                       localInfoMap  = new HashMap<>();
    // /< 远端文件更改信息(远端文件信息与本地数据库对比)
    private Map<String, Type>                       remoteInfoMap = new HashMap<>();

    // /< 逻辑中出现的文件路径
    private Set<String>                             filePaths     = new HashSet<>();

    // /< 本地数据库存储结果
    private Map<String, com.lxd.client.entity.File> localMap      = new HashMap<>();

    // /< 本地文件服务
    private FileServer                              server        = new FileServerImpl();

    // /< 文件比较
    public enum Type {
        // /< 一致
        CONSISTENT,
        // /< 不一致
        ATYPISM,
        // /< 有 -- 没有
        HAVE_NONE,
        // /< 没有 -- 有
        NONE_HAVE
    };

    /*
     * 得到本地文件的信息
     */
    private void getLocalInfo(String path) {
        // /< 得到本地数据库信息
        getlocalMap();
        // /< 遍历文件列表
        ergodicFile(path);
        // /< 处理遗留数据
        getlocalSurplus();
    }

    /*
     * 得到文件请求列表
     */
    public List<FileState> getFileInfo(String path, List<Info> infos) {
        getLocalInfo(path);
        getRemoteInfo(infos);
        getFilePaths();
        return getFileState();
    }

    private List<FileState> getFileState() {
        List<FileState> states = new LinkedList<>();

        for (String path : filePaths) {
            // /< 通过路径得到本地状态
            Type localType = localInfoMap.get(path);
            // /< 通过路径得到远端状态
            Type remoteType = remoteInfoMap.get(path);

            if (localType == null) {
                if (remoteType == Type.NONE_HAVE) {
                    states.add(new FileState(path, State.DOWNLOAD));
                    continue;
                }
            }

            List<FileState> state = null;
            switch (localType) {
                case CONSISTENT:
                    state = consistentHandle(path, remoteType);
                    if (state != null) {
                        states.addAll(state);
                    }
                    break;
                case ATYPISM:
                    state = atypismHandle(path, remoteType);
                    if (state != null) {
                        states.addAll(state);
                    }
                    break;
                case HAVE_NONE:
                    state = havaNoneHandle(path, remoteType);
                    if (state != null) {
                        states.addAll(state);
                    }
                    break;
                case NONE_HAVE:
                    state = noneHaveHandle(path, remoteType);
                    if (state != null) {
                        states.addAll(state);
                    }
                    break;
                default:
                    log.error("无法识别的状态类型");
            }
        }

        return states;
    }

    private List<FileState> noneHaveHandle(String path, Type remoteType) {
        List<FileState> result = new LinkedList<FileState>();
        switch (remoteType) {
            case CONSISTENT:
                result.add(new FileState(path, State.DELETE));
                break;
            case ATYPISM:
                result.add(new FileState(path, State.DOWNLOAD));
                break;
            case HAVE_NONE:
                result.add(new FileState(path, State.DELETE_TABLE));
                break;
            default:
                log.error("出现无法识别的 NONE_HAVA 子类逻辑");
                break;
        }
        return result;
    }

    private List<FileState> havaNoneHandle(String path, Type remoteType) {
        List<FileState> result = new LinkedList<FileState>();
        if (remoteType == null) {
            result.add(new FileState(path, State.UPLOAD));
            return result;
        }
        switch (remoteType) {
            case NONE_HAVE:
                result.add(new FileState(path, State.RENAME));
                result.add(new FileState(path, State.DOWNLOAD));
                break;
            default:
                log.error("出现无法识别的 HAVA_NONE 子类逻辑");
        }

        return result;
    }

    private List<FileState> atypismHandle(String path, Type remoteType) {
        List<FileState> result = new LinkedList<FileState>();

        switch (remoteType) {
            case CONSISTENT:
                result.add(new FileState(path, State.RENAME));
                break;
            case ATYPISM:
                result.add(new FileState(path, State.RENAME));
                result.add(new FileState(path, State.DOWNLOAD));
                break;
            case HAVE_NONE:
                result.add(new FileState(path, State.RENAME));
                break;
            default:
                log.error("出现无法识别的 ATYPISM 子类逻辑");
        }

        return result;
    }

    private List<FileState> consistentHandle(String path, Type remoteType) {
        List<FileState> result = new LinkedList<FileState>();
        switch (remoteType) {
            case CONSISTENT:
                break;
            case ATYPISM:
                result.add(new FileState(path, State.DOWNLOAD));
                break;
            case HAVE_NONE:
                result.add(new FileState(path, State.DELETE_ENTITY));
                break;
            default:
                log.error("出现无法识别的 CIBSUSTEBT 子类逻辑");
                break;
        }
        return result;
    }

    private void getFilePaths() {
        // /< 得到本地中出现的所有路径
        filePaths.addAll(localInfoMap.keySet());
        // /< 得到远端逻辑中出现的所有路径
        filePaths.addAll(remoteInfoMap.keySet());
    }

    /*
     * 得到远端文件的信息
     */
    private void getRemoteInfo(List<Info> infos) {
        // /< 得到本地数据库信息
        getlocalMap();
        // /< 遍历远端列表
        ergodicRemote(infos);
        // /< 处理遗留数据
        getRemoteSurplus();
    }

    private void getRemoteSurplus() {
        // /< 得到未被使用的文件信息
        for (String path : localMap.keySet()) {
            remoteInfoMap.put(path, Type.HAVE_NONE);
        }
    }

    private void ergodicRemote(List<Info> infos) {
        // /< 遍历传输过来的info信息
        for (Info info : infos) {
            // /< 对比文件
            String file_path = Grnerate.getClientAbsPath(info.getPath());
            // /< 根据文件路径检索文件信息
            com.lxd.client.entity.File file = localMap.get(file_path);
            if (file == null) {
                // /< 不存在该文件信息
                remoteInfoMap.put(file_path, Type.NONE_HAVE);
            } else if (file.getEdition().equals(info.getEdition())) {
                // /< 本地文件与服务器文件相同
                remoteInfoMap.put(file_path, Type.CONSISTENT);
            } else {
                // /< 本地文件与服务器文件不相同
                remoteInfoMap.put(file_path, Type.ATYPISM);
            }
            
            localMap.remove(file_path);
        }
        
    }

    /*
     * 得到本地数据文件存在但是文件不存在的文件信息
     */
    private void getlocalSurplus() {
        // /< 得到未被使用的文件信息
        for (String path : localMap.keySet()) {
            localInfoMap.put(path, Type.NONE_HAVE);
        }
    }

    /*
     * 得到本地数据库文件表结果
     */
    private void getlocalMap() {
        // /< 从数据库中拿到该用户的所有文件信息
        List<com.lxd.client.entity.File> locaList = server.searchFile(UiSingleton.getSingleton().getUser());
        // /< 遍历得到的文件信息, 将其封装到哈希表中, 节约查找的成本
        for (com.lxd.client.entity.File file : locaList) {
            localMap.put(Grnerate.getClientAbsPath(file.getPath()), file);
        }
    }

    /*
     * 遍历目录
     */
    private void ergodicFile(String path) {
        File dir = new File(path);
        // /< 罗列目录的所有文件及文件夹
        File[] files = dir.listFiles();

        // /< 文件夹为空
        if (files == null) return;
        // /< 遍历目录
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                // /< 如果是目录, 则迭代遍历
                getLocalInfo(files[i].getAbsolutePath());
            } else {
                // /< 对比文件
                String file_path = files[i].getAbsolutePath();
                // /< 根据文件路径检索文件信息
                com.lxd.client.entity.File file = localMap.get(file_path);
                if (file == null) {
                    // /< 不存在该文件信息
                    localInfoMap.put(file_path, Type.HAVE_NONE);
                } else if (file.getLast().equals(files[i].lastModified())) {
                    // /< 本地文件与服务器文件相同
                    localInfoMap.put(file_path, Type.CONSISTENT);
                } else {
                    // /< 本地文件与服务器文件不相同
                    localInfoMap.put(file_path, Type.ATYPISM);
                }
            }
            localMap.remove(files[i].getAbsolutePath());
        }
    }

}
