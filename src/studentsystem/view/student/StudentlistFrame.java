package studentsystem.view.student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import studentsystem.dao.ClassDao;
import studentsystem.dao.StudentDao;
import studentsystem.model.Student;
import studentsystem.model.StudentClass;
import studentsystem.util.Strutil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentlistFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField StudentNameText;
	private JTextField StudnetGradeText;
	private JTextField StudentClassText;
	private JTextField StudentSecondaryText;
	private JTextField StudentMajorText;
	private JTextField StudentNationText;
	private JButton deleteButton;
	private JButton editButton;
	private JRadioButton StudentFemaleRadioBtn;
	private JRadioButton StudentMaleRadioBtn ;
	private EditStudentFrame eidtstudentframe;
	private DefaultTableModel dtm= null;

	
	public StudentlistFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1017, 736);
		setLocationRelativeTo(null);//���ھ���
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1000, 457);
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
				"ѧ��", "�꼶", "����", "�Ա�", "����", "�༶", "רҵ", "ѧԺ"
			}
		){
			public boolean isCellEditable(int row,int column) {//�޷�ֱ�ӶԱ�����
				return false;
		};});
		table.getColumnModel().getColumn(0).setMaxWidth(83);
		table.getColumnModel().getColumn(5).setPreferredWidth(93);
		table.getColumnModel().getColumn(6).setPreferredWidth(135);
		table.getColumnModel().getColumn(7).setPreferredWidth(156);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ѧ����");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 477, 60, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("�꼶");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 542, 60, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("�༶��");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(251, 477, 60, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("����ѧԺ");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(251, 542, 88, 38);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("�Ա�");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(504, 477, 49, 38);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("����רҵ");
		lblNewLabel_5.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(575, 542, 88, 38);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("����");
		lblNewLabel_6.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(720, 477, 49, 38);
		contentPane.add(lblNewLabel_6);
		
		StudentNameText = new JTextField();
		StudentNameText.setBounds(93, 477, 119, 38);
		contentPane.add(StudentNameText);
		StudentNameText.setColumns(10);
		
		StudnetGradeText = new JTextField();
		StudnetGradeText.setColumns(10);
		StudnetGradeText.setBounds(93, 542, 119, 38);
		contentPane.add(StudnetGradeText);
		
		StudentClassText = new JTextField();
		StudentClassText.setColumns(10);
		StudentClassText.setBounds(348, 477, 119, 38);
		contentPane.add(StudentClassText);
		
		StudentSecondaryText = new JTextField();
		StudentSecondaryText.setColumns(10);
		StudentSecondaryText.setBounds(349, 542, 205, 38);
		contentPane.add(StudentSecondaryText);
		
		StudentMajorText = new JTextField();
		StudentMajorText.setColumns(10);
		StudentMajorText.setBounds(673, 545, 219, 38);
		contentPane.add(StudentMajorText);
		
		StudentNationText = new JTextField();
		StudentNationText.setColumns(10);
		StudentNationText.setBounds(779, 477, 93, 38);
		contentPane.add(StudentNationText);
		
		StudentMaleRadioBtn= new JRadioButton("��");
		StudentMaleRadioBtn.setFont(new Font("����", Font.PLAIN, 20));
		StudentMaleRadioBtn.setBounds(559, 478, 49, 37);
		contentPane.add(StudentMaleRadioBtn);
		
		StudentFemaleRadioBtn = new JRadioButton("Ů");
		StudentFemaleRadioBtn.setFont(new Font("����", Font.PLAIN, 20));
		StudentFemaleRadioBtn.setBounds(623, 478, 49, 37);
		contentPane.add(StudentFemaleRadioBtn);
		
	
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			searchButton(e);
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.setBounds(48, 624, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.setBounds(321, 624, 97, 38);
		contentPane.add(btnNewButton_1);
		
		deleteButton= new JButton("ɾ��");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletesudent(e);
			}
		});
		deleteButton.setFont(new Font("����", Font.PLAIN, 20));
		deleteButton.setBounds(591, 624, 97, 38);
		contentPane.add(deleteButton);
		
		editButton= new JButton("�༭");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editStudent(e);
			}
		});
		editButton.setFont(new Font("����", Font.PLAIN, 20));
		editButton.setBounds(862, 624, 97, 38);
		contentPane.add(editButton);
	
		
		this.dtm=(DefaultTableModel) table.getModel();
	     queryAllStudent();
	}
	protected void editStudent(ActionEvent e) {//�༭ѧ����Ϣ
		// TODO �Զ����ɵķ������
		Student tempStudent = new Student();
		tempStudent.setName(dtm.getValueAt(table.getSelectedRow(), 2).toString());
		tempStudent.setClassName(dtm.getValueAt(table.getSelectedRow(), 5).toString());
		tempStudent.setNation(dtm.getValueAt(table.getSelectedRow(), 4).toString());
		tempStudent.setGrade(dtm.getValueAt(table.getSelectedRow(), 1).toString());
		tempStudent.setSecondary(dtm.getValueAt(table.getSelectedRow(), 7).toString());
		tempStudent.setMajor(dtm.getValueAt(table.getSelectedRow(), 6).toString());
		tempStudent.setSex(dtm.getValueAt(table.getSelectedRow(), 3).toString());
		tempStudent.setId(dtm.getValueAt(table.getSelectedRow(), 0).toString());
		String ClassId= new StudentDao().queryStudentClass(tempStudent.getId());
		tempStudent.setClassid(ClassId);
		
		eidtstudentframe=new EditStudentFrame(tempStudent);
		eidtstudentframe.setVisible(true);
	}
	protected void deletesudent(ActionEvent e) {//ɾ������
		// TODO �Զ����ɵķ������
		if(JOptionPane.showConfirmDialog(this, "�Ƿ�ɾ����ѧ����Ϣ","����ɾ��.....",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION) {
			String id = dtm.getValueAt(this.table.getSelectedRow(), 0).toString();
			StudentDao studentDao=new StudentDao();
			JOptionPane.showMessageDialog(this,studentDao.deleteStudent(id));
			 queryAllStudent();
		}
		
	}
	protected void selectRow(MouseEvent e) {//������¼�
		// TODO �Զ����ɵķ������
		this.StudentNameText.setText(dtm.getValueAt(table.getSelectedRow(),2).toString());
		this.StudentClassText.setText(dtm.getValueAt(table.getSelectedRow(),5).toString());
		this.StudentMajorText.setText(dtm.getValueAt(table.getSelectedRow(),6).toString());
		this.StudentNationText.setText(dtm.getValueAt(table.getSelectedRow(),4).toString());
		this.StudentSecondaryText.setText(dtm.getValueAt(table.getSelectedRow(),7).toString());
		this.StudnetGradeText.setText(dtm.getValueAt(table.getSelectedRow(),1).toString());
		if("��".equals(dtm.getValueAt(table.getSelectedRow(),3).toString())) {
			this.StudentMaleRadioBtn.setSelected(true);
			this.StudentFemaleRadioBtn.setSelected(false);
		}else {
			this.StudentFemaleRadioBtn.setSelected(true);
			this.StudentMaleRadioBtn.setSelected(false);
		}
		this.editButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
	}
	protected void searchButton(ActionEvent e) {//������ť
		// TODO �Զ����ɵķ������
		String studentName =this.StudentNameText.getText();
		String studentClass=this.StudentClassText.getText();
		String studentNation=this.StudentNationText.getText();
		String studentGrade=this.StudnetGradeText.getText();
		String studnetSecondary=this.StudentSecondaryText.getText();
		String studentMajor=this.StudentMajorText.getText();
		String studentSex="";
		if(StudentMaleRadioBtn.isSelected()&&!StudentFemaleRadioBtn.isSelected()) {
			studentSex="��";
		}else if(	StudentFemaleRadioBtn.isSelected()&&!StudentMaleRadioBtn.isSelected()) {
			studentSex="Ů";
		}else {
			studentSex="";
		}
		 		
		if(Strutil.isEmpty(studentName)&&Strutil.isEmpty(studentClass)&&Strutil.isEmpty(studentNation)&&Strutil.isEmpty(studentGrade)&&Strutil.isEmpty(studnetSecondary)&&Strutil.isEmpty(studentMajor)&&Strutil.isEmpty(studentSex)) {//ʲô��������ֱ�ӵ������Ϊȫ�ֲ���
			  queryAllStudent();
			  return;
		}
		Student tempStudent = new Student();
		tempStudent.setName(studentName);
		tempStudent.setClassName(studentClass);
		tempStudent.setNation(studentNation);
		tempStudent.setGrade(studentGrade);
		tempStudent.setSecondary(studnetSecondary);
		tempStudent.setMajor(studentMajor);
		tempStudent.setSex(studentSex);
		 
		
		dtm.setRowCount(0);
		StudentDao studentDao = new StudentDao();
		List<Student>allStudentList=studentDao.querysearch(tempStudent);
		for(Student stc :allStudentList) {
			Vector v=new Vector();
			v.add(stc.getId());
			v.add(stc.getGrade());
            v.add(stc.getName());
            v.add(stc.getSex());
            v.add(stc.getNation());
            v.add(stc.getClassName());
			v.add(stc.getMajor());
			v.add(stc.getSecondary());
            dtm.addRow(v);
			this.editButton.setEnabled(false);
			this.deleteButton.setEnabled(false);
	}}
	
	
	protected void resetButton(ActionEvent e) {//���ð�ť
		// TODO �Զ����ɵķ������
		this.StudentNameText.setText(null);
		this.StudentClassText.setText(null);
		this.StudentMajorText.setText(null);
		this.StudentNationText.setText(null);
		this.StudentSecondaryText.setText(null);
		this.StudnetGradeText.setText(null);
		StudentMaleRadioBtn.setSelected(false);
		StudentFemaleRadioBtn.setSelected(false);
		this.editButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
		
	}
	
	
	public void queryAllStudent() {//����ȫ��ѧ���б�
		dtm.setRowCount(0);
		StudentDao studentDao = new StudentDao();
		List<Student>allStudentList=studentDao.queryAllstudent();
		for(Student stc :allStudentList) {
			Vector v=new Vector();
			v.add(stc.getId());
			v.add(stc.getGrade());
            v.add(stc.getName());
            v.add(stc.getSex());
            v.add(stc.getNation());
            v.add(stc.getClassName());
			v.add(stc.getMajor());
			v.add(stc.getSecondary());
            dtm.addRow(v);
			this.editButton.setEnabled(false);
			this.deleteButton.setEnabled(false);
	}
	
}}
