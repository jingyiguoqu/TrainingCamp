package cn.tedu.service;

import java.util.List;
import java.util.Map;

import cn.tedu.entity.SeatInfo;
import cn.tedu.entity.StudentInfo;
import cn.tedu.entity.TrainingCamp;
import cn.tedu.entity.User;
/**
 * 业务层接口
 * @author Administrator
 *
 */
public interface LoginService {
	public User checkLogin(String username,String password);
	public List<TrainingCamp> Tclist();
	public void addTc(TrainingCamp tc);
	public void checkDelete(int tcId);
	public TrainingCamp loadTc(int tcId);
	public void update(TrainingCamp tc);
	public void checkUpdate_pwd(User user);
	public List<SeatInfo> seatlist(int tcId);
	public List<Object> seatstulist(int studentId);
	public List<User> userlist(int num);
	public void updateuser(User user);
	public List<User> listJob();
	public void usersave(User user);
	public void userdelete(int userId);
	public int usercount();
	public void removestu(int tcId,int studentId);
	public List<StudentInfo> unseatstu();
	public void bookingstu(int tcId,int studentId,int serialNum);
	public List<TrainingCamp> hisTclist();
	Map<String,Object> findPageStudents(String name,Integer pageCurrent);
	void updateStu(StudentInfo stu);
	void insertStu(StudentInfo stu);
	void delStu(int studentId);
	User findByUsername(String username);
	List<User> findUserByJobId();
}
