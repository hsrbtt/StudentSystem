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
	 *������½ҳ��
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
		setTitle("��½ҳ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(218, 10, 0, 0);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("ѧ����Ϣ����ϵͳ");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 30));
		lblNewLabel.setBounds(141, 10, 275, 86);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("�û���");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(141, 106, 77, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("�� ��");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(142, 156, 58, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("�û�����");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 20));
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
		adminType.setModel(new DefaultComboBoxModel(new Usertype[] {Usertype.ADMIN,Usertype.STUDENT,Usertype.TEACHER}));//�û�����ѡ����ʾ
		adminType.setBounds(241, 207, 112, 26);
		contentPane.add(adminType);
		
		JButton btnNewButton = new JButton("ע��");//ע��
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.setBounds(26, 291, 97, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("����");//���ð�ť
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					resetButton(e);
			}
		});
		btnNewButton_1.setBounds(160, 291, 97, 42);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("��¼");//��¼
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnNewButton_2.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_2.setBounds(299, 291, 97, 42);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("�˳�");//�˳�
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_3.setBounds(428, 291, 97, 42);
		contentPane.add(btnNewButton_3);
		//ȡ������
		btnNewButton.setFocusable(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_2.setFocusable(false);
		btnNewButton_3.setFocusable(false);
		setLocationRelativeTo(null);
	}

	protected void confirmButton(ActionEvent e) {
		// TODOʵ�ֵ�¼����
		String name=this.adminName.getText();
		String password=this.password.getText();
		Usertype usertype =(Usertype) this.adminType.getSelectedItem();
		if("�û�����Ա".equals(usertype.getname())) {
			AdminDao admindao=new AdminDao();
			Admin admin=admindao.selectadmin(name, password);
			if(admin==null) {
				JOptionPane.showMessageDialog(this, "�û����������");
				return;
			}
			ManagerFrame indexFrame=new ManagerFrame(usertype,admin);//�ѵ�¼�û���Ϣ�����¸�ҳ��
			indexFrame.setVisible(true);//��ʾ�¸�ҳ��
			this.dispose();//�رյ�ǰҳ��
			
		
		}
		if("ѧ��".equals(usertype.getname())) {
			return;
		}
		if("��ʦ".equals(usertype.getname())){
			return;
		}
	
	}

	protected void resetButton(ActionEvent e) {
		// ��¼���ù���
		this.adminName.setText("");
		this.password.setText("");
		this.adminType.setSelectedIndex(0);
	}
}
