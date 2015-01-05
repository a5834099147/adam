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

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.border.LineBorder;










import com.lxd.client.resource.ClientResource;
import com.lxd.client.resource.RequestPackage;
import com.lxd.client.resource.property.ServerRegiest;
import com.lxd.client.view.control.UiSingleton;
import com.lxd.client.view.handle.RegHandle;
import com.lxd.protobuf.msg.Msg.Msg_;
import com.lxd.protobuf.msg.request.Request.Request_;
import com.lxd.protobuf.msg.request.user.Register.Register_;
import com.lxd.protobuf.msg.request.user.User.User_;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.SystemColor;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Component;


/**
 * 注册界面
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年9月18日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class RegPanel extends JPanel implements RegHandle{
	private static final long serialVersionUID = 2599791884854672419L;	
	///< 用户名输入框
	private JTextField field_name;
	///< 密码输入框
	private JPasswordField pwd;
	///< 重复密码输入框
	private JPasswordField pwd_again;
	
	///< 注册按钮
	private JButton reg_button;
	///< 返回按钮
	private JButton bak_button;
	
	///< 密码检查标签
	private JLabel pwd_again_check;
	
	///< 用户名检查标志
	private boolean name_bool = false;
	///< 密码检查标志
	private boolean pwd_bool = false;
	

	public RegPanel() {
	    ///< 初始化
		init();
		///< 添加监听器
		setListener();
	}
	
	/*
	 *  初始化
	 */
	private void init() {
		JPanel panel = new JPanel() {			
			private static final long serialVersionUID = -3861574625311864082L;
			
			@Override
			protected void paintComponent(final Graphics g) {
			    ///< 绘制背景透明渐变
				Graphics2D g2d = (Graphics2D)g;
				GradientPaint gPaint = new GradientPaint(0, 0, new Color(1.0f, 1.0f, 1.0f, 0.0f), 0, getHeight(), new Color(0.0f, 0.0f, 0.0f, 1.0f));						
							
				g2d.setPaint(gPaint);
				g2d.fillRect(0, 0, getWidth(), getHeight());									
			}
			
		};
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 5));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGap(95)
		            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(215, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGap(19)
		            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(139, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("注册");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 22));
		
		JLabel label_name = new JLabel("");
		label_name.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/user.png")));	
		
		JLabel label_pwd = new JLabel("");
		label_pwd.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/pwd.png")));
		
		JLabel lable_pwd_again = new JLabel("");
		lable_pwd_again.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/pwd.png")));	
		
		
		field_name = new JTextField();
		field_name.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		field_name.setColumns(10);	
		
		
		pwd = new JPasswordField();
		pwd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		
		pwd_again = new JPasswordField();
		pwd_again.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));		
		
		reg_button = new JButton("注册");
		reg_button.setEnabled(false);
		reg_button.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		
		bak_button = new JButton("返回");
		bak_button.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
				
		pwd_again_check = new JLabel(" ");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
		    gl_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel.createSequentialGroup()
		            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addGap(143)
		                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addGap(42)
		                    .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
		                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
		                            .addGroup(gl_panel.createSequentialGroup()
		                                .addComponent(label_name)
		                                .addGap(20)
		                                .addComponent(field_name, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
		                            .addComponent(reg_button, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
		                        .addGroup(gl_panel.createSequentialGroup()
		                            .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
		                                .addGroup(gl_panel.createSequentialGroup()
		                                    .addComponent(lable_pwd_again)
		                                    .addGap(22))
		                                .addGroup(gl_panel.createSequentialGroup()
		                                    .addComponent(label_pwd)
		                                    .addGap(18)))
		                            .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
		                                .addComponent(pwd_again, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(pwd, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))))
		                    .addGap(4)
		                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
		                        .addComponent(bak_button, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(pwd_again_check, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))))
		            .addContainerGap())
		);
		gl_panel.setVerticalGroup(
		    gl_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
		                    .addGap(18)
		                    .addComponent(field_name, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.RELATED))
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addComponent(label_name)
		                    .addGap(2)))
		            .addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
		            .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
		                .addComponent(pwd, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
		                .addComponent(label_pwd))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(pwd_again, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addGap(30)
		                    .addComponent(pwd_again_check, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(lable_pwd_again)))
		            .addGap(18)
		            .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
		                .addComponent(reg_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(bak_button, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
		            .addContainerGap())
		);
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {pwd, pwd_again, field_name});
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
	
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);		
			
		ImageIcon image = new ImageIcon(ImageIO.class.getResource("/com/lxd/client/view/res/login_bk.png"));
		g.drawImage(image.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);					
	}
	
	private void setBakButtonListener() {
		bak_button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNull();
				UiSingleton.getSingleton().getLayout().show(
						UiSingleton.getSingleton().getBamFrame().getContentPane(), "login");		
			}
		});
	}
		
	private void setRegButtonListener() {
		reg_button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
                String name = field_name.getText();			
                String pwd_string = new String(pwd.getPassword());
			    Msg_.Builder msg = Msg_.newBuilder();
		        Request_.Builder request = Request_.newBuilder();
		        User_.Builder user = User_.newBuilder();
		        Register_.Builder register = Register_.newBuilder();
		        register.setUserName(name);
		        register.setUserPwd(pwd_string);
		        user.setRegister(register);
		        request.setUser(user);
		        msg.setRequest(request);
		        msg.setJobId(-1L);
		        ClientResource.getSingleton().submitRequest(new RequestPackage(msg.build(), new ServerRegiest(name)));                
				setNull();
			}
		});
	}
	
	private void setPwdListener() {
		pwd.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String pwdString = new String(pwd.getPassword());
				String pwdAgain = new String(pwd_again.getPassword());
				
				if (!pwdString.equals(pwdAgain)) {
					pwd.setForeground(Color.RED);
					pwd_again.setForeground(Color.RED);
					
					pwd_again_check.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/error.png")));
					check("pwd", false);
				} else if (pwdString.equals("")) {
					pwd.setForeground(Color.BLUE);
					pwd_again.setForeground(Color.BLUE);
					
					pwd_again_check.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/error.png")));
					check("pwd", false);
				} else {
					pwd.setForeground(Color.BLACK);
					pwd_again.setForeground(Color.BLACK);
					
					pwd_again_check.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/ok.png")));
					check("pwd", true);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {				
			}
		});
	}
	
	private void setPwdAgainListener() {
		pwd_again.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String pwdString = new String(pwd.getPassword());
				String pwdAgain = new String(pwd_again.getPassword());
				
				if (!pwdString.equals(pwdAgain)) {
					pwd.setForeground(Color.RED);
					pwd_again.setForeground(Color.RED);
					
					pwd_again_check.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/error.png")));
					check("pwd", false);
				} else if (pwdString.equals("")) {
					pwd.setForeground(Color.BLUE);
					pwd_again.setForeground(Color.BLUE);
					
					pwd_again_check.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/error.png")));
					check("pwd", false);
				} else {
					pwd.setForeground(Color.BLACK);
					pwd_again.setForeground(Color.BLACK);
					
					pwd_again_check.setIcon(new ImageIcon(RegPanel.class.getResource("/com/lxd/client/view/res/ok.png")));
					check("pwd", true);
				}				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}	
	
	private void setFieldNameListener() {
			field_name.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String name = field_name.getText();
				if (!name.matches("^[a-zA-z][a-zA-Z0-9_]{2,9}$")) {
					check("name", false);
					EventQueue.invokeLater(new Runnable() {                        
                        @Override
                        public void run() {
                            field_name.setForeground(Color.RED);                         
                        }
                    });
				} else {
					check("name", true);
					EventQueue.invokeLater(new Runnable() {                        
                        @Override
                        public void run() {
                            field_name.setForeground(Color.BLACK);                           
                        }
                    });
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}
	
	private void setListener() {	
		setBakButtonListener();
		setRegButtonListener();
		setPwdListener();
		setPwdAgainListener();
		setFieldNameListener();	
	}	
	
	private void check(String item, boolean value) {
		switch (item) {
		case "name" : 
			name_bool = value;
			break;
		case "pwd" :
			pwd_bool = value;
			break;
		default:
			System.out.println("check 类型错误");
			break;
		}
		
		if (name_bool && pwd_bool) {
			reg_button.setEnabled(true);
		} else {
			reg_button.setEnabled(false);
		}
	}
	
	private void setNull() {
		///< 输入框置空
		field_name.setText("");
		pwd.setText("");
		pwd_again.setText("");		
		
		///< 状态置空
		pwd_again_check.setText("");
		
		///< 标识符置空
		name_bool = false;
		pwd_bool = false;
		
		///< 按钮设置为无效
		reg_button.setEnabled(false);
	}	

	@Override
	public void regSuccess() {
	    JOptionPane.showMessageDialog(null,"注册成功" , "信息", JOptionPane.INFORMATION_MESSAGE);  
	    ///< 跳转到登陆界面
	    UiSingleton.getSingleton().getLayout().show(
	                                                UiSingleton.getSingleton().getBamFrame().getContentPane(), "login");    
	}

	@Override
	public void regFail(String msg) {
		JOptionPane.showMessageDialog(null,"失败原因: " + msg ,"注册失败",JOptionPane.ERROR_MESSAGE);		
	}
}
