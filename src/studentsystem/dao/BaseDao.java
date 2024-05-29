package studentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import studentsystem.util.Dbutil;

public abstract class BaseDao {
	protected Connection con = Dbutil.getConnection();//��ȡ���ݿ�����
	
	protected PreparedStatement pStatement = null;//���ݿ��������
	
	protected void close() { //���ݿ�ر�
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}

}
