package cn.tedu.entity;

	

public class StudentInfo {
	int studentId;
	boolean state;
	int userId;
	String name;
	String type;
	String source;
	String degree;
	String major;
	boolean basic;
	String preSituation;
	int studyTime;
	String learnPro;
	boolean seatState;
	//user类的用户名
	String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public boolean isBasic() {
		return basic;
	}
	public void setBasic(boolean basic) {
		this.basic = basic;
	}
	public String getPreSituation() {
		return preSituation;
	}
	public void setPreSituation(String preSituation) {
		this.preSituation = preSituation;
	}
	public int getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(int studyTime) {
		this.studyTime = studyTime;
	}
	public String getLearnPro() {
		return learnPro;
	}
	public void setLearnPro(String learnPro) {
		this.learnPro = learnPro;
	}
	public boolean isSeatState() {
		return seatState;
	}
	public void setSeatState(boolean seatState) {
		this.seatState = seatState;
	}
	//无参构造
	public StudentInfo() {
		
	}
	//全部参数构造方法
	public StudentInfo(int studentId, boolean state, int userId, String name, String type, String source, String degree,
			String major, boolean basic, String preSituation, int studyTime, String learnPro, boolean seatState) {
		super();
		this.studentId = studentId;
		this.state = state;
		this.userId = userId;
		this.name = name;
		this.type = type;
		this.source = source;
		this.degree = degree;
		this.major = major;
		this.basic = basic;
		this.preSituation = preSituation;
		this.studyTime = studyTime;
		this.learnPro = learnPro;
		this.seatState = seatState;
	}
	//添加学生
	public StudentInfo(int userId, String name, String type, String source, String degree, String major, boolean basic,
			String preSituation, String learnPro) {
		super();
		this.userId = userId;
		this.name = name;
		this.type = type;
		this.source = source;
		this.degree = degree;
		this.major = major;
		this.basic = basic;
		this.preSituation = preSituation;
		this.learnPro = learnPro;
	}
	public StudentInfo(String name, String type, String source, String degree, String major, boolean basic,
			String preSituation, int studyTime, String learnPro, String username) {
		super();
		this.name = name;
		this.type = type;
		this.source = source;
		this.degree = degree;
		this.major = major;
		this.basic = basic;
		this.preSituation = preSituation;
		this.studyTime = studyTime;
		this.learnPro = learnPro;
		this.username = username;
	}
	@Override
	public String toString() {
		return "StudentInfo [name=" + name + ", type=" + type + ", source=" + source + ", degree=" + degree + ", major="
				+ major + ", basic=" + basic + ", preSituation=" + preSituation + ", studyTime=" + studyTime
				+ ", learnPro=" + learnPro + ", seatState=" + seatState + ", username=" + username + "]";
	}
	
	
	
}
