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
		setTitle("学生列表");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		setLocationRelativeTo(null);//窗口居中
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
		table.getTableHeader().setReorderingAllowed(false);//设置用户不可拖动
		table.setRowHeight(25);//表格高度
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"班级编号", "所属年级", "班级名", "所属学院", "所属专业", "班级信息"
			}
		) {
			public boolean isCellEditable(int row,int column) {//无法直接对表格操作
				return false;
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("班级名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 464, 104, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("学院");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 533, 104, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("年级");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(287, 464, 104, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("专业");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(287, 535, 104, 31);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 651, 104, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setBounds(209, 650, 97, 30);
		contentPane.add(btnNewButton_1);
		
		deleteButton= new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButton(e);
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		deleteButton.setBounds(601, 650, 97, 30);
		contentPane.add(deleteButton);
		
		editButton = new JButton("编辑");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			       editButton(e);
			}
		});
		editButton.setFont(new Font("宋体", Font.PLAIN, 20));
		editButton.setBounds(418, 650, 97, 30);
		contentPane.add(editButton);
		
		ClassName = new JTextField();
		ClassName.setFont(new Font("宋体", Font.PLAIN, 20));
		ClassName.setBounds(100, 464, 142, 31);
		contentPane.add(ClassName);
		ClassName.setColumns(10);
		
		ClassSecondary = new JTextField();
		ClassSecondary.setFont(new Font("宋体", Font.PLAIN, 20));
		ClassSecondary.setColumns(10);
		ClassSecondary.setBounds(68, 533, 209, 31);
		contentPane.add(ClassSecondary);
		
		ClassGrade = new JTextField();
		ClassGrade.setFont(new Font("宋体", Font.PLAIN, 20));
		ClassGrade.setColumns(10);
		ClassGrade.setBounds(365, 464, 118, 31);
		contentPane.add(ClassGrade);
		
		ClassMajor = new JTextField();
		ClassMajor.setFont(new Font("宋体", Font.PLAIN, 20));
		ClassMajor.setColumns(10);
		ClassMajor.setBounds(365, 533, 209, 31);
		contentPane.add(ClassMajor);
		this.dtm=(DefaultTableModel) table.getModel();
	     queryAllClass();
		}
	protected void editButton(ActionEvent e) {//编辑按钮
		// TODO 自动生成的方法存根
		JOptionPane.showConfirmDialog(this,"没想还咋写");
	}
	protected void deleteButton(ActionEvent e) {//删除按钮
		// TODO 自动生成的方法存根
		if(JOptionPane.showConfirmDialog(this, "是否删除此班级","正在删除.....",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION) {
			String id = dtm.getValueAt(this.table.getSelectedRow(), 0).toString();
			ClassDao classDao=new ClassDao();
			JOptionPane.showMessageDialog(this,classDao.deleteStudentClass(id));
			 queryAllClass();
		}
	}
	protected void selectRow(MouseEvent e) {//列表具体点击事件
		// TODO 自动生成的方法存根
		this.ClassName.setText(dtm.getValueAt(this.table.getSelectedRow(), 2).toString());
		this.ClassGrade.setText(dtm.getValueAt(this.table.getSelectedRow(), 1).toString());
		this.ClassSecondary.setText(dtm.getValueAt(this.table.getSelectedRow(), 3).toString());
		this.ClassMajor.setText(dtm.getValueAt(this.table.getSelectedRow(), 4).toString());
		this.editButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
	}
	//搜索按钮
		protected void search(ActionEvent e) {
		// TODO 自动生成的方法存根
		String name=this.ClassName.getText();
		String grade=this.ClassGrade.getText();
		String secondary=this.ClassSecondary.getText();
		String major=this.ClassMajor.getText();
		if(Strutil.isEmpty(name)&&Strutil.isEmpty(grade)&&Strutil.isEmpty(secondary)&&Strutil.isEmpty(major)) {//什么都不输入直接点击搜索为全局查找
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
		protected void resetButton(ActionEvent e) {//重置按钮
		// TODO 自动生成的方法存根
		this.ClassName.setText(null);
		this.ClassGrade.setText(null);
		this.ClassMajor.setText(null);
		this.ClassSecondary.setText(null);
		this.editButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
		
	}
		//全局查找方法
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
