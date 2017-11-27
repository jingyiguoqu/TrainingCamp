package cn.tedu.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tedu.util.JsonDateTypeConvert;

public class TrainingCamp implements Serializable{
	
	private static final long serialVersionUID = -7351716788097119819L;
	private int tcId;
	private String className;
	private String type;
	private int userId;
	private Date createdTime;
	private String seatType;
	private int seatNum;
	private int numPeople;
	private boolean TcState;
	//user
	private String username;
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public int getNumPeople() {
		return numPeople;
	}
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
	
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public boolean isTcState() {
		return TcState;
	}
	public void setTcState(boolean tcState) {
		TcState = tcState;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "TrainingCamp [tcId=" + tcId + ", className=" + className + ", type=" + type + ", userId=" + userId
				+ ", createdTime=" + createdTime + ", seatType=" + seatType + ", seatNum=" + seatNum + ", numPeople="
				+ numPeople + ", TcState=" + TcState + ", username=" + username + "]";
	}
	
	
	
	
	
	
}
