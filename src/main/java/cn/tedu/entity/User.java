package cn.tedu.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 6595141006320677888L;
	private int userId;
	private String username;
	private String password;
	private int jobId;
	private String phone;
	private boolean userstate;
	//job±í
	private String jobName;
	//×ÜÊý
	private int count;
	private int num;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isUserstate() {
		return userstate;
	}
	public void setUserstate(boolean userstate) {
		this.userstate = userstate;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", jobId=" + jobId
				+ ", phone=" + phone + ", userstate=" + userstate + ", jobName=" + jobName + "]";
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	
}
