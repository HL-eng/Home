package windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Login extends JFrame{
		private static final long serialVersionUID = 1L;
		public Login(){
			//getContentPane().setBackground(Color.BLUE);//设置背景颜色
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置窗口关闭方式
			setTitle("登录窗口");//为窗口设置标题
			setBounds(550, 220, 700, 460);//设置窗口位置以及大小
			Container cp=getContentPane();//为窗口添加容器
			cp.setLayout(null); //取消窗口的绝对布局
			JLabel jl_welcome=new JLabel("大学分数线查询");
			jl_welcome.setBounds(220, 20, 400, 50);
			jl_welcome.setFont(new Font("宋体",Font.BOLD,30));
			JLabel jl=new JLabel("用户名");//设置标签“用户名”
			jl.setBounds(150, 120, 200, 30);
			jl.setFont(new Font("宋体",Font.ITALIC,25));
			final JTextField name=new JTextField();//建立一个文本框
			name.setBounds(260, 120, 250, 30);
			JLabel jl2=new JLabel("密  码");
			jl2.setBounds(150, 200, 200, 30);
			jl2.setFont(new Font("宋体",Font.ITALIC,25));
			final JTextField password=new JTextField();//密码文本框
			password.setBounds(260, 200, 250, 30);
			//添加文本框，标签到容器上
			cp.add(jl_welcome);
			cp.add(jl);
			cp.add(name);
			cp.add(jl2);
			cp.add(password);
			JButton jb=new JButton("登录");//按钮名称
			
			
			jb.addActionListener(new ActionListener(){       //学生按钮登录事件监听
				public void actionPerformed(ActionEvent arg0) {
					String n=name.getText().trim();
					String p=password.getText().trim();
					if(n.length()==0||p.length()==0){
						JOptionPane.showMessageDialog(null, "用户名密码不能为空");
						return;
					}
					//这个地方要用判断用户名，密码是否正确方法
					try {
						DataBase d=new DataBase();
						String pw=d.readU(n);
						if(pw.equals(p)) {
							JOptionPane.showMessageDialog(null, "用户名密码输入正确");
							//初始化查询窗口
							new Search();
							//隐藏登录窗口
							setVisible(false);
						}
						else if(pw.equals("")){
							JOptionPane.showMessageDialog(null, "用户名不存在");
						}
						else {
							JOptionPane.showMessageDialog(null, "密码错误");
						}
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			});
			//注册按钮事件监听
			JButton jb2=new JButton("注册");
			jb2.addActionListener(new ActionListener(){  //内部类
				public void actionPerformed(ActionEvent arg0) {
					new Logon();
					setVisible(false);
				}
			});
			jb.setFont(new Font("宋体",Font.BOLD,15));
			jb.setBounds(150, 300, 120, 40);
			cp.add(jb);
			jb2.setFont(new Font("宋体",Font.BOLD,15));
			jb2.setBounds(450, 300, 120, 40);
			cp.add(jb2);
			setVisible(true);//使窗口可见
		}
		
		public static void main(String[] args) {
			new Login();

		}
}
