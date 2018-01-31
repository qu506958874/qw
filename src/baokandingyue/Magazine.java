package baokandingyue;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Magazine extends JFrame implements ActionListener {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "666";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/qw" ;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private Container c = getContentPane(); // ��ȡ���ݴ���

	private JMenuBar menuBar = new JMenuBar(); // �����˵���
	private JMenu Add = new JMenu("¼��"); // �����˵�
	private JMenu Order = new JMenu("����");
	private JMenu Select = new JMenu("��ѯ");
	private JMenu Count = new JMenu("ͳ��");
	private JMenu Alter = new JMenu("�޸�");
	private JMenu About = new JMenu("����");

	private JMenuItem Add_User = new JMenuItem("������Ա");// ����JMenu�˵���
	private JMenuItem Add_Mag = new JMenuItem("����");
	private JMenuItem Order_1 = new JMenuItem("����");
	private JMenuItem Select_1 = new JMenuItem("��ѯ");
	private JMenuItem Count_1 = new JMenuItem("ͳ��");
	private JMenuItem Alter_User = new JMenuItem("����Ա");
//	private JMenuItem Alter_Mag = new JMenuItem("������");
//	private JMenuItem Alter_Dept = new JMenuItem("������");
	private JMenuItem Alter_1 = new JMenuItem("�޸�");
	private JMenuItem About_1 = new JMenuItem("����");

	private JPanel Panel_Add_User = new JPanel();
	private JPanel Panel_Add_Mag = new JPanel();
	private JPanel Panel_Order = new JPanel();
	private JPanel Panel_Select = new JPanel();
	private JPanel Panel_Count = new JPanel();
	private JPanel Panel_Alter = new JPanel();
	private JPanel Panel_About = new JPanel();

	private JPanel BigPanel = new JPanel();

	private JLabel User_id = new JLabel("�û�ID", JLabel.CENTER);
	private JLabel User_name = new JLabel("����", JLabel.CENTER);
	private JLabel User_sex = new JLabel("�Ա�", JLabel.CENTER);
	private JLabel User_dept = new JLabel("����", JLabel.CENTER);
	private JLabel User_address = new JLabel("��ַ", JLabel.CENTER);
	private JLabel Mag_id = new JLabel("������", JLabel.CENTER);
	private JLabel Mag_name = new JLabel("��������", JLabel.CENTER);
	private JLabel Mag_press = new JLabel("������", JLabel.CENTER);
	private JLabel Mag_pressdate = new JLabel("��������", JLabel.CENTER);
	private JLabel Mag_price = new JLabel("�۸�", JLabel.CENTER);
	private JLabel Mag_comment = new JLabel("��ע", JLabel.CENTER);
	private JLabel Order_id = new JLabel("�������", JLabel.CENTER);
	private JTextArea About_me = new JTextArea("����������������������������������������������\n"
			+ "��                           �������Ĺ���ϵͳ                         ��\n" + "�ǩ�������������������������������������������\n"
			+ "��                                                                                    ��\n"
			+ "��                                                                                    ��\n"
			+ "��                               ��лʹ�ã���                             ��\n"
			+ "��                                                                                    ��\n"
			+ "��                                                                                    ��\n"
			+ "��                                                                                    ��\n"
			+ "����������������������������������������������", 20, 10);
	private JTextField T_Order_id = new JTextField(ran(), 15);
	private JTextField T_User_id = new JTextField(15);
	private JTextField T_User_name = new JTextField(15);
	private JTextField T_User_dept = new JTextField(15);
	private JTextField T_User_sex = new JTextField(15);
	private JTextField T_User_address = new JTextField(15);
	private JTextField T_Mag_id = new JTextField(15);
	private JTextField T_Mag_name = new JTextField(15);
	private JTextField T_Mag_press = new JTextField(15);
	private JTextField T_Mag_pressdate = new JTextField(15);
	private JTextField T_Mag_price = new JTextField(15);
	private JTextField T_Mag_comment = new JTextField(15);
	private JTextField SelectInformations = new JTextField(15);

	private JButton add_person = new JButton("¼��");
	private JButton add_mag = new JButton("¼��");
	private JButton B_order = new JButton("����");
	private JButton B_alter_user = new JButton("�޸���Ա");
	private JButton B_alter_mag = new JButton("�޸ı���");
	private JButton ok1 = new JButton("��ѯ");

	private String[] choiceItem = { "����Ա"  };
	private JComboBox choice = new JComboBox(choiceItem);
	private JComboBox choice1 = new JComboBox(choiceItem);

	String title[] = { "������", "�û�ID", "�û���", "�Ա�", "����", "��ַ", "����ID", "������", "������", "��������", "�۸�", "��ע" };

	JTable table; // ����table
	JScrollPane scroll; // ����������
	Vector vector = new Vector(); // ������������
	AbstractTableModel tm = new AbstractTableModel() { // ����ģ��
		public int getColumnCount() {
			return title.length;
		}

		public int getRowCount() {
			return vector.size();
		}

		public Object getValueAt(int row, int column) {// ȡ�õ�Ԫ���е�����
			if (!vector.isEmpty())
				return ((Vector) vector.elementAt(row)).elementAt(column);
			else
				return null;
		}

		public void setValueAt(Object value, int row, int column) {
		}

		public String getColumnName(int column) {
			return title[column];
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	public Magazine(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);// ���ùرմ���

		Add.add(Add_User);
		Add.add(Add_Mag);
		Order.add(Order_1);
		Select.add(Select_1);
		Count.add(Count_1);
		Alter.add(Alter_1);
		About.add(About_1);
		menuBar.add(Add);
		menuBar.add(Order);
		menuBar.add(Select);
		menuBar.add(Count);
		menuBar.add(Alter);
		menuBar.add(About);
		Add_User.addActionListener(this);
		Add_Mag.addActionListener(this);
		Order_1.addActionListener(this);
		Select_1.addActionListener(this);
		Count_1.addActionListener(this);
		Alter_1.addActionListener(this);
		About_1.addActionListener(this);
		B_alter_user.addActionListener(this);
		B_alter_mag.addActionListener(this);
		add_mag.addActionListener(this);
		add_person.addActionListener(this);
		B_order.addActionListener(this);
		ok1.addActionListener(this);
		setJMenuBar(menuBar); // ���ò˵���
		setPanel_Add_User(); // ���ø������
		setPanel_Add_Mag();
		setPanel_Order();
		setPanel_Select();
		setPanel_Count();
		setPanel_Alter();
		setPanel_About();
		BigPanel.add(Panel_Add_User); // �Ѹ��������뵽������
		BigPanel.add(Panel_Add_Mag);
		BigPanel.add(Panel_Order);
		BigPanel.add(Panel_Select);
		BigPanel.add(Panel_Count);
		BigPanel.add(Panel_Alter);
		BigPanel.add(Panel_About);

		table = new JTable(tm); // �����Լ�������ģ��
		table.setToolTipText("Display,goodboy");// ���ð�����ʾ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(table.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// ���ñ������ߴ�ģʽ
		table.setShowHorizontalLines(true); // ���õ�Ԫ��֮��ķָ��� ��ʾ
		table.setShowVerticalLines(true);
		table.setVisible(true);
		scroll = new JScrollPane(table); // ��table���Ϲ�����
		scroll.setPreferredSize(new Dimension(530, 200));
		c.add(scroll, BorderLayout.CENTER);
		c.add(BigPanel, BorderLayout.NORTH);
	}

	public void setPanel_Add_User() // ���¼�붩����Ա�����ͼ
	{
		Panel_Add_User.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "¼�붩����Ա"));
		Panel_Add_User.setLayout(new GridLayout(6, 2));
		Panel_Add_User.add(User_id);
		Panel_Add_User.add(T_User_id);
		Panel_Add_User.add(User_name);
		Panel_Add_User.add(T_User_name);
		Panel_Add_User.add(User_sex);
		Panel_Add_User.add(T_User_sex);
		Panel_Add_User.add(User_dept);
		Panel_Add_User.add(T_User_dept);
		Panel_Add_User.add(User_address);
		Panel_Add_User.add(T_User_address);
		Panel_Add_User.add(add_person);

		Panel_Add_User.setVisible(false);
	}

	public void setPanel_Add_Mag() // ���¼�뱨�������ͼ
	{
		Panel_Add_Mag.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "¼�뱨��"));
		Panel_Add_Mag.setLayout(new GridLayout(7, 2));
		Panel_Add_Mag.add(Mag_id);
		Panel_Add_Mag.add(T_Mag_id);
		Panel_Add_Mag.add(Mag_name);
		Panel_Add_Mag.add(T_Mag_name);
		Panel_Add_Mag.add(Mag_press);
		Panel_Add_Mag.add(T_Mag_press);
		Panel_Add_Mag.add(Mag_pressdate);
		Panel_Add_Mag.add(T_Mag_pressdate);
		Panel_Add_Mag.add(Mag_price);
		Panel_Add_Mag.add(T_Mag_price);
		Panel_Add_Mag.add(Mag_comment);
		Panel_Add_Mag.add(T_Mag_comment);
		Panel_Add_Mag.add(add_mag);

		Panel_Add_Mag.setVisible(false);
	}

	public void setPanel_Order() // ��ƶ��İ����ͼ
	{
		Panel_Order.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "����"));
		Panel_Order.setLayout(new GridLayout(4, 2));
		Panel_Order.add(Order_id);
		Panel_Order.add(T_Order_id);
		Panel_Order.add(User_id);
		Panel_Order.add(T_User_id);
		Panel_Order.add(Mag_id);
		Panel_Order.add(T_Mag_id);
		Panel_Order.add(B_order);
		Panel_Order.setVisible(false);

	}

	public void setPanel_Select() // ��Ʋ�ѯ�����ͼ
	{
		Panel_Select.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "��ѯ"));
		Panel_Select.setLayout(new FlowLayout());
		Panel_Select.add(choice);
		Panel_Select.add(SelectInformations);
		Panel_Select.add(ok1);
		Panel_Select.setVisible(false);
	}

	public void setPanel_Count() // ���ͳ�ư����ͼ
	{
		Panel_Count.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ͳ��"));
		Panel_Count.setLayout(new GridLayout(1, 1));
		countInformations();
		Panel_Count.add(choice);
		Panel_Count.add(SelectInformations);
		Panel_Count.add(ok1);

		Panel_Count.setVisible(false);
	}

	public void setPanel_Alter() // ����޸İ����ͼ
	{
		Panel_Alter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "�޸�"));
		Panel_Alter.setLayout(new FlowLayout());
		Panel_Alter.add(B_alter_user);
		Panel_Alter.add(B_alter_mag);

		Panel_Alter.setVisible(false);
	}

	public void setPanel_About() // ��ƹ��ڰ����ͼ
	{
		Panel_About.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "����"));
		Panel_About.setLayout(new FlowLayout());
		Panel_About.add(About_me);

		Panel_About.setVisible(false);
	}

	public void HidePanel() {
		Panel_Add_User.setVisible(false);
		Panel_Add_Mag.setVisible(false);
		Panel_Order.setVisible(false);
		Panel_Select.setVisible(false);
		Panel_Count.setVisible(false);
		Panel_Alter.setVisible(false);
		Panel_About.setVisible(false);
	}

	public void Add_User() {
		try {
			Class.forName(driverClassName);
			String url = "jdbc:mysql://127.0.0.1:3306/qw";
			Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
			String sql ="insert into T_users (User_id,User_name,sex,dept,address)values( "
					+ T_User_id.getText() + ",'" 
					+ T_User_name.getText() + "','"
					+ T_User_sex.getText() + "','"
					+ T_User_dept.getText() + "','" 
					+ T_User_address.getText() + "')";
			stmt.executeUpdate(sql);
			stmt.executeUpdate("commit");
			JOptionPane.showMessageDialog(null, "�������ݳɹ�����");
		} catch (Exception sqle) {
			System.err.println(sqle);
		}
	}

	public void Add_Mag() {
		try {
			Class.forName(driverClassName);// 1.ע������
			Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);// 2.�������ݿ�
			Statement stmt = con.createStatement();
			String sql = "insert into T_mags (Mag_id,Mag_name,press,pressdate,price,comment)values ("
					+ T_Mag_id.getText() + ",'" 
					+ T_Mag_name.getText() + "','"
					+ T_Mag_press.getText() + "','"
					+ T_Mag_pressdate.getText() + "'," 
					+ T_Mag_price.getText() + ",'" 
					+ T_Mag_comment.getText() + "')";
			stmt.executeUpdate(sql);
			stmt.executeUpdate("commit");
			JOptionPane.showMessageDialog(null, "�������ݳɹ�����");
		} catch (Exception sqle) {
			System.err.println(sqle);
		}
	}

	public void Order() {
		try {
			Class.forName(driverClassName);
			Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
			String sql = "insert into T_orders values("
					+ T_Order_id.getText() + "," 
					+ T_User_id.getText() + ","
					+ T_Mag_id.getText() + ")";
						System.out.print(sql);
			System.out.print(sql);
			stmt.executeUpdate(sql);
			stmt.executeUpdate("commit");
			JOptionPane.showMessageDialog(null, "���붩���ɹ�����");
		} catch (Exception sqle) {
			System.err.println(sqle);
		}
	}

	public void countInformations(){
		try{
			Class.forName(driverClassName);
			Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
      		String sql ="select T_orders.O_ID,T_orders.USER_ID,T_users.USER_NAME,T_users.SEX,T_users.DEPT,T_users.ADDRESS,T_mags.MAG_ID,T_mags.MAG_NAME,T_mags.PRESS,T_mags.PRESSDATE,T_mags.PRICE,	T_mags.COMMENT from T_users,T_mags,T_orders where T_users.USER_ID=T_orders.USER_ID and  T_orders.MAG_ID=T_mags.MAG_ID ";
			ResultSet rSet = stmt.executeQuery(sql);
			vector.removeAllElements(); // ��ʼ����������
			tm.fireTableStructureChanged(); // ���±������
			Vector rec_vector = new Vector();
           
			while (rSet.next()) {
				rec_vector.addElement(String.valueOf(rSet.getString(1)));
				rec_vector.addElement(String.valueOf(rSet.getString(2)));
				rec_vector.addElement(rSet.getString(3));
				rec_vector.addElement(rSet.getString(4));
				rec_vector.addElement(rSet.getString(5));
				rec_vector.addElement(rSet.getString(6));
				rec_vector.addElement(String.valueOf(rSet.getString(7)));
				rec_vector.addElement(rSet.getString(8));
				rec_vector.addElement(rSet.getString(9));
				rec_vector.addElement(rSet.getString(10));
				rec_vector.addElement(String.valueOf(rSet.getString(11)));
				rec_vector.addElement(rSet.getString(12));		
				vector.addElement(rec_vector);
			}
			tm.fireTableStructureChanged();
			stmt.close();
			con.close();	
		}catch(Exception ee){
			ee.printStackTrace();}
	}

	public void selectInformation(){
		try{
			Class.forName(driverClassName);
			Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
      		String sql ="select T_orders.O_ID,T_orders.USER_ID,T_users.USER_NAME,T_users.SEX,"
      				+ "T_users.DEPT,T_users.ADDRESS,T_mags.MAG_ID,T_mags.MAG_NAME,T_mags.PRESS,"
      				+ "	T_mags.PRESSDATE,T_mags.PRICE,T_mags.COMMENT from T_users,T_mags,T_orders "
      				+ "	where T_orders.USER_ID =  "+SelectInformations.getText()+ "	and T_users.USER_ID=T_orders.USER_ID and T_orders.MAG_ID=T_mags.MAG_ID";
			ResultSet rSet = stmt.executeQuery(sql);
			vector.removeAllElements(); // ��ʼ����������
			tm.fireTableStructureChanged(); // ���±������
			Vector rec_vector = new Vector();

			while (rSet.next()) {
				rec_vector.addElement(String.valueOf(rSet.getString(1)));
				rec_vector.addElement(String.valueOf(rSet.getString(2)));
				rec_vector.addElement(rSet.getString(3));
				rec_vector.addElement(rSet.getString(4));
				rec_vector.addElement(rSet.getString(5));
				rec_vector.addElement(rSet.getString(6));
				rec_vector.addElement(String.valueOf(rSet.getString(7)));
				rec_vector.addElement(rSet.getString(8));
				rec_vector.addElement(rSet.getString(9));
				rec_vector.addElement(rSet.getString(10));
				rec_vector.addElement(String.valueOf(rSet.getString(11)));
				rec_vector.addElement(rSet.getString(12));		
				vector.addElement(rec_vector);
			}
			tm.fireTableStructureChanged();
			stmt.close();
			con.close();	
		}catch(Exception ee){
				ee.printStackTrace();}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Add_User) {
			HidePanel();
			setPanel_Add_User();
			Panel_Add_User.setVisible(true);
			scroll.setVisible(true);
			table.setVisible(true);
		}
		if (e.getSource() == add_person) {
			System.out.print("ok");
			Add_User();
		}
		if (e.getSource() == Add_Mag) {
			HidePanel();
			setPanel_Add_Mag();
			Panel_Add_Mag.setVisible(true);
			scroll.setVisible(true);
			table.setVisible(true);
		}
		if (e.getSource() == add_mag) {
			Add_Mag();
		}
		if (e.getSource() == Order_1) {
			HidePanel();
			setPanel_Order();
			Panel_Order.setVisible(true);
			scroll.setVisible(true);
			table.setVisible(true);
		}
		if (e.getSource() == B_order) {
			Order();
		}
		if (e.getSource() == Select_1) {
			HidePanel();
			setPanel_Select();
			Panel_Select.setVisible(true);
			scroll.setVisible(true);
			table.setVisible(true);
		}
		if (e.getSource() == Count_1) {
			HidePanel();
			setPanel_Count();
			countInformations();
			Panel_Count.setVisible(true);
			scroll.setVisible(true);
			table.setVisible(true);
		}
		if (e.getSource() == ok1) {
			selectInformation();
		}
		if (e.getSource() == Alter_1) {
			HidePanel();
			setPanel_Alter();
			Panel_Alter.setVisible(true);
			table.setVisible(true);
			if (e.getSource() == B_alter_user) {
				Alter_User frame = new Alter_User();
				frame.setBounds(20, 20, 400, 300);
				frame.setVisible(true);
			}
			if (e.getSource() == B_alter_mag) {
				Alter_Mag frame = new Alter_Mag();
				frame.setBounds(20, 20, 400, 300);
				frame.setVisible(true);
			}
		}
		if (e.getSource() == About_1) {
			HidePanel();
			setPanel_About();
			Panel_About.setVisible(true);
		}
		if (e.getSource() == B_alter_user) {
			Alter_User frame = new Alter_User();
			frame.setBounds(20, 20, 400, 300);
			frame.setVisible(true);
		}
		if (e.getSource() == B_alter_mag) {
			Alter_Mag frame = new Alter_Mag();
			frame.setBounds(20, 20, 400, 300);
			frame.setVisible(true);
		}
	}

	public String ran() {
		String s = "";
		for (int i = 0; i < 5; i++) {
			s = s + (Math.round(Math.random() * (10 - 1)) + 1);
		}
		return s;
	}

	public static void main(String[] args) {
		Magazine f = new Magazine("��������ϵͳ");
		f.setVisible(true);
		f.setBounds(10, 10, 600, 500);
	}
}
