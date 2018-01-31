package baokandingyue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;
import java.awt.*;

class Alter_User extends JFrame implements ActionListener{
	private static final String USERNAME = "root";
	private static final String PASSWORD = "666";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/qw";
	private static final String driverClassName = "com.mysql.jdbc.Driver";
private JLabel old_User_id=new JLabel("用户ID：",JLabel.CENTER);
private JLabel new_User_name=new JLabel("新用户名：",JLabel.CENTER);
private JLabel new_User_sex=new JLabel("性别：",JLabel.CENTER);
private JLabel new_User_dept=new JLabel("部门：",JLabel.CENTER);
private JLabel new_User_address=new JLabel("地址：",JLabel.CENTER);

private JTextField T_User_id =new JTextField(15);
private JTextField T_new_User_name =new JTextField(15);
private JTextField T_new_User_sex =new JTextField(15);
private JTextField T_new_User_dept =new JTextField(15);
private JTextField T_new_User_address =new JTextField(15);

private JPanel p1=new JPanel();
private JPanel p2=new JPanel();
private JPanel p3=new JPanel();
private JPanel p4=new JPanel();
private JPanel p5=new JPanel();
private JPanel p6=new JPanel();
private JPanel p7=new JPanel();

private JButton b1=new JButton("确认");
private JButton b2=new JButton("取消");

public Alter_User(){
	Container c = getContentPane();
	setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置关闭窗口
	setLayout(new GridLayout(7,2));
	
	p1.add(old_User_id);p1.add(T_User_id);
	p3.add(new_User_name);p3.add(T_new_User_name);
	p4.add(new_User_sex);p4.add(T_new_User_sex);
	p5.add(new_User_dept);p5.add(T_new_User_dept);
	p6.add(new_User_address);p6.add(T_new_User_address);
	b1.addActionListener(this);
	b2.addActionListener(this);
	p7.add(b1);p7.add(b2);
	
	c.add(p1);
	c.add(p3);c.add(p4);
	c.add(p5);c.add(p6);
	c.add(p7);
	setVisible(true);
	setBounds(20,20,300,400);
	addWindowListener(new WL());
}

public void actionPerformed(ActionEvent e){
	if(e.getSource()==b1){
		try{
			Class.forName(driverClassName);
	     	Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
		    Statement stmt = con.createStatement();
  			String sql = "update T_users set user_id ="+Integer.parseInt(T_User_id.getText())+",user_name='"+T_new_User_name.getText()+"',sex='"+T_new_User_sex.getText()+"',dept='"+T_new_User_dept.getText()+"',address='"+T_new_User_address.getText()+"' where user_id ="+Integer.parseInt(T_User_id.getText())+"";
  				stmt.execute(sql);
  			//	executeQuery(sql);//cuo 
  				JOptionPane.showMessageDialog(null,"修改成功！！");
  			
  		
  				
		}
		catch(Exception sqle){
			JOptionPane.showMessageDialog(null,"修改失败！");
			System.err.println(sqle);
		}
	}
	if(e.getSource()==b2){
		this.setVisible(false);
	}
}

class WL extends WindowAdapter{
	public void windowClosing(WindowEvent we){
		setVisible(false);
	}
}

public static void main(String []args){
	Alter_User f=new Alter_User();
}
}
