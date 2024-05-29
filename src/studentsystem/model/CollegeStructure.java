package studentsystem.model;

public enum CollegeStructure {
	DASHUJU("大数据与互联网学院",0),
	RONGMEI("融媒学院",1),
	Wenlv("文化旅游学院",2),
	SHUYI("数字艺术学院",3),
	WENBO("文博学院",4),
	HUANYI("环境艺术学院",5);
	private String name;
	private int index;
	private CollegeStructure(String name,int index) {
		this.name=name;
		this.index=index;
	}
	public static final String[][] major= {
			{"广播电视技术","移动互联网技术","大数据技术与应用"},
			{"新闻采编与制作","影视多媒体技术"},
			{"景区开发技术","导游","景区设计"},
			{"游戏设计","软件工程","会计"},
			{"哲学","文学","汉语言"},
			{"园林开发","环境保护工程","环境设计"},		
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
