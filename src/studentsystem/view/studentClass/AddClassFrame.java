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
		setTitle("��Ӱ༶");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 762, 488);
		setLocationRelativeTo(null);//���ھ���
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�༶��");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 56, 90, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("�����꼶");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(423, 56, 90, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("����ѧԺ");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(36, 151, 90, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("�༶����");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(36, 243, 90, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("����רҵ");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 20));
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
		
		 classGradeConb = new JComboBox(new DefaultComboBoxModel(CollegeStructure.gradestr));//���ְ༶ѡ��
		classGradeConb.setBounds(523, 56, 140, 31);
		contentPane.add(classGradeConb);
		
		classSecondaryComb= new JComboBox(new DefaultComboBoxModel(CollegeStructure.secondarystr));//ѧԺѡ��
		classSecondaryComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����ǵ��ѧԺʱ�Զ����ֶ�Ӧ��רҵ
				AddClassFrame.this.classMajorComb.setModel(
		        new DefaultComboBoxModel(CollegeStructure.major[AddClassFrame.this.classSecondaryComb.getSelectedIndex()]));
			}
		});
		classSecondaryComb.setBounds(136, 155, 148, 31);
		contentPane.add(classSecondaryComb);
		
		classMajorComb= new JComboBox(new DefaultComboBoxModel(CollegeStructure.major[0]));//רҵѡ��Ĭ���ǵ�һ��ѧԺ��ѡ��
		classMajorComb.setBounds(523, 155, 140, 31);
		contentPane.add(classMajorComb);
		
		JButton btnNewButton = new JButton("ȷ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			addClassButton(e);
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.setBounds(84, 408, 97, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restButton(e);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.setBounds(566, 408, 97, 33);
		contentPane.add(btnNewButton_1);
		btnNewButton.setFocusable(false);
		btnNewButton_1.setFocusable(false);
	}
	protected void addClassButton(ActionEvent e) {//ȷ�ϰ�ť,��Ӱ༶��Ϣ
		String name=this.classNameText.getText();
		String grade=this.classGradeConb.getSelectedItem().toString();
		String secondary=this.classSecondaryComb.getSelectedItem().toString();
		String major=this.classMajorComb.getSelectedItem().toString();
		String info=this.classinfo.getText();
		//���ɰ༶���
		String id=CollegeStructure.majornum[classSecondaryComb.getSelectedIndex()][classMajorComb.getSelectedIndex()]+String.valueOf(Integer.parseInt(grade)%2000);
		StudentClass temClass=new StudentClass(id,grade,name,secondary,major,info);
		ClassDao classDao=new ClassDao();
		JOptionPane.showMessageDialog(this,classDao.addStudentClass(temClass));
		// TODO �Զ����ɵķ������
		if(ManagerFrame.clf!=null) {//���򿪰༶�б�ҳ��ʱ�ٽ�����Ӱ�������Լ�ʱ��ʾ���б���
			ManagerFrame.clf.queryAllClass();
		}
	}
	protected void restButton(ActionEvent e) {//���ð�ť
		// TODO �Զ����ɵķ������
		this.classGradeConb.setSelectedIndex(0);
		this.classinfo.setText(null);
		this.classSecondaryComb.setSelectedIndex(0);
		this.classMajorComb.setModel(new DefaultComboBoxModel(CollegeStructure.major[0]));
		this.classNameText.setText(null);
	}}
	
