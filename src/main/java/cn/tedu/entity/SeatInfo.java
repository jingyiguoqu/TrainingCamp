package cn.tedu.entity;

import java.util.Date;

public class SeatInfo {
	private int tcId;
	private int serialNum;
	private boolean seatState;
	private int studentId;
	private Date sitDownTime;
	//stu±í
	private String name;
	private String learnPro;
	//user±í
	private String username;
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public boolean isSeatState() {
		return seatState;
	}
	public void setSeatState(boolean seatState) {
		this.seatState = seatState;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public Date getSitDownTime() {
		return sitDownTime;
	}
	public void setSitDownTime(Date sitDownTime) {
		this.sitDownTime = sitDownTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLearnPro() {
		return learnPro;
	}
	public void setLearnPro(String learnPro) {
		this.learnPro = learnPro;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "SeatInfo [tcId=" + tcId + ", serialNum=" + serialNum + ", seatState=" + seatState + ", studentId="
				+ studentId + ", sitDownTime=" + sitDownTime + ", name=" + name + ", learnPro=" + learnPro
				+ ", username=" + username + "]";
	}
	
}
