package cn.tedu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.dao.SeatDAO;
import cn.tedu.dao.StuDAO;
import cn.tedu.dao.TcDAO;
import cn.tedu.dao.UserDAO;
import cn.tedu.entity.SeatInfo;
import cn.tedu.entity.StudentInfo;
import cn.tedu.entity.TrainingCamp;
import cn.tedu.entity.User;
import cn.tedu.util.PageObject;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Resource(name="userDAO")
	private UserDAO dao;
	@Resource(name="tcDAO")
	private TcDAO tcdao;
	@Resource(name="seatDAO")
	private SeatDAO seatdao;
	@Resource(name="stuDAO")
	private StuDAO studao;
	
	public User checkLogin(String username, String password) {
		
		User user = null;
		//通过用户名查询用户
		user = dao.findByUsername(username);
		//判断用户是否存在,如果不存在抛出异常,明确提示"用户名不存在"
		if(user==null){
			throw new AppException("用户名不存在!");
		}
		//判断密码是否正确,如果错误,给予提示
		if(!user.getPassword().equals(password)){
			throw new AppException("密码错误!");
		}
		//登录验证通过
		return user;
	}
	//训练营列表
	
	public List<TrainingCamp> Tclist() {
		
		return tcdao.listTc();
	}
	//添加训练营
	public void addTc(TrainingCamp tc) {
		//通过传进来的username找到userid并set进tc里面
		tc.setUserId(dao.findByUsername(tc.getUsername()).getUserId());
		tcdao.addTc(tc);
	}
	//删除训练营
	public void checkDelete(int tcId) {
		TrainingCamp tc = tcdao.findById(tcId);
		tcdao.delete(tcId);
		
	}
	//传入tcId返回数值
	public TrainingCamp loadTc(int tcId) {
		TrainingCamp tc=tcdao.findById(tcId);
		tc.setUsername(dao.findById(tcdao.findById(tcId).getUserId()).getUsername());
		return tc;
	}
	//修改训练营信息
	public void update(TrainingCamp tc) {
		System.out.println("开始修改");
		tc.setUserId(dao.findByUsername(tc.getUsername()).getUserId());
		tcdao.update(tc);
		System.out.println("修改成功");
	}
	//修改密码
	public void checkUpdate_pwd(User user) {
		System.out.println("开始修改密码");
		dao.update_pwd(user);
		System.out.println("密码修改完毕");
	}
	//座位列表
	public List<SeatInfo> seatlist(int tcId) {
		System.out.println("返回参数");
		return seatdao.seatlist(tcId);
	}
	//选中作为返回学生信息
	public List<Object> seatstulist(int studentId) {
		System.out.println("返回学生信息");
		StudentInfo stu=studao.seatstulist(studentId);
		String a;
		if(stu.isBasic()){
			a="有";
		}else{
			a="无";
		}
		List<Object> list=new ArrayList<Object>();
		list.add(stu.getName());
		list.add(stu.getUsername());
		list.add(stu.getType());
		list.add(stu.getSource());
		list.add(stu.getDegree());
		list.add(stu.getMajor());
		list.add(a);
		list.add(stu.getStudyTime()+"天");
		list.add(stu.getLearnPro());
		return list;
	}
	//返回用户列表
	public List<User> userlist(int num) {
		
		return dao.findAll(num);
	}
	//修改user
	public void updateuser(User user) {
		user.setJobId(dao.findIdbyjobName(user.getJobName()).getJobId());
		System.out.println(user);
		dao.update(user);
	}

	public List<User> listJob() {
		return dao.listJob();
	}
	//保存用户
	public void usersave(User user) {
		user.setJobId(dao.findIdbyjobName(user.getJobName()).getJobId());
		System.out.println(user);
		dao.usersave(user);
		
	}
	//删除用户
	public void userdelete(int userId) {
		dao.delete(userId);
		
	}
	//查询用户总数
	public int usercount() {
		return dao.usercount();
	}
	//取消一名学生座位
	public void removestu(int tcId, int studentId) {
		SeatInfo seat=new SeatInfo();
		seat.setTcId(tcId);
		seat.setStudentId(studentId);
		tcdao.removestu(tcId);
		seatdao.removestu(seat);
		studao.removestu(studentId);
	}
	//找到没有座位的学生的id和名字
	public List<StudentInfo> unseatstu() {
		
		return studao.unseatstu();
	}
	//订座
	public void bookingstu(int tcId, int studentId, int serialNum) {
		SeatInfo seat=new SeatInfo();
		seat.setTcId(tcId);
		seat.setSerialNum(serialNum);
		seat.setStudentId(studentId);
		tcdao.bookingstu(tcId);
		seatdao.bookingstu(seat);
		studao.bookingstu(studentId);
	}
	//历史训练营
	public List<TrainingCamp> hisTclist() {
		return tcdao.hislistTc();
	}
	
	public Map<String, Object> findPageStudents(String name, Integer pageCurrent) {
		//1.验证参数的有效性
			if(pageCurrent==null || pageCurrent<1){
				throw new AppException("参数值无效,pageCurrent="+pageCurrent);
			}
				//2.获取当前页数据
				//2.1获取startIndex的值
			PageObject pageObject = new PageObject();
			int pageSize = pageObject.getPageSize();
			int startIndex = (pageCurrent-1)*pageSize;
				//2.2根据startIndex的值获取当前页数据
			List<StudentInfo> list = studao.stuList(name, startIndex, pageSize);
				//3.获取总记录数
			int rowCount = studao.getRowCount(name);
			pageObject.setPageCurrent(pageCurrent);
			pageObject.setStartIndex(startIndex);
			pageObject.setRowCount(rowCount);
			pageObject.setPageSize(pageSize);
				//4.封装查询结果到map对象
				//4.1hashMap底层结构
				//4.2hashMap是否线程安全
				//4.3HashMap是否能保证添加元素的有序性?
				//4.4HashMap在并发场景下如何使用(将其转换为同步集合,或者直接使用ConcurrentHashMap)CAS
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", list);
			map.put("pageObject", pageObject);
				
			return map;
	}
	public User findByUsername(String username){
		return dao.findByUsername(username);
	}
	public void updateStu(StudentInfo stu) {
		studao.updateStu(stu);
		
	}

	public void insertStu(StudentInfo stu) {
		studao.insertStu(stu);
		
	}

	public void delStu(int studentId) {
		studao.deleteStu(studentId);
		
	}

	public List<User> findUserByJobId() {
		
		return studao.findUserByJobId();
	}
}
