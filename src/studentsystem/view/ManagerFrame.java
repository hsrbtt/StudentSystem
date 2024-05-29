package studentsystem.view;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.DesktopManager;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;

import studentsystem.model.Admin;
import studentsystem.model.Usertype;
import studentsystem.view.Systemmanager.Revisepassword;
import studentsystem.view.student.AddStudentFrame;
import studentsystem.view.student.StudentlistFrame;
import studentsystem.view.studentClass.AddClassFrame;
import studentsystem.view.studentClass.ClasslistFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ManagerFrame extends JFrame {
	public static   Usertype usertype;
	public static  Admin admin;
	public static Revisepassword indexfarme;
	public static AddClassFrame addClassFrame;
	public static  ClasslistFrame  clf=null;
	public static AddStudentFrame  addstudentframe;
	public static StudentlistFrame studentlisframe=null;
	public ManagerFrame( Usertype u, Admin a) {
		getContentPane().setBackground(new Color(255, 255, 255));
		//接收用户信息
		usertype=u;
		admin=a;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 521);
		setLocationRelativeTo(null);//窗口居中
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("系统管理");
		mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改密码");
		//修改密码
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revisepassword(e);
			}
		});
		mntmNewMenuItem.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("退出系统");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("班级管理");
		mnNewMenu_1.setFont(new Font("宋体", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("添加班级");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				addStudentClass(e);
				
			}
		});
		mntmNewMenuItem_2.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("班级列表");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentclasslist(e);
			}
		});
		mntmNewMenuItem_1_1.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem_1_1);
		
		JMenu mnNewMenu_1_1 = new JMenu("学生管理");
		mnNewMenu_1_1.setFont(new Font("宋体", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1_1);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("添加学生");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudentInfo(e);
			}
		});
		mntmNewMenuItem_2_1.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu_1_1.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_1_1_1 = new JMenuItem("学生列表");
		mntmNewMenuItem_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentlist(e);
			}
		});
		mntmNewMenuItem_1_1_1.setFont(new Font("宋体", Font.PLAIN, 15));
		mnNewMenu_1_1.add(mntmNewMenuItem_1_1_1);
	
	       
	}


protected void studentlist(ActionEvent e) {//显示学生列表页面
		// TODO 自动生成的方法存根
	studentlisframe=new StudentlistFrame();
	studentlisframe.setVisible(true);}


protected void addStudentInfo(ActionEvent e) {//显示添加学生页面
		// TODO 自动生成的方法存根
	addstudentframe=new AddStudentFrame();
	addstudentframe.setVisible(true);
	}


private void studentclasslist(ActionEvent e) {//显示班级列表页面
		// TODO 自动生成的方法存根
	    clf= new ClasslistFrame();
		clf.setVisible(true);
	
	}



	private void revisepassword(ActionEvent e) {//显示修改系统管理密码页面
		
		indexfarme=new Revisepassword();
		indexfarme.setVisible(true);
	
		}
	//显示班级管理
	private void addStudentClass(ActionEvent e) {
			// TODO 自动生成的方法存根
		 addClassFrame=new AddClassFrame();
		 addClassFrame.setVisible(true);
		}
		
	
	}


