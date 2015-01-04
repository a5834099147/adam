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

package com.lxd.client.view.ui;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.lxd.client.view.control.UiSingleton;

/**
 * 框架
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月3日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class BamFrame extends JFrame{
	private static final long serialVersionUID = 267941845725755204L;

	public BamFrame() {
		this.setTitle("Adam 云盘");
		getContentPane().setLayout(UiSingleton.getSingleton().getLayout());
		getContentPane().add(UiSingleton.getSingleton().getLoginPanel());
		getContentPane().add(UiSingleton.getSingleton().getRegPanel());
		
		///< 自适应布局大小
		this.pack();
		
		///< 设置界面为屏幕中央
		this.setLocationRelativeTo(null);
		
		///< 设置背景为透明
		((JComponent) this.getContentPane()).setOpaque(false);
		
		///< 设置可见
		this.setVisible(true);
		
		///< 设置关闭选项(与控制台等同存活)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		///< 设置大小
		this.setSize(1000, 521);
	}
}
