package studentsystem.view.studentClass;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import studentsystem.dao.ClassDao;
import studentsystem.model.CollegeStructure;
import studentsystem.model.StudentClass;
import studentsystem.view.ManagerFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddClassFrame extends JFrame {

	private JPanel contentPane;
	private JTextField classNameText;
    private JComboBox classSecondaryComb ;
    private JComboBox classGradeConb;
	private JComboBox classMajorComb;
	private JTextArea classinfo; 
	public AddClassFrame() {
		setTitle("添加班级");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 762, 488);
		setLocationRelativeTo(null);//窗口居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("班级名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 56, 90, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("所属年级");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(423, 56, 90, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("所属学院");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(36, 151, 90, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("班级介绍");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(36, 243, 90, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("所属专业");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(423, 151, 90, 34);
		contentPane.add(lblNewLabel_4);
		
		 classinfo = new JTextArea();
		classinfo.setLineWrap(true);
		classinfo.setBounds(136, 243, 363, 108);
		contentPane.add(classinfo);
		
		classNameText = new JTextField();
		classNameText.setBounds(136, 56, 148, 34);
		contentPane.add(classNameText);
		classNameText.setColumns(10);
		
		 classGradeConb = new JComboBox(new DefaultComboBoxModel(CollegeStructure.gradestr));//出现班级选项
		classGradeConb.setBounds(523, 56, 140, 31);
		contentPane.add(classGradeConb);
		
		classSecondaryComb= new JComboBox(new DefaultComboBoxModel(CollegeStructure.secondarystr));//学院选项
		classSecondaryComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//当我们点击学院时自动出现对应的专业
				AddClassFrame.this.classMajorComb.setModel(
		        new DefaultComboBoxModel(CollegeStructure.major[AddClassFrame.this.classSecondaryComb.getSelectedIndex()]));
			}
		});
		classSecondaryComb.setBounds(136, 155, 148, 31);
		contentPane.add(classSecondaryComb);
		
		classMajorComb= new JComboBox(new DefaultComboBoxModel(CollegeStructure.major[0]));//专业选项默认是第一个学院的选项
		classMajorComb.setBounds(523, 155, 140, 31);
		contentPane.add(classMajorComb);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			addClassButton(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(84, 408, 97, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restButton(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(566, 408, 97, 33);
		contentPane.add(btnNewButton_1);
		btnNewButton.setFocusable(false);
		btnNewButton_1.setFocusable(false);
	}
	protected void addClassButton(ActionEvent e) {//确认按钮,添加班级信息
		String name=this.classNameText.getText();
		String grade=this.classGradeConb.getSelectedItem().toString();
		String secondary=this.classSecondaryComb.getSelectedItem().toString();
		String major=this.classMajorComb.getSelectedItem().toString();
		String info=this.classinfo.getText();
		//生成班级编号
		String id=CollegeStructure.majornum[classSecondaryComb.getSelectedIndex()][classMajorComb.getSelectedIndex()]+String.valueOf(Integer.parseInt(grade)%2000);
		StudentClass temClass=new StudentClass(id,grade,name,secondary,major,info);
		ClassDao classDao=new ClassDao();
		JOptionPane.showMessageDialog(this,classDao.addStudentClass(temClass));
		// TODO 自动生成的方法存根
		if(ManagerFrame.clf!=null) {//当打开班级列表页面时再进行添加班操作可以及时显示在列表上
			ManagerFrame.clf.queryAllClass();
		}
	}
	protected void restButton(ActionEvent e) {//重置按钮
		// TODO 自动生成的方法存根
		this.classGradeConb.setSelectedIndex(0);
		this.classinfo.setText(null);
		this.classSecondaryComb.setSelectedIndex(0);
		this.classMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[0]));
		this.classNameText.setText(null);
	}}
	
