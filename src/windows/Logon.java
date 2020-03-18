package windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;


public class Logon extends JFrame{
		private static final long serialVersionUID = 1L;
		public Logon(){
			//getContentPane().setBackground(Color.BLUE);//���ñ�����ɫ
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ô��ڹرշ�ʽ
			setTitle("��¼����");//Ϊ�������ñ���
			setBounds(550, 220, 700, 460);//���ô���λ���Լ���С
			Container cp=getContentPane();//Ϊ�����������
			cp.setLayout(null); //ȡ�����ڵľ��Բ���
			JLabel jl_welcome=new JLabel("��ѧ�����߲�ѯ");
			jl_welcome.setBounds(220, 20, 400, 50);
			jl_welcome.setFont(new Font("����",Font.BOLD,30));
			JLabel jl=new JLabel("�û���");//���ñ�ǩ���û�����
			jl.setBounds(150, 120, 200, 30);
			jl.setFont(new Font("����",Font.ITALIC,25));
			final JTextField name=new JTextField();//����һ���ı���
			name.setBounds(260, 120, 250, 30);
			JLabel jl2=new JLabel("��  ��");
			jl2.setBounds(150, 200, 200, 30);
			jl2.setFont(new Font("����",Font.ITALIC,25));
			final JTextField password=new JTextField();//�����ı���
			password.setBounds(260, 200, 250, 30);
			//����ı��򣬱�ǩ��������
			cp.add(jl_welcome);
			cp.add(jl);
			cp.add(name);
			cp.add(jl2);
			cp.add(password);
			
			//���ذ�ť�¼�����
            JButton jb=new JButton("����");//��ť����
			jb.addActionListener(new ActionListener(){       //ѧ����ť��¼�¼�����
				public void actionPerformed(ActionEvent arg0) {
					new Login();
					setVisible(false);
				}
			});
			//ȷ�ϰ�ť�¼�����
			JButton jb2=new JButton("ȷ��");
			jb2.addActionListener(new ActionListener(){  //�ڲ���
				public void actionPerformed(ActionEvent arg0) {
					String n=name.getText().trim();
					String p=password.getText().trim();
					if(n.length()==0||p.trim().length()==0){
						JOptionPane.showMessageDialog(null, "�û������벻��Ϊ��");
						return;
					}
					//����ط�Ҫ���ж��û����Ƿ��Ѵ���
					try {
						DataBase d=new DataBase();
						String pw=d.readU(n);
						if(pw.equals("")) {
							d.writeU(n, p);
							new Login();
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null, "�û����Ѵ���");
						}
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			});
			jb.setFont(new Font("����",Font.BOLD,15));
			jb.setBounds(150, 300, 120, 40);
			cp.add(jb);
			jb2.setFont(new Font("����",Font.BOLD,15));
			jb2.setBounds(450, 300, 120, 40);
			cp.add(jb2);
			setVisible(true);//ʹ���ڿɼ�
		}
		
		public static void main(String[] args) {
			new Login();

		}
}
