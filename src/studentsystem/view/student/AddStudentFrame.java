package studentsystem.view.student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import studentsystem.dao.ClassDao;
import studentsystem.dao.StudentDao;
import studentsystem.model.CollegeStructure;
import studentsystem.model.Student;
import studentsystem.model.StudentClass;
import studentsystem.util.Strutil;
import studentsystem.view.ManagerFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStudentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField studentNameText;
	private JTextField studentNationText;
	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private JLabel studentClassId;
	private JComboBox studentGradeComb ;
	private JComboBox studentSecondaryComb;
	private JComboBox studentMajorComb;
	private JComboBox studentClassComb;
	private ArrayList<StudentClass> arrayClass=null;
	
	
	public AddStudentFrame() {
		setTitle("添加学生信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 837, 587);
		setLocationRelativeTo(null);//窗口居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学生姓名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(74, 42, 99, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("所属年级");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(74, 125, 99, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("所属学院");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(74, 216, 99, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("性别");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(432, 42, 56, 32);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("民族");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(432, 125, 99, 32);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("所属专业");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(432, 216, 99, 32);
		contentPane.add(lblNewLabel_5);
		
		studentNameText = new JTextField();
		studentNameText.setFont(new Font("宋体", Font.PLAIN, 20));
		studentNameText.setBounds(219, 42, 129, 29);
		contentPane.add(studentNameText);
		studentNameText.setColumns(10);
		
		studentNationText = new JTextField("汉族");
		studentNationText.setFont(new Font("宋体", Font.PLAIN, 20));
		studentNationText.setColumns(10);
		studentNationText.setBounds(541, 126, 129, 29);
		contentPane.add(studentNationText);
		
		JLabel lblNewLabel_2_1 = new JLabel("所属班级");
		lblNewLabel_2_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(74, 292, 99, 32);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("班级编号");
		lblNewLabel_2_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(432, 292, 99, 32);
		contentPane.add(lblNewLabel_2_2);
		
		studentGradeComb= new JComboBox(CollegeStructure.gradestr);
		studentGradeComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentClass(e);
			}
		});
		studentGradeComb.setFont(new Font("宋体", Font.PLAIN, 20));
		studentGradeComb.setBounds(219, 125, 104, 30);
		contentPane.add(studentGradeComb);
		
		 studentSecondaryComb = new JComboBox(CollegeStructure.secondarystr);
		 studentSecondaryComb.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		AddStudentFrame.this.studentMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[ studentSecondaryComb.getSelectedIndex()]));//选择不同的学院显示对应专业
		 		setStudentClass(e);
		 	}
		 });
		studentSecondaryComb.setFont(new Font("宋体", Font.PLAIN, 20));
		studentSecondaryComb.setBounds(219, 219, 165, 30);
		contentPane.add(studentSecondaryComb);
		
		studentMajorComb= new JComboBox(CollegeStructure.major[0]);
		studentMajorComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentClass(e);
			}
		});
		studentMajorComb.setFont(new Font("宋体", Font.PLAIN, 20));
		studentMajorComb.setBounds(541, 219, 171, 30);
		contentPane.add(studentMajorComb);
		
       
		ClassDao classDao=new ClassDao();
		 //根据专业，年级，学院显示对应班级
		this.arrayClass=classDao.querySomeClass(studentGradeComb.getSelectedItem().toString(), studentSecondaryComb.getSelectedItem().toString(), studentMajorComb.getSelectedItem().toString());
		studentClassComb= new JComboBox(this.arrayClass.toArray());
		studentClassComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrame.this.studentClassId.setText(arrayClass.get(studentClassComb.getSelectedIndex()).getId());//选择不同班级显示对应编号
			}
		});
	    if(this.arrayClass.isEmpty()) {
	    	studentClassComb.setEnabled(false);
		}
		studentClassComb.setFont(new Font("宋体", Font.PLAIN, 20));
		studentClassComb.setBounds(219, 299, 104, 30);
		contentPane.add(studentClassComb);
		
		 maleRadioButton= new JRadioButton("男");
		 maleRadioButton.setSelected(true);
		maleRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		maleRadioButton.setBounds(506, 42, 56, 30);
		contentPane.add(maleRadioButton);
		
		femaleRadioButton = new JRadioButton("女");
		femaleRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		femaleRadioButton.setBounds(595, 44, 56, 30);
		contentPane.add(femaleRadioButton);
		
		ButtonGroup bg= new ButtonGroup();
		bg.add(femaleRadioButton);
		bg.add(maleRadioButton);
		
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(94, 469, 104, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(566, 469, 104, 39);
		contentPane.add(btnNewButton_1);
		
		studentClassId = new JLabel(arrayClass.get(0).getId());
		studentClassId.setFont(new Font("宋体", Font.PLAIN, 20));
		studentClassId.setBounds(541, 292, 99, 32);
		contentPane.add(studentClassId);
	}


	protected void confirmButton(ActionEvent e) {//确认按钮
		// TODO 自动生成的方法存根
		String classId=this.studentClassId.getText();
		if(!classId.matches("[0-9]*")) {//当班级为空点击确认会弹窗
			JOptionPane.showMessageDialog(this,"请选择对应班级");
			return;
		}
		if(new ClassDao().querySomeClass(classId)==false) {
			JOptionPane.showMessageDialog(this,"没有此班级");
			resetButton();
			return;
		}
		String className=this.studentClassComb.getSelectedItem().toString();
		String id =classId;
		String grade =this.studentGradeComb.getSelectedItem().toString();
		String name =this.studentNameText.getText();
		if(Strutil.isEmpty(name)) {
			JOptionPane.showMessageDialog(this,"请输入姓名！");
			return;
		}
		String sex ="男";
		if(this.femaleRadioButton.isSelected()) {
			sex = "女";
		}
		String nation = this.studentNationText.getText();
		String major = this.studentMajorComb.getSelectedItem().toString();
		String secondary =this.studentSecondaryComb.getSelectedItem().toString();
		Student tempStudent =new Student(id, grade, classId,className, name, sex, nation,major, secondary);
		JOptionPane.showMessageDialog(this,new StudentDao().addStudentInfo(tempStudent));
		if(ManagerFrame.addstudentframe!=null) {////当打开学生列表页面时再进行添加学生可以及时显示在列表上
			ManagerFrame.studentlisframe.queryAllStudent();
		}
	}


	protected void resetButton() {//重置按钮
		// TODO 自动生成的方法存根
		this.studentNameText.setText("");
		this.maleRadioButton.setSelected(true);
		this.studentNationText.setText("汉族");
		this.studentGradeComb.setSelectedIndex(0);
		this.studentSecondaryComb.setSelectedIndex(0);
		this.studentMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[0]));
		ClassDao classDao=new ClassDao();
		this.arrayClass=classDao.querySomeClass(studentGradeComb.getSelectedItem().toString(), studentSecondaryComb.getSelectedItem().toString(), studentMajorComb.getSelectedItem().toString());
		if(arrayClass.isEmpty()) {
	
			this.studentClassId.setText("没有编号");
			this.studentClassComb.setEnabled(false);
		}else {
			this.studentClassComb.setModel(new DefaultComboBoxModel(this.arrayClass.toArray()));
			this.studentClassId.setText(this.arrayClass.get(0).getId());	
		}
			
	
	}


	protected void setStudentClass(ActionEvent e) {//显示对应专业，学院，年级的班级
		ClassDao classDao=new ClassDao();
		this.arrayClass=classDao.querySomeClass(studentGradeComb.getSelectedItem().toString(), studentSecondaryComb.getSelectedItem().toString(), studentMajorComb.getSelectedItem().toString());
		if(arrayClass.isEmpty()) {
			//当没有对应的班级时无法操作班级键
			this.studentClassId.setText("没有编号");
			this.studentClassComb.setEnabled(false);
		}else {
			this.studentClassId.setText(this.arrayClass.get(0).getId());
			this.studentClassComb.setEnabled(true);
		}
			
		this.studentClassComb.setModel(new DefaultComboBoxModel(this.arrayClass.toArray()));
		
	}
}
