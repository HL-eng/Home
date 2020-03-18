package windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
public class Search extends JFrame{
	public  static JTextField JTFsearch=new JTextField();	//�����ı���
	public  static JTextArea JTAresult=new JTextArea();		//����ı���
	public  static JTextArea recommend=new JTextArea();	//�Ƽ��ı���
	
	public Search(){
		getContentPane().setBackground(Color.CYAN);//���ñ�����ɫ
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ô��ڹرշ�ʽ
		setTitle("��������");//Ϊ�������ñ���
		setBounds(250, 25, 1100, 900);//���ô���λ���Լ���С
		Container cp=getContentPane();//Ϊ�����������
		cp.setLayout(null); //ȡ�����ڵľ��Բ���
		JLabel jl_welcome=new JLabel("��ѧ�����߲�ѯ");
		jl_welcome.setBounds(220, 20, 400, 50);
		jl_welcome.setFont(new Font("����",Font.BOLD,30));
		
		/*�����ı���*/
		JLabel jl=new JLabel("��������");//���ñ�ǩ������������
		jl.setBounds(50, 780, 100, 30);
		jl.setFont(new Font("����",Font.ITALIC,25));
		JTFsearch.setBounds(160, 780, 700, 50);
		cp.add(JTFsearch);
		
		/*����ı���*/
		JLabel jl2=new JLabel("�������");
		jl2.setBounds(700, 100, 200, 30);
		jl2.setFont(new Font("����",Font.ITALIC,25));
	    JTAresult.setBounds(600, 150, 400, 600);
	    JTAresult.setLineWrap(true);        //�����Զ����й��� 
	    JTAresult.setWrapStyleWord(true);   // ������в����ֹ���
	    JTAresult.setEditable(true);
	    JScrollPane resultSP = new JScrollPane(JTAresult);
	    resultSP.setBounds(600, 150, 400, 600);
	    resultSP.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
	    resultSP.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	    cp.add(resultSP);
	    
	    /*�Ƽ��ı���*/
	    JLabel jl3=new JLabel("�����뿴");
		jl3.setBounds(200, 100, 200, 30);
		jl3.setFont(new Font("����",Font.ITALIC,25));
		recommend.setBounds(100, 150, 400, 600);
		JScrollPane recommendSP = new JScrollPane(recommend);
		recommendSP.setBounds(100, 150, 400, 600);
		recommendSP.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		recommendSP.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		cp.add(recommendSP);
		//����ı��򣬱�ǩ��������
		cp.add(jl_welcome);
		cp.add(jl);
		cp.add(jl2);
		cp.add(jl3);
		
		/*ȷ�ϰ�ť*/
		JButton JBsure=new JButton("����");//��ť����
		JBsure.setFont(new Font("����",Font.BOLD,15));
		JBsure.setBounds(950, 780, 100, 50);
		cp.add(JBsure);
		JBsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String goal=JTFsearch.getText().trim();
				M2(goal);//�������������goal������������
			}
		});
		setVisible(true);//ʹ���ڿɼ�
	}
	
	/*���������goal����������*/
	public static void M2(String goal) {
//      Scanner s1 = new Scanner(System.in);
//      String target = s1.nextLine();

      String target = goal.trim();
      String year = "";
      if (target != null) {
          for (int i = 0; i < target.length(); i++) {
              if (target.charAt(i) >= 48 && target.charAt(i) <= 57) {
                  year += target.charAt(i);
              }
          }
          JTAresult.append("����ǣ�" + year+"\r\n");
          int nian = target.indexOf("��");
          int de = target.indexOf("��");
          /*��ȡ����� ֮���Լ�����������������¸�ʽ
           * xxѧУ
           * xxʡxxѧУ
           * xxʡxxרҵ
           * xxרҵ*/
          String control = target.substring(nian + 1, de);
          JTAresult.append("����Լ����" + control);
         
          /*control�����*/
          String[] school = new String[10];
          String[] department = new String[15];
          String[] All = new String[50];
          for (int i = 0; i < 10; i++) {
              school[i] = " ";
          }
          for (int i = 0; i < 15; i++) {
              department[i] = " ";
          }


          int key = 0;
          for (int i = 0; i < control.length(); i++) {
              String temp = control.substring(i, i + 1);

              //���Ԫ��
              All[i] = temp;
              if (temp.equals("ѧ")) {
                  if (All[i - 1].equals("��")) {
                      /*�Ѿ�ɨ�赽ѧУ��*/
                      key = i;
                      for (int j = 0; j <= i; j++) {
                          school[j] = All[j];
                      }
                  }


              } else if (temp.equals("Ժ")) {

                  if (All[i - 1].equals("ѧ")) {
                      /*�Ѿ�ɨ�赽ѧУ��*/
                      for (int j = 0; j <= i; j++) {
                          school[j] = All[j];
                      }
                      key = i;
                  }

              } else if (temp.equals("ҵ")) {
                  for (int k = 0; k <= i - key - 3; k++) {
                      department[k] = All[key + k + 1];/*�ʼʹ��i-key+k+1�����߼����󣬵����޷��޸�*/
                  }
              }
          }
          String sna = "";
          for (int q = 0; q < 10; q++) {
              sna += school[q];
          }
          String dna = "";
          for (int j = 0; j < 15; j++) {
              dna += department[j];
          }
          sna = sna.trim();
          dna = dna.trim();
          JTAresult.append("��ѧ���ǣ�" + sna);
          JTAresult.append("רҵ���ǣ�" + dna);
          JTAresult.append("���ö�Ӧ��sqlģ��");
      }


      String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";

      try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");
          Statement stmt = (Statement) conn.createStatement();
          stmt.execute("select * from message");

          ResultSet resultset=stmt.executeQuery("select * from message");
          while (resultset.next()) {
              System.out.println(resultset.getString(1));
              System.out.println(resultset.getString(2));
              System.out.println(resultset.getString(3));

          }

      }catch (Exception e)
      {
          e.printStackTrace();
      }
  }
	public static void main(String args[]) {
		new Search();
	}
}
