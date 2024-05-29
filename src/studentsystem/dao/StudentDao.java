package studentsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studentsystem.model.Student;
import studentsystem.model.StudentClass;

public class StudentDao extends BaseDao{
	//新增学生
	public String addStudentInfo(Student tempStudent) {
		String resultstr = "新增失败";
		//防止出现一摸一样的id导致错误
		String sqlstr1="select count(*)from s_student where id like'"+tempStudent.getId() + "%'";
		
		
		String sqlstr2="select *from s_student where id = ?";
		String sqlstr3="insert into s_student values(?,?,?,?,?,?,?,?,?)";
		
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
			this.pStatement.setString(1, tempStudent.getId()+ ++count);
			executeQuery=this.pStatement.executeQuery();
			while(executeQuery.next()) {
				this.pStatement.setString(1, tempStudent.getId()+ ++count);
				executeQuery=this.pStatement.executeQuery();
			}
			tempStudent.setId(tempStudent.getId()+ count);//生成新的id
			
			//添加到数据库
			this.pStatement=this.con.prepareStatement(sqlstr3);
			this.pStatement.setString(1, tempStudent.getId());
			this.pStatement.setString(2,tempStudent.getGrade());
			this.pStatement.setString(3,tempStudent.getClassid());
			this.pStatement.setString(4,tempStudent.getClassName());
			this.pStatement.setString(5,tempStudent.getName());
			this.pStatement.setString(6,tempStudent.getSex());
			this.pStatement.setString(7,tempStudent.getNation());
			this.pStatement.setString(9,tempStudent.getMajor());
			this.pStatement.setString(8,tempStudent.getSecondary());
			
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
	//全局查询
	public ArrayList<Student>queryAllstudent(){
		 ArrayList <Student> array= new ArrayList<Student>();
		 String sqlstr="select * from s_student";
		 try {
			this.pStatement=this.con.prepareStatement(sqlstr);
			ResultSet executeQuery=this.pStatement.executeQuery();
			while(executeQuery.next()) {
				Student tempStudent= new Student();
				tempStudent.setId(executeQuery.getString(1));
				tempStudent.setGrade(executeQuery.getString(2));
				tempStudent.setClassid(executeQuery.getString(3));
				tempStudent.setClassName(executeQuery.getString(4));
				tempStudent.setName(executeQuery.getString(5));
				tempStudent.setSex(executeQuery.getString(6));
				tempStudent.setNation(executeQuery.getString(7));
				tempStudent.setMajor(executeQuery.getString(8));
				tempStudent.setSecondary(executeQuery.getString(9));
				array.add(tempStudent);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			this.close();
		}
		 return array;
		  
	}
	//条件查询
	public ArrayList<Student>querysearch(Student student){
		 ArrayList <Student> arraystudent= new ArrayList<Student>();
		 String sqlstr="select * from s_student where name like '%"+student.getName()+
				 "%' and classname like '%"+student.getClassName()+
				 "%' and nation like '%"+student.getNation()+
				 "%' and sex like '%"+student.getSex()+
				 "%'and grade like '%"+student.getGrade()+
				 "%' and secondary like '%"+student.getSecondary()+
				 "%' and major like '%"+student.getMajor()+"%'";
		 try {
			this.pStatement=this.con.prepareStatement(sqlstr);
			 ResultSet executeQuery=this.pStatement.executeQuery();
			 while(executeQuery.next()) {
				 Student tempStudent= new Student();
				 tempStudent.setId(executeQuery.getString(1));
				 tempStudent.setGrade(executeQuery.getString(2));
				 tempStudent.setClassid(executeQuery.getString(3));
				 tempStudent.setClassName(executeQuery.getString(4));
				 tempStudent.setName(executeQuery.getString(5));
				 tempStudent.setSex(executeQuery.getString(6));
				 tempStudent.setNation(executeQuery.getString(7));
				 tempStudent.setMajor(executeQuery.getString(8));
				 tempStudent.setSecondary(executeQuery.getString(9));
				 arraystudent.add(tempStudent);
			 }} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
				 this.close();
			 }
		 
		 
		 return arraystudent;
	}
	//删除学生信息
    public String deleteStudent(String id) {
    	String resultstr="删除失败";
    	String sqlstr="delete from s_student where id = ?";
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
    //根据学号查班级编号
    public String queryStudentClass(String id) {
		String ClassId=null;
		String sqlstr="select classid from s_student where id ="+id;
		try {
    		this.pStatement=this.con.prepareStatement(sqlstr);
    		ResultSet executeQuery=this.pStatement.executeQuery();
    		if(executeQuery.next()) {
    			 ClassId=executeQuery.getString("classid");
    		}
    	} catch (SQLException e) {
    		// TODO 自动生成的 catch 块
    		e.printStackTrace();
    	}finally {
    		this.close();
    	}
		return ClassId;
	}
    //编辑学生信息
    public String editStudentInfo(Student tempStudent) {
    	String resultstr = "编辑失败";
         String sqlstr1="select count(*)from s_student where id like'"+tempStudent.getId() + "%'";
		String sqlstr2="select *from s_student where id = ?";
    	String sqlstr="update s_student set name = ?, sex = ?, nation = ?,classid = ?,grade = ?,classname = ?,major = ?,secondary = ?,id = ? where id = ?";
    	String OldId=tempStudent.getId();
    	try {
    		//生成新id
    		this.pStatement=this.con.prepareStatement(sqlstr1);
			ResultSet executeQuery=this.pStatement.executeQuery();
			int count=-1;
			if(executeQuery.next()) {
				count=executeQuery.getInt(1);
			}
			if(count==-1) {
				return resultstr;
			}
			this.pStatement=this.con.prepareStatement(sqlstr2);
			this.pStatement.setString(1, tempStudent.getId()+ ++count);
			executeQuery=this.pStatement.executeQuery();
			while(executeQuery.next()) {
				this.pStatement.setString(1, tempStudent.getId()+ ++count);
				executeQuery=this.pStatement.executeQuery();
			}
			tempStudent.setId(tempStudent.getId()+ count);
    		
    		//更新记录
    		this.pStatement=this.con.prepareStatement(sqlstr);
    		this.pStatement.setString(1,tempStudent.getName());
    		this.pStatement.setString(2,tempStudent.getSex());
    		this.pStatement.setString(3,tempStudent.getNation());
    		this.pStatement.setString(4,tempStudent.getClassid());
    		this.pStatement.setString(5,tempStudent.getGrade());
    		this.pStatement.setString(6,tempStudent.getClassName());
    		this.pStatement.setString(7,tempStudent.getMajor());
    		this.pStatement.setString(8,tempStudent.getSecondary());
    	    this.pStatement.setString(9, tempStudent.getId());
    	    this.pStatement.setString(10, OldId);
    		if(this.pStatement.executeUpdate()>0) {
				resultstr = "编辑成功";
	          }
    		} catch (SQLException e) {
    		// TODO 自动生成的 catch 块
    		e.printStackTrace();
    	}finally {
    		this.close();
    	}
    	return resultstr;
    }
}

