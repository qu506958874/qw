package qww;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBC {
	
	public static void main(String[] args) {
		
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			long start =System.currentTimeMillis();
			//建立连接（连接对象内部其实包含了Scoket对象，是一个远程连接，比较耗时！这是Connection对象管理的一个要点！）
			//真正开发中，为了提高效率，都会使用连接地来管理连接对象
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/t1","root", "123");
			long end = System.currentTimeMillis();
			
			System.out.println(conn);//测试连接是够成功
			System.out.println("建立连接，耗时："+(end-start)+"ms毫秒");//建立连接耗时多少
			
			Statement stmt=conn.createStatement();
			
			String sql="insert into tb5(id,name,age)values(5,'kaifeng',22)";
			stmt.execute(sql);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}