package studentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import studentsystem.util.Dbutil;

public abstract class BaseDao {
	protected Connection con = Dbutil.getConnection();//获取数据库驱动
	
	protected PreparedStatement pStatement = null;//数据库操作对象
	
	protected void close() { //数据库关闭
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
