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

public class Alter_Mag extends JFrame implements ActionListener{
	private static final String USERNAME = "root";
	private static final String PASSWORD = "666";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/qw";
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private JLabel old_Mag_id=new JLabel("旧报刊ID：",JLabel.CENTER);
	private JLabel new_Mag_id=new JLabel("新报刊ID：",JLabel.CENTER);
	private JLabel new_Mag_name=new JLabel("新报刊名：",JLabel.CENTER);
	private JLabel new_Mag_press=new JLabel("出版社：",JLabel.CENTER);
	private JLabel new_Mag_pressdate=new JLabel("出版日期：",JLabel.CENTER);
	private JLabel new_Mag_price=new JLabel("价格：",JLabel.CENTER);
	private JLabel new_Mag_comment=new JLabel("备注：",JLabel.CENTER);
	
	private JTextField T_old_Mag_id =new JTextField(15);
	private JTextField T_new_Mag_id =new JTextField(15);
	private JTextField T_new_Mag_name =new JTextField(15);
	private JTextField T_new_Mag_press =new JTextField(15);
	private JTextField T_new_Mag_pressdate =new JTextField(15);
	private JTextField T_new_Mag_price =new JTextField(15);
	private JTextField T_new_Mag_comment =new JTextField(15);
	
	private JPanel p1=new JPanel();
	private JPanel p2=new JPanel();
	private JPanel p3=new JPanel();
	private JPanel p4=new JPanel();
	private JPanel p5=new JPanel();
	private JPanel p6=new JPanel();
	private JPanel p7=new JPanel();
	private JPanel p8=new JPanel();
	
	private JButton b1=new JButton("确认");
	private JButton b2=new JButton("取消");
	
	public Alter_Mag(){
		Container c = getContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);// 设置关闭窗口
		setLayout(new GridLayout(8,2));
		
		p1.add(old_Mag_id);p1.add(T_old_Mag_id);
		p2.add(new_Mag_id);p2.add(T_new_Mag_id);
		p3.add(new_Mag_name);p3.add(T_new_Mag_name);
		p4.add(new_Mag_press);p4.add(T_new_Mag_press);
		p5.add(new_Mag_pressdate);p5.add(T_new_Mag_pressdate);
		p6.add(new_Mag_price);p6.add(T_new_Mag_price);
		p7.add(new_Mag_comment);p7.add(T_new_Mag_comment);
		b1.addActionListener(this);
		b2.addActionListener(this);
		p8.add(b1);p8.add(b2);
		
		c.add(p1);c.add(p2);
		c.add(p3);c.add(p4);
		c.add(p5);c.add(p6);
		c.add(p7);c.add(p8);
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
      			String sql = "update T_mags set MAG_ID = "+T_new_Mag_id.getText()
     	 				+",MAG_NAME = '"+T_new_Mag_name.getText()+"' , press = '"+T_new_Mag_press.getText()
     	 				+"' , pressdate = '"+T_new_Mag_pressdate.getText()+"' , price = '"+T_new_Mag_price.getText()
     	 				+"',commen='"+T_new_Mag_comment.getText()
     	 				+"'  where mag_id = "+T_old_Mag_id.getText();
      			    stmt.executeQuery(sql);
      				JOptionPane.showMessageDialog(null,"修改成功！！");
    		}
    		catch(Exception sqle){
    			JOptionPane.showMessageDialog(null,"修改失败！！");
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
		Alter_Mag f=new Alter_Mag();
	}
}
