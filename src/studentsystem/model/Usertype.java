package studentsystem.model;
//�û�����
public enum Usertype {
	ADMIN("�û�����Ա",0),
	STUDENT("ѧ��",1),
	TEACHER("��ʦ",2);
	
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
