package studentsystem.model;
//用户类型
public enum Usertype {
	ADMIN("用户管理员",0),
	STUDENT("学生",1),
	TEACHER("教师",2);
	
	private int index;
	private String name;
	private Usertype( String name,int index) {
		this.name=name;
		this.index=index;
	}
	public String getname() {
		return name;
	}
	public  void setname(String name) {
		this.name=name;
	}
	public  int getindex() {
		return index;
	}
	public  void setindex(int index) {
		this.index=index;
	}
	public String toString() {
		return name;
	}

}
