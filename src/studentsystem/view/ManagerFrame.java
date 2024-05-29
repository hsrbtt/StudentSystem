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
		//�����û���Ϣ
		usertype=u;
		admin=a;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 521);
		setLocationRelativeTo(null);//���ھ���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("ϵͳ����");
		mnNewMenu.setFont(new Font("����", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("�޸�����");
		//�޸�����
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revisepassword(e);
			}
		});
		mntmNewMenuItem.setFont(new Font("����", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("�˳�ϵͳ");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("����", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("�༶����");
		mnNewMenu_1.setFont(new Font("����", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("��Ӱ༶");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				addStudentClass(e);
				
			}
		});
		mntmNewMenuItem_2.setFont(new Font("����", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("�༶�б�");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentclasslist(e);
			}
		});
		mntmNewMenuItem_1_1.setFont(new Font("����", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem_1_1);
		
		JMenu mnNewMenu_1_1 = new JMenu("ѧ������");
		mnNewMenu_1_1.setFont(new Font("����", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1_1);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("���ѧ��");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudentInfo(e);
			}
		});
		mntmNewMenuItem_2_1.setFont(new Font("����", Font.PLAIN, 15));
		mnNewMenu_1_1.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_1_1_1 = new JMenuItem("ѧ���б�");
		mntmNewMenuItem_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentlist(e);
			}
		});
		mntmNewMenuItem_1_1_1.setFont(new Font("����", Font.PLAIN, 15));
		mnNewMenu_1_1.add(mntmNewMenuItem_1_1_1);
	
	       
	}


protected void studentlist(ActionEvent e) {//��ʾѧ���б�ҳ��
		// TODO �Զ����ɵķ������
	studentlisframe=new StudentlistFrame();
	studentlisframe.setVisible(true);}


protected void addStudentInfo(ActionEvent e) {//��ʾ���ѧ��ҳ��
		// TODO �Զ����ɵķ������
	addstudentframe=new AddStudentFrame();
	addstudentframe.setVisible(true);
	}


private void studentclasslist(ActionEvent e) {//��ʾ�༶�б�ҳ��
		// TODO �Զ����ɵķ������
	    clf= new ClasslistFrame();
		clf.setVisible(true);
	
	}



	private void revisepassword(ActionEvent e) {//��ʾ�޸�ϵͳ��������ҳ��
		
		indexfarme=new Revisepassword();
		indexfarme.setVisible(true);
	
		}
	//��ʾ�༶����
	private void addStudentClass(ActionEvent e) {
			// TODO �Զ����ɵķ������
		 addClassFrame=new AddClassFrame();
		 addClassFrame.setVisible(true);
		}
		
	
	}


