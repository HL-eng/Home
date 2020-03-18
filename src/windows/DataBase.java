package windows;
import java.sql.*;

import javax.swing.JOptionPane;

public class DataBase {
	 public static String uri="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sch_109";
	 public static String user="sa";
	 public static String password="lilei123";       
	 public static String base="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动
	 
	 public static String tableU="U";//用户表
	 public DataBase() {}
	 public DataBase(String uri,String user,String password,String base) {
		 this.uri=uri;
		 this.user=user;
		 this.password=password;
		 this.base=base;
	 }
	 
	 public void setTableU(String U) {
		 this.tableU=U;
	 }
	 
	/*向用户表添加信息*/
	 public void writeU(String name,String pw) {	//用户名，密码
	     try{  
	    	 Class.forName(base);
		 }
		 catch(Exception e){} 
		 Connection con;
		 Statement sql; 
		 try{  
		      con=DriverManager.getConnection(uri,user,password);
		      sql=con.createStatement();
		      String condition="insert into U values("+"'"+name+"'"+","+"'"+pw+"'"+")";
		      sql.executeUpdate(condition);
		      con.close();
		    }
		    catch(SQLException e){
		    	JOptionPane.showMessageDialog(null, e);
		    }		 
	 }
	 /*根据用户名查找密码,返回密码*/
	  public String readU(String name) {	//用户名
	     try{  
	    	 Class.forName(base);
		 }
		 catch(Exception e){} 
		 Connection con;
		 Statement sql;
		 ResultSet rs;
		 String pw="";
		 try{  
		      con=DriverManager.getConnection(uri,user,password);
		      sql=con.createStatement();
		      String condition="select password from U where name="+"'"+name+"'";
		          rs=sql.executeQuery(condition);
		          if(rs.next()) {
		        	  pw=rs.getString(1).trim();
		          }
		          con.close();
		    }
		    catch(SQLException e){
		    	JOptionPane.showMessageDialog(null, e);
		    }
		 return pw;
	 }		
}
