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

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.lxd.client.monitor.MonitorDir;
import com.lxd.client.resource.ClientResource;
import com.lxd.client.resource.RequestPackage;
import com.lxd.client.resource.property.ServerLanding;
import com.lxd.client.view.control.UiSingleton;
import com.lxd.client.view.handle.LoginHandle;
import com.lxd.client.view.ui.util.ViewUtil;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.user.Landing.Landing_;
import com.lxd.protobuf.msg.request.user.User.User_;
import com.lxd.utils.Define;

/**
 * 登陆界面
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年9月18日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class LoginPanel extends JPanel implements LoginHandle {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -7111350291666839105L;
	boolean id_check = false;
	boolean pwd_check = false;
	boolean show_check = false;
	///< 输入框
	private JTextField text_user;
	private JTextField text_check;
	private JPasswordField text_pwd;
	
	///< 按钮
	private JButton button_login;
	private JButton button_regist;
	
	///< 验证码
	private JLabel label_show;
	private JPanel panel;
	
	
	public LoginPanel() {				
		init();			
		setListener();										
	}
	
	private void init() {
		setCode();
		
		panel = new JPanel() {
			
			private static final long serialVersionUID = -1992132241707483452L;

			@Override
			protected void paintComponent(final Graphics g) {
				
				Graphics2D g2d = (Graphics2D)g;
				GradientPaint gPaint = new GradientPaint(0, 0, new Color(1.0f, 1.0f, 1.0f, 0.0f), 0, getHeight(), new Color(0.0f, 0.0f, 0.0f, 0.8f));						
							
				g2d.setPaint(gPaint);
				g2d.fillRect(0, 0, getWidth(), getHeight());									
			}
		};
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 5, true));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(442)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(190, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		
		JLabel label_id = new JLabel("");
		label_id.setIcon(new ImageIcon(LoginPanel.class.getResource("/com/lxd/client/view/res/user.png")));
		label_id.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		
		JLabel label_pwd = new JLabel("");
		label_pwd.setIcon(new ImageIcon(LoginPanel.class.getResource("/com/lxd/client/view/res/pwd.png")));
		label_pwd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		
		JLabel label_check = new JLabel("");
		label_check.setIcon(new ImageIcon(LoginPanel.class.getResource("/com/lxd/client/view/res/check.png")));
		label_check.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		text_user = new JTextField();
		text_user.setBackground(Color.WHITE);
		text_user.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		text_user.setColumns(10);
		
		text_pwd = new JPasswordField();
		text_pwd.setBackground(Color.WHITE);
		text_pwd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		
		text_check = new JTextField();
		text_check.setBackground(Color.WHITE);
		text_check.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		text_check.setColumns(10);
		
		button_login = new JButton("登陆");
		button_login.setEnabled(false);
		button_login.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		
		
		button_regist = new JButton("注册");
		button_regist.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
		
		label_show = new JLabel("2256");
		label_show.setFont(new Font("黑体", Font.PLAIN, 18));
		
		JLabel label_login = new JLabel("登陆系统");
		label_login.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(178)
					.addComponent(label_login, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(63)
					.addComponent(label_id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(text_user, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(63)
					.addComponent(label_pwd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(text_pwd, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(63)
					.addComponent(label_check)
					.addGap(18)
					.addComponent(text_check, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(label_show, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(116)
					.addComponent(button_login)
					.addGap(83)
					.addComponent(button_regist))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addComponent(label_login, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_id)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(text_user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_pwd)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(text_pwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_check, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(text_check, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(label_show)))
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button_login)
						.addComponent(button_regist)))
		);
		panel.setLayout(gl_panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{label_check, label_pwd, label_id, text_pwd, text_user, label_show, text_check, button_login, button_regist}));
		setLayout(groupLayout);
	}
	
	private void setTextUserListener() {
		text_user.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String id_string = text_user.getText();
				if (id_string.matches("^[a-zA-z][a-zA-Z0-9_]{2,9}$")) {
					check("id", true);
					EventQueue.invokeLater(new Runnable() {						
						@Override
						public void run() {
							text_user.setForeground(Color.BLACK);							
						}
					});
					
				} else {
					check("id", false);
					EventQueue.invokeLater(new Runnable() {						
						@Override
						public void run() {
							text_user.setForeground(Color.RED);							
						}
					});
					
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}
	
	
	
	private void setLabelShowListener() {
		label_show.addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setCode();
				check("show", false);
			}
		});
	}
	
	private void setTextPwdListener() {
		text_pwd.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String pwd = new String(text_pwd.getPassword());
				check("pwd", !pwd.equals(""));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}
	
	private void setTextCheckListener() {
		text_check.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String check_1 = text_check.getText();
				String check_2 = label_show.getText();
				
				check("show", check_1.equals(check_2));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}
	
	private void setButtonLoginListener() {
		button_login.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			    ///< 输入的用户名
			    String user_name = text_user.getText();
			    ///< 输入的密码			    
			    String user_pwd = new String(text_pwd.getPassword());	
			    Msg_.Builder msg = Msg_.newBuilder();
                Request_.Builder request = Request_.newBuilder();
                User_.Builder user = User_.newBuilder();
                Landing_.Builder landing = Landing_.newBuilder();
                landing.setUserName(user_name);
                landing.setUserPwd(user_pwd);
                user.setLanding(landing);
                request.setUser(user);
                msg.setRequest(request);
                msg.setJobId(-1L);
                ClientResource.getSingleton().submitRequest(new RequestPackage(msg.build(), new ServerLanding(user_name)));         
				setNull();
			}
		});
	}
	
	private void setButtonRegistListener() {
		button_regist.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				///< 清空数据
				setNull();
				UiSingleton.getSingleton().getLayout().show(
						UiSingleton.getSingleton().getBamFrame().getContentPane(), "reg");				
			}
		});
	}
	
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);		
			
		ImageIcon image = new ImageIcon(ImageIO.class.getResource("/com/lxd/client/view/res/login_bk.png"));
		g.drawImage(image.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);					
	}
	
	private void setListener() {			
		setTextUserListener();
		setTextPwdListener();
		setButtonLoginListener();
		setButtonRegistListener();
		setTextCheckListener();
		setLabelShowListener();
	}	
	
	///< 设置随机数
	private void setCode() {
		EventQueue.invokeLater(new Runnable() {			
			@Override
			public void run() {
				label_show.setText(ViewUtil.createCode() + "");
			}
		});				
	}
	
	void setNull() {
		text_user.setText("");
		text_check.setText("");
		text_pwd.setText("");
		
		id_check = false;
		pwd_check = false;
		show_check = false;
		
		button_login.setEnabled(false);
	}
	
	void check(String item, boolean value) {
		switch (item) {
		case "id" :
			id_check = value;
			break;
		case "pwd" :
			pwd_check = value;
			break;
		case "show" :
			show_check = value;
			break;
		default:
			System.out.println("登陆消息错误##" + item);
			break;
		}
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if (id_check && pwd_check && show_check) {
					button_login.setEnabled(true);
				} else {
					button_login.setEnabled(false);
				}				
			}
		});
		
	}

	@Override
	public void loginSuccess(String user) {
	    ///< 将用户名注册给界面
	    UiSingleton.getSingleton().setUser(user);
	    ///< 开启监听服务
	    new MonitorDir(Define.CLIENT).start();
	    //TODO 跳转页面
	}

	@Override
	public void loginFail(String msg) {
		JOptionPane.showMessageDialog(null, "失败原因: " + msg , "登陆失败", JOptionPane.ERROR_MESSAGE);		
	}
}
