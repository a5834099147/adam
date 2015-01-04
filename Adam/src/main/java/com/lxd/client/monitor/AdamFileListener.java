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

package com.lxd.client.monitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;


/**
 * 目录监听回调
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月4日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class AdamFileListener implements FileAlterationListener {
    @Override  
    public void onStop(FileAlterationObserver observer) { 
        
    }  
      
    @Override  
    public void onStart(FileAlterationObserver observer) {  
    }  
      
    @Override  
    public void onFileDelete(File file) {  
         System.out.println("onFileDelete "+file.getPath());            
    }  
      
    @Override  
    public void onFileCreate(File file) {  
         System.out.println("onFileCreate "+file.getPath());           
    }  
      
    @Override  
    public void onFileChange(File file) {             
         System.out.println("onFileChange "+file.getPath());  
    }  
      
    @Override  
    public void onDirectoryDelete(File file) {             
        System.out.println("onDirectoryDelete "+file.getPath());  
    }  
      
    @Override  
    public void onDirectoryCreate(File file) {  
        System.out.println("onDirectoryCreate "+file.getPath());  
          
    }  
      
    @Override  
    public void onDirectoryChange(File file) {  
        System.out.println("onDirectoryChange "+file.getPath());            
    }  
}
