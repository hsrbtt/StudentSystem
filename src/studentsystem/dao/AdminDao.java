package studentsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import studentsystem.model.Admin;
import studentsystem.view.ManagerFrame;
//登陆系统
public class AdminDao extends BaseDao{
	
	public Admin selectadmin(String name,String password) {
		String sqlstr="select*from s_admin where name = ? and password = ?";
		Admin admin=null;
		try {//读取信息
			this.pStatement=this.con.prepareStatement(sqlstr);
			this.pStatement.setString(1, name);
			this.pStatement.setString(2, password);
			//逐行查询
			ResultSet executeQuery = this.pStatement.executeQuery();
			if(executeQuery.next()) {
				admin=new Admin(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getString(3));//查询成功返回用户信息
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return admin;
	}
	//修改密码
	public String revisepassword(Admin admin,String newpassword) {
		String resultstr="用户名或密码失败";
		String sqlStr="update s_admin set password = ? where id = ? and name = ? and password = ?";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1, newpassword);
			this.pStatement.setInt(2, admin.getId());
			this.pStatement.setString(3, admin.getName());
			this.pStatement.setString(4, admin.getPassword());
			if(this.pStatement.executeUpdate()>0) {
				resultstr="操作成功";
				ManagerFrame.admin.setPassword(newpassword);//更新系统管理页面密码防止第二次修改读取的是旧密码
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
