package baokandingyue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.*;

import baokandingyue.Login.JpanelBack;

public class Login extends JFrame implements ActionListener{
	private static final String USERNAME = "root";
	private static final String PASSWORD = "666";
	private static final String url="jdbc:mysql://127.0.0.1:3306/qw";
	private static final String driverClassName ="com.mysql.jdbc.Driver";
    private JPanel panel1=new JPanel();
    private JPanel panel2=new JPanel();
    private JPanel panel3=new JPanel();
    private JPanel panel4=new JPanel();
    private JLabel lab1=new JLabel("欢迎使用报刊管理系统！");
    private JLabel lab2=new JLabel("用户名",JLabel.CENTER);
    private JLabel lab3=new JLabel("密  码",JLabel.CENTER);
    private JTextField t1=new JTextField(15);
    private JPasswordField t2=new JPasswordField(15);
    private JButton b1=new JButton("进入");
    private JButton b2=new JButton("退出");
    private Font ft=new Font("隶书",Font.BOLD,40);
    private Font ft1=new Font("宋体",Font.PLAIN,30);
    private Font ft2=new Font("黑体",Font.PLAIN,15);
    static final String sys_id="admin";
    static final String sys_password="123456";
    static Connection conn;
    static Statement stmt;
    static ResultSet rSet;
    
    public Login(){
		JpanelBack panel = new JpanelBack();
		panel.setLayout(new BorderLayout());
		lab1.setFont(ft);
		lab2.setFont(ft1);
		t1.setFont(ft1);
		lab3.setFont(ft1);
		t2.setFont(ft1);
		b1.setFont(ft2);
		b1.addActionListener(this);
		b2.setFont(ft2);
		b2.addActionListener(this);
		panel4.setLayout(new GridLayout(4,1));
		panel4.setOpaque(false);
        panel4.add(lab1);
        panel1.add(lab2);panel1.add(t1);panel1.setOpaque(false);
		panel2.add(lab3);panel2.add(t2);panel2.setOpaque(false);
		panel3.add(b1);panel3.add(b2);panel3.setOpaque(false);
		panel4.add(panel1);
		panel4.add(panel2);
		panel4.add(panel3);
		panel.add(panel4,BorderLayout.EAST);
        add(panel);
		setTitle("报刊管理系统");
        setSize(800,600);
       	setVisible(true);
         //得到屏幕信息以便使框架居中显示
        Dimension screeSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screeWidth=screeSize.width;
        int screeHeight=screeSize.height;
        //得到框架的大小信息
       	Dimension frameSize=this.getSize();
        int x=(screeWidth-frameSize.width)/2;
   		int y=(screeHeight-frameSize.height)/2;
       	this.setLocation(x, y);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
    	int i;
    	if(e.getSource()==b1){
			String id=t1.getText();
			String password=t2.getText();
			if(id.equals("")||password.equals(""))
				JOptionPane.showMessageDialog(null,"用户名或密码不能为空！！");
			else{
				if(checkUser(id,password)){
					f.setVisible(false);
					Magazine f1=new Magazine("报刊管理系统");
					f1.setTitle("报刊管理系统");
					f1.setVisible(true);
					f1.setBounds(10, 10, 800, 500);}
				else
					JOptionPane.showMessageDialog(null,"用户名或密码错误！！");
			}
    	}
    	if(e.getSource()==b2){
    		i=JOptionPane.showConfirmDialog(null,"确认退出<报刊管理系统>？",
    			"报刊管理系统",JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE);
    		if(i==JOptionPane.YES_OPTION)
    			System.exit(0);
           	else	return;
    	}
    }
    public boolean checkUser(String id ,String password){
    	try{
			String driverClassName = "com.mysql.jdbc.Driver";
			Class.forName(driverClassName);
			String url = "jdbc:mysql://127.0.0.1:3306/qw";
			Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
			String sql ="select * from T_admin ";
			rSet=stmt.executeQuery(sql);
			while (rSet.next()){
				System.out.print(rSet.getString(1)+rSet.getString(2));
            	if (rSet.getString(1).equals(id)&& rSet.getString(2).equals(password))
            	{	
            		return true;	
            		}
            	
      			else	
      				return false;
    	}	rSet.close();
    	}	
    	catch(Exception sqle){
    		System.err.println(sqle);
    		return false;
    	}
    	return false;
	}
	class JpanelBack extends JPanel {
		BufferedImage  img =null;
		public JpanelBack (){
			try{	
				img = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\aa.jpg"));
			}
   		 	catch (IOException e){
		 			e.printStackTrace();
   		 		} 
		}
		protected void paintComponent(Graphics g){
			super.paintComponent(g); 
  		  	g.drawImage(img, 0,0, getWidth(),getHeight(),this); 
   		}
  	 }
  	 public static Login f=new Login();
  	 public static void main(String []args){
		f.setBounds(10, 10, 600, 500);
		f.setVisible(true);
	}
}
