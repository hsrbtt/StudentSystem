package studentsystem.view.studentClass;

import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import studentsystem.dao.ClassDao;
import studentsystem.model.StudentClass;
import studentsystem.util.Strutil;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClasslistFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField ClassName;
	private JTextField ClassSecondary;
	private JTextField ClassGrade;
	private JTextField ClassMajor;
	private JButton deleteButton;
	private JButton editButton;
	
	private DefaultTableModel dtm= null;

	/**
	 * Launch the application.
	 */
	
	
	public ClasslistFrame() {
		setTitle("ѧ���б�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		setLocationRelativeTo(null);//���ھ���
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 860, 428);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectRow(e);
			}
		});
		table.getTableHeader().setReorderingAllowed(false);//�����û������϶�
		table.setRowHeight(25);//���߶�
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"�༶���", "�����꼶", "�༶��", "����ѧԺ", "����רҵ", "�༶��Ϣ"
			}
		) {
			public boolean isCellEditable(int row,int column) {//�޷�ֱ�ӶԱ�����
				return false;
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("�༶��");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 464, 104, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ѧԺ");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 533, 104, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("�꼶");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(287, 464, 104, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("רҵ");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(287, 535, 104, 31);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(e);
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 651, 104, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setBounds(209, 650, 97, 30);
		contentPane.add(btnNewButton_1);
		
		deleteButton= new JButton("ɾ��");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButton(e);
			}
		});
		deleteButton.setFont(new Font("����", Font.PLAIN, 20));
		deleteButton.setBounds(601, 650, 97, 30);
		contentPane.add(deleteButton);
		
		editButton = new JButton("�༭");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			       editButton(e);
			}
		});
		editButton.setFont(new Font("����", Font.PLAIN, 20));
		editButton.setBounds(418, 650, 97, 30);
		contentPane.add(editButton);
		
		ClassName = new JTextField();
		ClassName.setFont(new Font("����", Font.PLAIN, 20));
		ClassName.setBounds(100, 464, 142, 31);
		contentPane.add(ClassName);
		ClassName.setColumns(10);
		
		ClassSecondary = new JTextField();
		ClassSecondary.setFont(new Font("����", Font.PLAIN, 20));
		ClassSecondary.setColumns(10);
		ClassSecondary.setBounds(68, 533, 209, 31);
		contentPane.add(ClassSecondary);
		
		ClassGrade = new JTextField();
		ClassGrade.setFont(new Font("����", Font.PLAIN, 20));
		ClassGrade.setColumns(10);
		ClassGrade.setBounds(365, 464, 118, 31);
		contentPane.add(ClassGrade);
		
		ClassMajor = new JTextField();
		ClassMajor.setFont(new Font("����", Font.PLAIN, 20));
		ClassMajor.setColumns(10);
		ClassMajor.setBounds(365, 533, 209, 31);
		contentPane.add(ClassMajor);
		this.dtm=(DefaultTableModel) table.getModel();
	     queryAllClass();
		}
	protected void editButton(ActionEvent e) {//�༭��ť
		// TODO �Զ����ɵķ������
		JOptionPane.showConfirmDialog(this,"û�뻹զд");
	}
	protected void deleteButton(ActionEvent e) {//ɾ����ť
		// TODO �Զ����ɵķ������
		if(JOptionPane.showConfirmDialog(this, "�Ƿ�ɾ���˰༶","����ɾ��.....",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION) {
			String id = dtm.getValueAt(this.table.getSelectedRow(), 0).toString();
			ClassDao classDao=new ClassDao();
			JOptionPane.showMessageDialog(this,classDao.deleteStudentClass(id));
			 queryAllClass();
		}
	}
	protected void selectRow(MouseEvent e) {//�б�������¼�
		// TODO �Զ����ɵķ������
		this.ClassName.setText(dtm.getValueAt(this.table.getSelectedRow(), 2).toString());
		this.ClassGrade.setText(dtm.getValueAt(this.table.getSelectedRow(), 1).toString());
		this.ClassSecondary.setText(dtm.getValueAt(this.table.getSelectedRow(), 3).toString());
		this.ClassMajor.setText(dtm.getValueAt(this.table.getSelectedRow(), 4).toString());
		this.editButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
	}
	//������ť
		protected void search(ActionEvent e) {
		// TODO �Զ����ɵķ������
		String name=this.ClassName.getText();
		String grade=this.ClassGrade.getText();
		String secondary=this.ClassSecondary.getText();
		String major=this.ClassMajor.getText();
		if(Strutil.isEmpty(name)&&Strutil.isEmpty(grade)&&Strutil.isEmpty(secondary)&&Strutil.isEmpty(major)) {//ʲô��������ֱ�ӵ������Ϊȫ�ֲ���
			  queryAllClass();
			  return;
		}
		StudentClass tempClass=new StudentClass();
		tempClass.setName(name);
		tempClass.setGrade(grade);
		tempClass.setSecondary(secondary);
		tempClass.setMajor(major);
	
		dtm.setRowCount(0);
		ClassDao classDao = new ClassDao();
		List<StudentClass>allClassList=classDao.querySearch(tempClass);
		for(StudentClass stc : allClassList) {
			Vector v=new Vector();
			v.add(stc.getId());
			v.add(stc.getGrade());
            v.add(stc.getName());
            v.add(stc.getSecondary());
            v.add(stc.getMajor());
            v.add(stc.getInfo());
            dtm.addRow(v);
		}
			
	}
		protected void resetButton(ActionEvent e) {//���ð�ť
		// TODO �Զ����ɵķ������
		this.ClassName.setText(null);
		this.ClassGrade.setText(null);
		this.ClassMajor.setText(null);
		this.ClassSecondary.setText(null);
		this.editButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
		
	}
		//ȫ�ֲ��ҷ���
		public void queryAllClass() {
			dtm.setRowCount(0);
			ClassDao classDao = new ClassDao();
			List<StudentClass>allClassList=classDao.queryAllClass();
			for(StudentClass stc : allClassList) {
				Vector v=new Vector();
				v.add(stc.getId());
				v.add(stc.getGrade());
	            v.add(stc.getName());
	            v.add(stc.getSecondary());
	            v.add(stc.getMajor());
	            v.add(stc.getInfo());
	            dtm.addRow(v);
				this.editButton.setEnabled(false);
				this.deleteButton.setEnabled(false);
			}
}}
