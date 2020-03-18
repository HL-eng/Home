package windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
public class Search extends JFrame{
	public  static JTextField JTFsearch=new JTextField();	//搜索文本框
	public  static JTextArea JTAresult=new JTextArea();		//结果文本区
	public  static JTextArea recommend=new JTextArea();	//推荐文本区
	
	public Search(){
		getContentPane().setBackground(Color.CYAN);//设置背景颜色
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置窗口关闭方式
		setTitle("搜索窗口");//为窗口设置标题
		setBounds(250, 25, 1100, 900);//设置窗口位置以及大小
		Container cp=getContentPane();//为窗口添加容器
		cp.setLayout(null); //取消窗口的绝对布局
		JLabel jl_welcome=new JLabel("大学分数线查询");
		jl_welcome.setBounds(220, 20, 400, 50);
		jl_welcome.setFont(new Font("宋体",Font.BOLD,30));
		
		/*搜索文本框*/
		JLabel jl=new JLabel("搜索条件");//设置标签“搜索条件”
		jl.setBounds(50, 780, 100, 30);
		jl.setFont(new Font("宋体",Font.ITALIC,25));
		JTFsearch.setBounds(160, 780, 700, 50);
		cp.add(JTFsearch);
		
		/*结果文本区*/
		JLabel jl2=new JLabel("搜索结果");
		jl2.setBounds(700, 100, 200, 30);
		jl2.setFont(new Font("宋体",Font.ITALIC,25));
	    JTAresult.setBounds(600, 150, 400, 600);
	    JTAresult.setLineWrap(true);        //激活自动换行功能 
	    JTAresult.setWrapStyleWord(true);   // 激活断行不断字功能
	    JTAresult.setEditable(true);
	    JScrollPane resultSP = new JScrollPane(JTAresult);
	    resultSP.setBounds(600, 150, 400, 600);
	    resultSP.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
	    resultSP.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	    cp.add(resultSP);
	    
	    /*推荐文本区*/
	    JLabel jl3=new JLabel("猜您想看");
		jl3.setBounds(200, 100, 200, 30);
		jl3.setFont(new Font("宋体",Font.ITALIC,25));
		recommend.setBounds(100, 150, 400, 600);
		JScrollPane recommendSP = new JScrollPane(recommend);
		recommendSP.setBounds(100, 150, 400, 600);
		recommendSP.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		recommendSP.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		cp.add(recommendSP);
		//添加文本框，标签到容器上
		cp.add(jl_welcome);
		cp.add(jl);
		cp.add(jl2);
		cp.add(jl3);
		
		/*确认按钮*/
		JButton JBsure=new JButton("搜索");//按钮名称
		JBsure.setFont(new Font("宋体",Font.BOLD,15));
		JBsure.setBounds(950, 780, 100, 50);
		cp.add(JBsure);
		JBsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String goal=JTFsearch.getText().trim();
				M2(goal);//调用输出函数，goal是搜索条件。
			}
		});
		setVisible(true);//使窗口可见
	}
	
	/*输出函数，goal是搜索条件*/
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
          JTAresult.append("年份是：" + year+"\r\n");
          int nian = target.indexOf("年");
          int de = target.indexOf("的");
          /*获取年与的 之间的约束条件，可能由如下格式
           * xx学校
           * xx省xx学校
           * xx省xx专业
           * xx专业*/
          String control = target.substring(nian + 1, de);
          JTAresult.append("完整约束：" + control);
         
          /*control入队列*/
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

              //添加元素
              All[i] = temp;
              if (temp.equals("学")) {
                  if (All[i - 1].equals("大")) {
                      /*已经扫描到学校名*/
                      key = i;
                      for (int j = 0; j <= i; j++) {
                          school[j] = All[j];
                      }
                  }


              } else if (temp.equals("院")) {

                  if (All[i - 1].equals("学")) {
                      /*已经扫描到学校名*/
                      for (int j = 0; j <= i; j++) {
                          school[j] = All[j];
                      }
                      key = i;
                  }

              } else if (temp.equals("业")) {
                  for (int k = 0; k <= i - key - 3; k++) {
                      department[k] = All[key + k + 1];/*最开始使用i-key+k+1导致逻辑错误，导致无法修复*/
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
          JTAresult.append("大学名是：" + sna);
          JTAresult.append("专业名是：" + dna);
          JTAresult.append("调用对应的sql模板");
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
