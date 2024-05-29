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
		setLocationRelativeTo(null);//窗口居中
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
		table.getTableHeader().setReorderingAllowed(false);//设置用户不可拖动
		table.setRowHeight(25);//表格高度
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"学号", "年级", "姓名", "性别", "民族", "班级", "专业", "学院"
			}
		){
			public boolean isCellEditable(int row,int column) {//无法直接对表格操作
				return false;
		};});
		table.getColumnModel().getColumn(0).setMaxWidth(83);
		table.getColumnModel().getColumn(5).setPreferredWidth(93);
		table.getColumnModel().getColumn(6).setPreferredWidth(135);
		table.getColumnModel().getColumn(7).setPreferredWidth(156);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("学生名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 477, 60, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("年级");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 542, 60, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("班级名");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(251, 477, 60, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("所属学院");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(251, 542, 88, 38);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("性别");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(504, 477, 49, 38);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("所属专业");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(575, 542, 88, 38);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("民族");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
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
		
		StudentMaleRadioBtn= new JRadioButton("男");
		StudentMaleRadioBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		StudentMaleRadioBtn.setBounds(559, 478, 49, 37);
		contentPane.add(StudentMaleRadioBtn);
		
		StudentFemaleRadioBtn = new JRadioButton("女");
		StudentFemaleRadioBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		StudentFemaleRadioBtn.setBounds(623, 478, 49, 37);
		contentPane.add(StudentFemaleRadioBtn);
		
	
		
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			searchButton(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(48, 624, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(321, 624, 97, 38);
		contentPane.add(btnNewButton_1);
		
		deleteButton= new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletesudent(e);
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		deleteButton.setBounds(591, 624, 97, 38);
		contentPane.add(deleteButton);
		
		editButton= new JButton("编辑");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editStudent(e);
			}
		});
		editButton.setFont(new Font("宋体", Font.PLAIN, 20));
		editButton.setBounds(862, 624, 97, 38);
		contentPane.add(editButton);
	
		
		this.dtm=(DefaultTableModel) table.getModel();
	     queryAllStudent();
	}
	protected void editStudent(ActionEvent e) {//编辑学生信息
		// TODO 自动生成的方法存根
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
	protected void deletesudent(ActionEvent e) {//删除按键
		// TODO 自动生成的方法存根
		if(JOptionPane.showConfirmDialog(this, "是否删除此学生信息","正在删除.....",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION) {
			String id = dtm.getValueAt(this.table.getSelectedRow(), 0).toString();
			StudentDao studentDao=new StudentDao();
			JOptionPane.showMessageDialog(this,studentDao.deleteStudent(id));
			 queryAllStudent();
		}
		
	}
	protected void selectRow(MouseEvent e) {//表格点击事件
		// TODO 自动生成的方法存根
		this.StudentNameText.setText(dtm.getValueAt(table.getSelectedRow(),2).toString());
		this.StudentClassText.setText(dtm.getValueAt(table.getSelectedRow(),5).toString());
		this.StudentMajorText.setText(dtm.getValueAt(table.getSelectedRow(),6).toString());
		this.StudentNationText.setText(dtm.getValueAt(table.getSelectedRow(),4).toString());
		this.StudentSecondaryText.setText(dtm.getValueAt(table.getSelectedRow(),7).toString());
		this.StudnetGradeText.setText(dtm.getValueAt(table.getSelectedRow(),1).toString());
		if("男".equals(dtm.getValueAt(table.getSelectedRow(),3).toString())) {
			this.StudentMaleRadioBtn.setSelected(true);
			this.StudentFemaleRadioBtn.setSelected(false);
		}else {
			this.StudentFemaleRadioBtn.setSelected(true);
			this.StudentMaleRadioBtn.setSelected(false);
		}
		this.editButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
	}
	protected void searchButton(ActionEvent e) {//搜索按钮
		// TODO 自动生成的方法存根
		String studentName =this.StudentNameText.getText();
		String studentClass=this.StudentClassText.getText();
		String studentNation=this.StudentNationText.getText();
		String studentGrade=this.StudnetGradeText.getText();
		String studnetSecondary=this.StudentSecondaryText.getText();
		String studentMajor=this.StudentMajorText.getText();
		String studentSex="";
		if(StudentMaleRadioBtn.isSelected()&&!StudentFemaleRadioBtn.isSelected()) {
			studentSex="男";
		}else if(	StudentFemaleRadioBtn.isSelected()&&!StudentMaleRadioBtn.isSelected()) {
			studentSex="女";
		}else {
			studentSex="";
		}
		 		
		if(Strutil.isEmpty(studentName)&&Strutil.isEmpty(studentClass)&&Strutil.isEmpty(studentNation)&&Strutil.isEmpty(studentGrade)&&Strutil.isEmpty(studnetSecondary)&&Strutil.isEmpty(studentMajor)&&Strutil.isEmpty(studentSex)) {//什么都不输入直接点击搜索为全局查找
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
	
	
	protected void resetButton(ActionEvent e) {//重置按钮
		// TODO 自动生成的方法存根
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
	
	
	public void queryAllStudent() {//生成全部学生列表
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
