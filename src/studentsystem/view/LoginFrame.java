package studentsystem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import studentsystem.dao.AdminDao;
import studentsystem.model.Admin;
import studentsystem.model.Usertype;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField adminName;
	private JTextField password;
	private JComboBox adminType ;

	/**
	 *创建登陆页面
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setBackground(new Color(240, 240, 240));
		setTitle("登陆页面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(218, 10, 0, 0);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("学生信息管理系统");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBounds(141, 10, 275, 86);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(141, 106, 77, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密 码");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(142, 156, 58, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("用户类型");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(141, 206, 103, 24);
		contentPane.add(lblNewLabel_3);
		
		adminName = new JTextField();
		adminName.setBounds(218, 106, 135, 25);
		contentPane.add(adminName);
		adminName.setColumns(10);
		
		password = new JTextField();
		password.setBounds(218, 159, 135, 24);
		contentPane.add(password);
		password.setColumns(10);
		
		adminType = new JComboBox();
		adminType.setModel(new DefaultComboBoxModel(new Usertype[] {Usertype.ADMIN,Usertype.STUDENT,Usertype.TEACHER}));//用户类型选择显示
		adminType.setBounds(241, 207, 112, 26);
		contentPane.add(adminType);
		
		JButton btnNewButton = new JButton("注册");//注册
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(26, 291, 97, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");//重置按钮
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					resetButton(e);
			}
		});
		btnNewButton_1.setBounds(160, 291, 97, 42);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("登录");//登录
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(299, 291, 97, 42);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("退出");//退出
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_3.setBounds(428, 291, 97, 42);
		contentPane.add(btnNewButton_3);
		//取消虚线
		btnNewButton.setFocusable(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_2.setFocusable(false);
		btnNewButton_3.setFocusable(false);
		setLocationRelativeTo(null);
	}

	protected void confirmButton(ActionEvent e) {
		// TODO实现登录功能
		String name=this.adminName.getText();
		String password=this.password.getText();
		Usertype usertype =(Usertype) this.adminType.getSelectedItem();
		if("用户管理员".equals(usertype.getname())) {
			AdminDao admindao=new AdminDao();
			Admin admin=admindao.selectadmin(name, password);
			if(admin==null) {
				JOptionPane.showMessageDialog(this, "用户名密码错误");
				return;
			}
			ManagerFrame indexFrame=new ManagerFrame(usertype,admin);//把登录用户信息传给下个页面
			indexFrame.setVisible(true);//显示下个页面
			this.dispose();//关闭当前页面
			
		
		}
		if("学生".equals(usertype.getname())) {
			return;
		}
		if("教师".equals(usertype.getname())){
			return;
		}
	
	}

	protected void resetButton(ActionEvent e) {
		// 登录重置功能
		this.adminName.setText("");
		this.password.setText("");
		this.adminType.setSelectedIndex(0);
	}
}
