package studentsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studentsystem.model.StudentClass;

public class ClassDao extends BaseDao {
//添加班级
public String addStudentClass(StudentClass tempClass) {
	String resultstr="添加模块";
	
	//防止出现一摸一样的id导致错误
	String sqlstr1="select count(*)from s_class where id like'"+tempClass.getId() + "%'";
	
	
	String sqlstr2="select *from s_class where id = ?";
	String sqlstr3="insert into s_class values(?,?,?,?,?,?)";
	//查找有几条id相同的数据
	try {
		this.pStatement=this.con.prepareStatement(sqlstr1);
		ResultSet executeQuery=this.pStatement.executeQuery();
		int count=-1;
		if(executeQuery.next()) {
			count=executeQuery.getInt(1);
		}
		if(count==-1) {
			return resultstr;
		}
		//再插入之前再查一下id是否相同，相同再进行自增
		this.pStatement=this.con.prepareStatement(sqlstr2);
		this.pStatement.setString(1, tempClass.getId()+ ++count);
		executeQuery=this.pStatement.executeQuery();
		while(executeQuery.next()) {
			this.pStatement.setString(1, tempClass.getId()+ ++count);
			executeQuery=this.pStatement.executeQuery();
		}
		tempClass.setId(tempClass.getId()+ count);//生成新的id
		
		//添加到数据库
		this.pStatement=this.con.prepareStatement(sqlstr3);
		this.pStatement.setString(1, tempClass.getId());
		this.pStatement.setString(2,tempClass.getGrade());
		this.pStatement.setString(3,tempClass.getName());
		this.pStatement.setString(4,tempClass.getSecondary());
		this.pStatement.setString(5,tempClass.getMajor());
		this.pStatement.setString(6,tempClass.getInfo());
		if(this.pStatement.executeUpdate()>0) {
			resultstr="添加成功";
		}
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally {
		this.close();
	}
	return resultstr;
}
//生成班级列表（全局查找）
public ArrayList <StudentClass> queryAllClass() {
	 ArrayList <StudentClass> array= new ArrayList<StudentClass>();
	 String sqlstr="select * from s_class";
	 try {
		this.pStatement=this.con.prepareStatement(sqlstr);
		ResultSet executeQuery=this.pStatement.executeQuery();
		while(executeQuery.next()) {
			StudentClass tempClass= new StudentClass();
			tempClass.setId(executeQuery.getString(1));
			tempClass.setGrade(executeQuery.getString(2));
			tempClass.setName(executeQuery.getString(3));
			tempClass.setSecondary(executeQuery.getString(4));
			tempClass.setMajor(executeQuery.getString(5));
			tempClass.setInfo(executeQuery.getString(6));
			array.add(tempClass);
		}
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally {
		this.close();
	}
	 return array;
	  
}
//条件查找
public ArrayList<StudentClass>querySearch(StudentClass tempClass){
	ArrayList<StudentClass> arrays = new ArrayList<StudentClass>();
	String sqlstr="select * from s_class where name like '%"+tempClass.getName()+"%' and grade like '%"+tempClass.getGrade()+"%' and secondary like '%"+tempClass.getSecondary()+"%' and major like '%"+tempClass.getMajor()+"%'";	
    try {
		this.pStatement=this.con.prepareStatement(sqlstr);
		ResultSet executeQuery=this.pStatement.executeQuery();
		while(executeQuery.next()) {
			StudentClass tempClass2= new StudentClass();
			tempClass2.setId(executeQuery.getString(1));
			tempClass2.setGrade(executeQuery.getString(2));
			tempClass2.setName(executeQuery.getString(3));
			tempClass2.setSecondary(executeQuery.getString(4));
			tempClass2.setMajor(executeQuery.getString(5));
			tempClass2.setInfo(executeQuery.getString(6));
			arrays.add(tempClass2);
		}
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally {
		this.close();
    
}return arrays;
}
//删除班级
public String deleteStudentClass(String id) {
	String resultstr="删除失败";
	String sqlstr="delete from s_class where id = ?";
	try {
		this.pStatement=this.con.prepareStatement(sqlstr);
		this.pStatement.setString(1, id);
		if(this.pStatement.executeUpdate()>0) {
			  resultstr="删除成功";
		}
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally {
		this.close();
	}
	return resultstr;
}
//按照年级，专业，学院查找对应班级
public ArrayList <StudentClass> querySomeClass(String grade,String secondary,String major){
	ArrayList <StudentClass> tempArray= new ArrayList<StudentClass>();
	String sqlStr="select id,name from s_class where grade= ? and secondary = ? and major = ? ";
	try {
		this.pStatement=this.con.prepareStatement(sqlStr);
		this.pStatement.setString(1,grade);
		this.pStatement.setString(2,secondary);
		this.pStatement.setString(3,major);
		ResultSet executeQuery =this.pStatement.executeQuery();
		while(executeQuery.next()) {
			StudentClass tempClass=new StudentClass();
			tempClass.setId(executeQuery.getString("id"));
			tempClass.setName(executeQuery.getString("name"));
			tempArray.add(tempClass);
		}
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally {
		this.close();
	}
	return tempArray;
}
//根据班级编号查找是否存在此班级
public boolean querySomeClass(String classId) {
	boolean flag=false;
	String sqlstr= "select name from s_class where id = "+classId;
	try {
		this.pStatement = this.con.prepareStatement(sqlstr);
		ResultSet executeQuery =this.pStatement.executeQuery();
		if(executeQuery.next())
			flag=true;
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally {
		this.close();
	}
	return flag;
	
}
}