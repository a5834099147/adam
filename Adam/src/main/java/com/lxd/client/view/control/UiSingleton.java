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

package com.lxd.client.view.control;

import java.awt.CardLayout;
import java.awt.EventQueue;

import com.lxd.client.view.ui.ClientFrame;
import com.lxd.client.view.ui.LoginPanel;
import com.lxd.client.view.ui.RegPanel;

/**
 * UI 单例
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年9月18日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class UiSingleton {
	///< 单例变量
	private static UiSingleton singleton = new UiSingleton();
	///< 布局
	private CardLayout layout = null;
		
	///<UI界面	
	//TODO 
	private LoginPanel loginPanel = null;
	private RegPanel regPanel = null;
	
	private ClientFrame clientFrame = null;	
	///< 用户名
	private String user = "li_xd";
	
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    public ClientFrame getBamFrame() {
		return clientFrame;
	}

	private UiSingleton() {
	}
	
	public void BamInit() {
		if (clientFrame == null) {
			EventQueue.invokeLater(new Runnable() {				
				@Override
				public void run() {
				    
					loginPanel = new LoginPanel();
					regPanel = new RegPanel();
					
					layout = new CardLayout();
					
					layout.addLayoutComponent(loginPanel, "login");
					layout.addLayoutComponent(regPanel, "reg");	
					
					clientFrame = new ClientFrame();						
				}
			});				
		}		
	}
	
	public static UiSingleton getSingleton() {
		return singleton;
	}		

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public RegPanel getRegPanel() {
		return regPanel;
	}

	public CardLayout getLayout() {
		return layout;
	}
}
