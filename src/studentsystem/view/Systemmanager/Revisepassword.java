package studentsystem.view.Systemmanager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import studentsystem.dao.AdminDao;
import studentsystem.util.Strutil;
import studentsystem.view.ManagerFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Revisepassword extends JFrame {

	private JPanel contentPane;
	private JTextField Newpassword;
	private JTextField Oldpassword;
	private JTextField Againpassword;

	
	public Revisepassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	 	setBounds(100, 100, 579, 348);
		setLocationRelativeTo(null);//窗口居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u7528\u6237\u540D");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(101, 31, 110, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u65E7\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(101, 78, 110, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(101, 126, 110, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(101, 171, 110, 34);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}

			
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(59, 251, 110, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}

		
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(363, 251, 110, 34);
		contentPane.add(btnNewButton_1);
		
		Newpassword = new JTextField();
		Newpassword.setToolTipText("");
		Newpassword.setColumns(10);
		Newpassword.setBounds(255, 126, 152, 30);
		contentPane.add(Newpassword);
		
		Oldpassword = new JTextField();
		Oldpassword.setColumns(10);
		Oldpassword.setBounds(255, 78, 152, 30);
		contentPane.add(Oldpassword);
		
		Againpassword = new JTextField();
		Againpassword.setColumns(10);
		Againpassword.setBounds(255, 171, 152, 30);
		contentPane.add(Againpassword);
		//读取用户类型和用户名
		String usertypestr=ManagerFrame.usertype.getname();
		String adminnamestr=ManagerFrame.admin.getName();
		
		
		JLabel lblNewLabel_4 = new JLabel("【"+usertypestr+"】"+adminnamestr);
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(267, 31, 237, 34);
		contentPane.add(lblNewLabel_4);
		setVisible(true);
	}
	private void resetButton() {//重置按钮
		// TODO 自动生成的方法存根
		this.Oldpassword.setText(null);
		this.Newpassword.setText(null);
	    this.Againpassword.setText(null);	
	
	}
	private void confirmButton(ActionEvent e) {//确认按钮
		// TODO 自动生成的方法存根
		String oldpassword =this.Oldpassword.getText();
		String newpassword=this.Newpassword.getText();
		String againpassword=this.Againpassword.getText();
		if(Strutil.isEmpty(oldpassword)) {
			JOptionPane.showMessageDialog(this,"请输入原密码");
			return;
		}
		if(Strutil.isEmpty(newpassword)) {
			JOptionPane.showMessageDialog(this,"请输入新密码");
			return;
		}
		if(Strutil.isEmpty(againpassword)) {
			JOptionPane.showMessageDialog(this,"请确认密码");
			return;
		}
		if("用户管理员".equals(ManagerFrame.usertype.getname())) {
			AdminDao admindao=new AdminDao();
			JOptionPane.showMessageDialog(this,admindao.revisepassword(ManagerFrame.admin, newpassword));//读取修改后的新密码
			resetButton();
			return;
		}
        if("学生".equals(ManagerFrame.usertype.getname())) {
        	return;
		}
        if("教师".equals(ManagerFrame.usertype.getname())) {
        	return;
		}
	}
	public void doDefaultCloseAction() {//c重写关闭窗体键
		resetButton() ;
	}
}
