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

package com.lxd.resource;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 任务状态检测
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月18日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class JobStatus {
    private static final Logger log = LogManager.getLogger(JobStatus.class);
    
    private Hashtable< Long, Status> status = new Hashtable<>();
    
    
    ///< 增加任务
    public void addJob(Long jobId, Object property) {
        status.put(jobId, new Status().setProperty(property));
        log.debug("新建一个任务, 任务编号为:" + jobId);
    }
    
    ///< 得到任务的附加属性
    public Object getProperty(Long jobId) {
        return status.get(jobId).getProperty();
    }
    
    ///< 检测是否完成
    public boolean checkFinished(Long jobId) {
        return status.get(jobId).isFinished();
    }    
 
    /*
     *  设置任务总块数
     */
    public void init (Long jobid, int totoal) {
        status.get(jobid).setTotal(totoal);
        log.debug("初始化任务" + jobid + "的任务个数为" + totoal);
    }
    
    ///< 判断块是否需要进行
    public boolean checkToDo(Long jobId, int total, int current) {
        ///< 判断任务是否开始过
        if (status.get(jobId).getTotal() == 0) {
            status.get(jobId).setTotal(total);
        }
        
        return  !status.get(jobId).getState(current);        
    }
    
    ///< 设置当前块正在进行
    public void setDoing(Long jobId, int current) {
        status.get(jobId).setDoingState(current);
        log.debug("设置任务编号为" + jobId + "的任务块" + current + "正在进行中");
    }
    
    ///< 设置当前块完成
    public void setDone(Long jobId, int current) {
        status.get(jobId).setDoneState(current);
        log.debug("设置任务编号为" + jobId + "的任务块" + current + "已经完成");
    }
    
    ///< 设置完成, 删除任务信息
    public void setDone(Long jobId) {
        status.remove(jobId);
        log.debug("设置任务编号为" + jobId + "的任务已经完成");
    }
    
    ///< 设置当前块失败
    public void setError(Long jobId, int current) {
        status.get(jobId).setErrorState(current);
        log.debug("设置任务编号为" + jobId + "的任务块" + current + "发生错误");
    }
    
    ///< 获取未完成的块好
    public List<Integer> getUnfinished(Long jobId) {
        return status.get(jobId).getUnfinished();
    }
    
    ///< 获取已完成的块号
    public List<Integer> getDone(Long jobId) {
        return status.get(jobId).getDone();
    }
    
    ///< 获取没有开始的块号
    public List<Integer> getNotStart(Long jobId) {
        return status.get(jobId).getNotStart();
    }
    
    ///< 获取正在进行中的任务
    public List<Integer> getDoing(Long jobId) {
        return status.get(jobId).getDoing();
    }
    
    ///< 获取当前完成数量
    public int getCurrentFinished(Long jobId) {
        return status.get(jobId).getCurrent();
    }
    
    ///< 状态内部类
    private class Status {
        ///< 任务总数
        private int total;
        ///< 任务状态数组
        private byte[] states;
        ///< 任务当前完成数量
        private int current;    
        ///< 任务附加信息
        private Object property;       
        
        public Object getProperty() {
            return property;
        }

        
        public Status setProperty(Object property) {
            this.property = property;
            return this;
        }

        ///< 设置块数时, 初始化状态数组
        public void setTotal(int total) {
            this.total = total;
            states = new byte[total];
        }       
        
        public int getTotal() {
            return total;
        }

        ///< 得到当前块号的任务是否正在进行或完成
        public boolean getState(int num) {
            if (states[num] != 0) {
                return true;
            } else {
                return false;
            }
        }
        
        ///< 设置当前块号的任务为进行态
        public void setDoingState(int num) {
            states[num] = -1;
        }
        
        ///< 设置当前块号的任务为完成态
        public void setDoneState(int num) {
            states[num] = 1;
            ///< 当前数量完成数量 + 1
            current += 1;
        }      
        
        
        ///< 设置当前块号的任务失败
        public void setErrorState(int num) {
            states[num] = -2;
        }
        
        ///< 获取未完成任务的列表
        public List<Integer> getUnfinished() {
            List<Integer> res = new LinkedList<>();
            for (int i = 0; i < total; ++i) {
                if (states[i] != 1) {
                    res.add(i);
                }
            }
            return res;
        }
        
        ///< 获取已经完成的任务
        public List<Integer> getDone() {
            List<Integer> res = new LinkedList<>();
            for (int i = 0; i < total; ++i) {
                if (states[i] == 1) {
                    res.add(i);
                }
            }
            return res;
        }
        
        ///< 获取没开始做的任务
        public List<Integer> getNotStart() {
            List<Integer> res = new LinkedList<>();
            for (int i = 0; i < total; ++i) {
                if (states[i] == 0) {
                    res.add(i);
                }
            }
            return res;
        }
        
        ///< 获取正在进行中的任务
        public List<Integer> getDoing() {
            List<Integer> res = new LinkedList<>();
            for (int i = 0; i < total; ++i) {
                if (states[i] == -1) {
                    res.add(i);
                }
            }
            return res;
        }  
        
        ///< 获取当前完成数量
        public int getCurrent() {
            return current;
        }
        
        ///< 获取是否完成
       public boolean isFinished() {
           return current == total;
       }
       
    }
}