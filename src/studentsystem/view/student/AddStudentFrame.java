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
		setTitle("���ѧ����Ϣ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 837, 587);
		setLocationRelativeTo(null);//���ھ���
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ѧ������");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(74, 42, 99, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("�����꼶");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(74, 125, 99, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("����ѧԺ");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(74, 216, 99, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("�Ա�");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(432, 42, 56, 32);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("����");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(432, 125, 99, 32);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("����רҵ");
		lblNewLabel_5.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(432, 216, 99, 32);
		contentPane.add(lblNewLabel_5);
		
		studentNameText = new JTextField();
		studentNameText.setFont(new Font("����", Font.PLAIN, 20));
		studentNameText.setBounds(219, 42, 129, 29);
		contentPane.add(studentNameText);
		studentNameText.setColumns(10);
		
		studentNationText = new JTextField("����");
		studentNationText.setFont(new Font("����", Font.PLAIN, 20));
		studentNationText.setColumns(10);
		studentNationText.setBounds(541, 126, 129, 29);
		contentPane.add(studentNationText);
		
		JLabel lblNewLabel_2_1 = new JLabel("�����༶");
		lblNewLabel_2_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(74, 292, 99, 32);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("�༶���");
		lblNewLabel_2_2.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(432, 292, 99, 32);
		contentPane.add(lblNewLabel_2_2);
		
		studentGradeComb= new JComboBox(CollegeStructure.gradestr);
		studentGradeComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentClass(e);
			}
		});
		studentGradeComb.setFont(new Font("����", Font.PLAIN, 20));
		studentGradeComb.setBounds(219, 125, 104, 30);
		contentPane.add(studentGradeComb);
		
		 studentSecondaryComb = new JComboBox(CollegeStructure.secondarystr);
		 studentSecondaryComb.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		AddStudentFrame.this.studentMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[ studentSecondaryComb.getSelectedIndex()]));//ѡ��ͬ��ѧԺ��ʾ��Ӧרҵ
		 		setStudentClass(e);
		 	}
		 });
		studentSecondaryComb.setFont(new Font("����", Font.PLAIN, 20));
		studentSecondaryComb.setBounds(219, 219, 165, 30);
		contentPane.add(studentSecondaryComb);
		
		studentMajorComb= new JComboBox(CollegeStructure.major[0]);
		studentMajorComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentClass(e);
			}
		});
		studentMajorComb.setFont(new Font("����", Font.PLAIN, 20));
		studentMajorComb.setBounds(541, 219, 171, 30);
		contentPane.add(studentMajorComb);
		
       
		ClassDao classDao=new ClassDao();
		 //����רҵ���꼶��ѧԺ��ʾ��Ӧ�༶
		this.arrayClass=classDao.querySomeClass(studentGradeComb.getSelectedItem().toString(), studentSecondaryComb.getSelectedItem().toString(), studentMajorComb.getSelectedItem().toString());
		studentClassComb= new JComboBox(this.arrayClass.toArray());
		studentClassComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrame.this.studentClassId.setText(arrayClass.get(studentClassComb.getSelectedIndex()).getId());//ѡ��ͬ�༶��ʾ��Ӧ���
			}
		});
	    if(this.arrayClass.isEmpty()) {
	    	studentClassComb.setEnabled(false);
		}
		studentClassComb.setFont(new Font("����", Font.PLAIN, 20));
		studentClassComb.setBounds(219, 299, 104, 30);
		contentPane.add(studentClassComb);
		
		 maleRadioButton= new JRadioButton("��");
		 maleRadioButton.setSelected(true);
		maleRadioButton.setFont(new Font("����", Font.PLAIN, 20));
		maleRadioButton.setBounds(506, 42, 56, 30);
		contentPane.add(maleRadioButton);
		
		femaleRadioButton = new JRadioButton("Ů");
		femaleRadioButton.setFont(new Font("����", Font.PLAIN, 20));
		femaleRadioButton.setBounds(595, 44, 56, 30);
		contentPane.add(femaleRadioButton);
		
		ButtonGroup bg= new ButtonGroup();
		bg.add(femaleRadioButton);
		bg.add(maleRadioButton);
		
		
		JButton btnNewButton = new JButton("ȷ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.setBounds(94, 469, 104, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.setBounds(566, 469, 104, 39);
		contentPane.add(btnNewButton_1);
		
		studentClassId = new JLabel(arrayClass.get(0).getId());
		studentClassId.setFont(new Font("����", Font.PLAIN, 20));
		studentClassId.setBounds(541, 292, 99, 32);
		contentPane.add(studentClassId);
	}


	protected void confirmButton(ActionEvent e) {//ȷ�ϰ�ť
		// TODO �Զ����ɵķ������
		String classId=this.studentClassId.getText();
		if(!classId.matches("[0-9]*")) {//���༶Ϊ�յ��ȷ�ϻᵯ��
			JOptionPane.showMessageDialog(this,"��ѡ���Ӧ�༶");
			return;
		}
		if(new ClassDao().querySomeClass(classId)==false) {
			JOptionPane.showMessageDialog(this,"û�д˰༶");
			resetButton();
			return;
		}
		String className=this.studentClassComb.getSelectedItem().toString();
		String id =classId;
		String grade =this.studentGradeComb.getSelectedItem().toString();
		String name =this.studentNameText.getText();
		if(Strutil.isEmpty(name)) {
			JOptionPane.showMessageDialog(this,"������������");
			return;
		}
		String sex ="��";
		if(this.femaleRadioButton.isSelected()) {
			sex = "Ů";
		}
		String nation = this.studentNationText.getText();
		String major = this.studentMajorComb.getSelectedItem().toString();
		String secondary =this.studentSecondaryComb.getSelectedItem().toString();
		Student tempStudent =new Student(id, grade, classId,className, name, sex, nation,major, secondary);
		JOptionPane.showMessageDialog(this,new StudentDao().addStudentInfo(tempStudent));
		if(ManagerFrame.addstudentframe!=null) {////����ѧ���б�ҳ��ʱ�ٽ������ѧ�����Լ�ʱ��ʾ���б���
			ManagerFrame.studentlisframe.queryAllStudent();
		}
	}


	protected void resetButton() {//���ð�ť
		// TODO �Զ����ɵķ������
		this.studentNameText.setText("");
		this.maleRadioButton.setSelected(true);
		this.studentNationText.setText("����");
		this.studentGradeComb.setSelectedIndex(0);
		this.studentSecondaryComb.setSelectedIndex(0);
		this.studentMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[0]));
		ClassDao classDao=new ClassDao();
		this.arrayClass=classDao.querySomeClass(studentGradeComb.getSelectedItem().toString(), studentSecondaryComb.getSelectedItem().toString(), studentMajorComb.getSelectedItem().toString());
		if(arrayClass.isEmpty()) {
	
			this.studentClassId.setText("û�б��");
			this.studentClassComb.setEnabled(false);
		}else {
			this.studentClassComb.setModel(new DefaultComboBoxModel(this.arrayClass.toArray()));
			this.studentClassId.setText(this.arrayClass.get(0).getId());	
		}
			
	
	}


	protected void setStudentClass(ActionEvent e) {//��ʾ��Ӧרҵ��ѧԺ���꼶�İ༶
		ClassDao classDao=new ClassDao();
		this.arrayClass=classDao.querySomeClass(studentGradeComb.getSelectedItem().toString(), studentSecondaryComb.getSelectedItem().toString(), studentMajorComb.getSelectedItem().toString());
		if(arrayClass.isEmpty()) {
			//��û�ж�Ӧ�İ༶ʱ�޷������༶��
			this.studentClassId.setText("û�б��");
			this.studentClassComb.setEnabled(false);
		}else {
			this.studentClassId.setText(this.arrayClass.get(0).getId());
			this.studentClassComb.setEnabled(true);
		}
			
		this.studentClassComb.setModel(new DefaultComboBoxModel(this.arrayClass.toArray()));
		
	}
}
