package studentsystem.model;

public enum CollegeStructure {
	DASHUJU("�������뻥����ѧԺ",0),
	RONGMEI("��ýѧԺ",1),
	Wenlv("�Ļ�����ѧԺ",2),
	SHUYI("��������ѧԺ",3),
	WENBO("�Ĳ�ѧԺ",4),
	HUANYI("��������ѧԺ",5);
	private String name;
	private int index;
	private CollegeStructure(String name,int index) {
		this.name=name;
		this.index=index;
	}
	public static final String[][] major= {
			{"�㲥���Ӽ���","�ƶ�����������","�����ݼ�����Ӧ��"},
			{"���Ųɱ�������","Ӱ�Ӷ�ý�弼��"},
			{"������������","����","�������"},
			{"��Ϸ���","�������","���"},
			{"��ѧ","��ѧ","������"},
			{"԰�ֿ���","������������","�������"},		
	};
	public static String[][] majornum= {
			{"131","121","111"},
			{"001","321"},
			{"041","124","231"},
			{"151","178","61"},
			{"567","145",},
			{"79","099","41"}		
	};
	public static final String[]secondarystr= {
			DASHUJU.getName(),
			RONGMEI.getName(),
			Wenlv.getName(),
			SHUYI.getName(),
			WENBO.getName(),
			HUANYI.getName()
	};
	public static final String[]gradestr= {"2012","2023","2020"};
	public int getIndex() {
		return index;
	}
	public String getName() {
		return name;
	}
	

}
