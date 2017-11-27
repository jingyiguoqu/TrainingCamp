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
		//ͨ���û�����ѯ�û�
		user = dao.findByUsername(username);
		//�ж��û��Ƿ����,����������׳��쳣,��ȷ��ʾ"�û���������"
		if(user==null){
			throw new AppException("�û���������!");
		}
		//�ж������Ƿ���ȷ,�������,������ʾ
		if(!user.getPassword().equals(password)){
			throw new AppException("�������!");
		}
		//��¼��֤ͨ��
		return user;
	}
	//ѵ��Ӫ�б�
	
	public List<TrainingCamp> Tclist() {
		
		return tcdao.listTc();
	}
	//���ѵ��Ӫ
	public void addTc(TrainingCamp tc) {
		//ͨ����������username�ҵ�userid��set��tc����
		tc.setUserId(dao.findByUsername(tc.getUsername()).getUserId());
		tcdao.addTc(tc);
	}
	//ɾ��ѵ��Ӫ
	public void checkDelete(int tcId) {
		TrainingCamp tc = tcdao.findById(tcId);
		tcdao.delete(tcId);
		
	}
	//����tcId������ֵ
	public TrainingCamp loadTc(int tcId) {
		TrainingCamp tc=tcdao.findById(tcId);
		tc.setUsername(dao.findById(tcdao.findById(tcId).getUserId()).getUsername());
		return tc;
	}
	//�޸�ѵ��Ӫ��Ϣ
	public void update(TrainingCamp tc) {
		System.out.println("��ʼ�޸�");
		tc.setUserId(dao.findByUsername(tc.getUsername()).getUserId());
		tcdao.update(tc);
		System.out.println("�޸ĳɹ�");
	}
	//�޸�����
	public void checkUpdate_pwd(User user) {
		System.out.println("��ʼ�޸�����");
		dao.update_pwd(user);
		System.out.println("�����޸����");
	}
	//��λ�б�
	public List<SeatInfo> seatlist(int tcId) {
		System.out.println("���ز���");
		return seatdao.seatlist(tcId);
	}
	//ѡ����Ϊ����ѧ����Ϣ
	public List<Object> seatstulist(int studentId) {
		System.out.println("����ѧ����Ϣ");
		StudentInfo stu=studao.seatstulist(studentId);
		String a;
		if(stu.isBasic()){
			a="��";
		}else{
			a="��";
		}
		List<Object> list=new ArrayList<Object>();
		list.add(stu.getName());
		list.add(stu.getUsername());
		list.add(stu.getType());
		list.add(stu.getSource());
		list.add(stu.getDegree());
		list.add(stu.getMajor());
		list.add(a);
		list.add(stu.getStudyTime()+"��");
		list.add(stu.getLearnPro());
		return list;
	}
	//�����û��б�
	public List<User> userlist(int num) {
		
		return dao.findAll(num);
	}
	//�޸�user
	public void updateuser(User user) {
		user.setJobId(dao.findIdbyjobName(user.getJobName()).getJobId());
		System.out.println(user);
		dao.update(user);
	}

	public List<User> listJob() {
		return dao.listJob();
	}
	//�����û�
	public void usersave(User user) {
		user.setJobId(dao.findIdbyjobName(user.getJobName()).getJobId());
		System.out.println(user);
		dao.usersave(user);
		
	}
	//ɾ���û�
	public void userdelete(int userId) {
		dao.delete(userId);
		
	}
	//��ѯ�û�����
	public int usercount() {
		return dao.usercount();
	}
	//ȡ��һ��ѧ����λ
	public void removestu(int tcId, int studentId) {
		SeatInfo seat=new SeatInfo();
		seat.setTcId(tcId);
		seat.setStudentId(studentId);
		tcdao.removestu(tcId);
		seatdao.removestu(seat);
		studao.removestu(studentId);
	}
	//�ҵ�û����λ��ѧ����id������
	public List<StudentInfo> unseatstu() {
		
		return studao.unseatstu();
	}
	//����
	public void bookingstu(int tcId, int studentId, int serialNum) {
		SeatInfo seat=new SeatInfo();
		seat.setTcId(tcId);
		seat.setSerialNum(serialNum);
		seat.setStudentId(studentId);
		tcdao.bookingstu(tcId);
		seatdao.bookingstu(seat);
		studao.bookingstu(studentId);
	}
	//��ʷѵ��Ӫ
	public List<TrainingCamp> hisTclist() {
		return tcdao.hislistTc();
	}
	
	public Map<String, Object> findPageStudents(String name, Integer pageCurrent) {
		//1.��֤��������Ч��
			if(pageCurrent==null || pageCurrent<1){
				throw new AppException("����ֵ��Ч,pageCurrent="+pageCurrent);
			}
				//2.��ȡ��ǰҳ����
				//2.1��ȡstartIndex��ֵ
			PageObject pageObject = new PageObject();
			int pageSize = pageObject.getPageSize();
			int startIndex = (pageCurrent-1)*pageSize;
				//2.2����startIndex��ֵ��ȡ��ǰҳ����
			List<StudentInfo> list = studao.stuList(name, startIndex, pageSize);
				//3.��ȡ�ܼ�¼��
			int rowCount = studao.getRowCount(name);
			pageObject.setPageCurrent(pageCurrent);
			pageObject.setStartIndex(startIndex);
			pageObject.setRowCount(rowCount);
			pageObject.setPageSize(pageSize);
				//4.��װ��ѯ�����map����
				//4.1hashMap�ײ�ṹ
				//4.2hashMap�Ƿ��̰߳�ȫ
				//4.3HashMap�Ƿ��ܱ�֤���Ԫ�ص�������?
				//4.4HashMap�ڲ������������ʹ��(����ת��Ϊͬ������,����ֱ��ʹ��ConcurrentHashMap)CAS
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
