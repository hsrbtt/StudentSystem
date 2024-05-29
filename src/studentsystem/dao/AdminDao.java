package studentsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import studentsystem.model.Admin;
import studentsystem.view.ManagerFrame;
//��½ϵͳ
public class AdminDao extends BaseDao{
	
	public Admin selectadmin(String name,String password) {
		String sqlstr="select*from s_admin where name = ? and password = ?";
		Admin admin=null;
		try {//��ȡ��Ϣ
			this.pStatement=this.con.prepareStatement(sqlstr);
			this.pStatement.setString(1, name);
			this.pStatement.setString(2, password);
			//���в�ѯ
			ResultSet executeQuery = this.pStatement.executeQuery();
			if(executeQuery.next()) {
				admin=new Admin(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getString(3));//��ѯ�ɹ������û���Ϣ
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return admin;
	}
	//�޸�����
	public String revisepassword(Admin admin,String newpassword) {
		String resultstr="�û���������ʧ��";
		String sqlStr="update s_admin set password = ? where id = ? and name = ? and password = ?";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1, newpassword);
			this.pStatement.setInt(2, admin.getId());
			this.pStatement.setString(3, admin.getName());
			this.pStatement.setString(4, admin.getPassword());
			if(this.pStatement.executeUpdate()>0) {
				resultstr="�����ɹ�";
				ManagerFrame.admin.setPassword(newpassword);//����ϵͳ����ҳ�������ֹ�ڶ����޸Ķ�ȡ���Ǿ�����
			}


		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return resultstr;
	}
	

}
