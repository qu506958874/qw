package qww;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBC {
	
	public static void main(String[] args) {
		
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			long start =System.currentTimeMillis();
			//�������ӣ����Ӷ����ڲ���ʵ������Scoket������һ��Զ�����ӣ��ȽϺ�ʱ������Connection��������һ��Ҫ�㣡��
			//���������У�Ϊ�����Ч�ʣ�����ʹ�����ӵ����������Ӷ���
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/t1","root", "123");
			long end = System.currentTimeMillis();
			
			System.out.println(conn);//���������ǹ��ɹ�
			System.out.println("�������ӣ���ʱ��"+(end-start)+"ms����");//�������Ӻ�ʱ����
			
			Statement stmt=conn.createStatement();
			
			String sql="insert into tb5(id,name,age)values(5,'kaifeng',22)";
			stmt.execute(sql);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

	}
}