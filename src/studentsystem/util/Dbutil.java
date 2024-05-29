package studentsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbutil {
	private static String jbcDriver = "com.mysql.cj.jdbc.Driver";


	public static Connection getConnection() {
		try {
			//加载数据库驱动
			Class.forName(jbcDriver);
			//获取数据库连接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem?serverTimezone=GMT%2B8&characterEncoding=UTF-8", "root", "");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}