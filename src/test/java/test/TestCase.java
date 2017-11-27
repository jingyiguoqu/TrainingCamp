package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.dao.SeatDAO;
import cn.tedu.dao.StuDAO;
import cn.tedu.dao.TcDAO;
import cn.tedu.dao.UserDAO;
import cn.tedu.entity.SeatInfo;
import cn.tedu.entity.StudentInfo;
import cn.tedu.entity.TrainingCamp;
import cn.tedu.entity.User;

public class TestCase {
	private UserDAO dao;
	private StuDAO dao1;
	private TcDAO dao2;
	private SeatDAO dao3;
	
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
		dao = ac.getBean("userDAO",UserDAO.class);
		System.out.println(dao.getClass().getName());
		dao1=ac.getBean("stuDAO",StuDAO.class);
		dao2=ac.getBean("tcDAO",TcDAO.class);
		dao3=ac.getBean("seatDAO",SeatDAO.class);
	}
	@Test
	public void test1(){
		User user = dao.findByUsername("Sally");
		System.out.println(user);
	}
	@Test
	public void test2(){
		List<User> list = dao.findAll(0);
		System.out.println(list);
	}
	@Test
	public void test3(){
		StudentInfo stu=new StudentInfo(4, "李锋", "A", "A", "本科", "计算机", true, "是个傻逼", "D");
		dao1.insertStu(stu);
	}
	@Test
	public void test4(){
		List<TrainingCamp> t=dao2.listTc();
		System.out.println(t);
	}
	@Test
	public void test5(){
		TrainingCamp tc=dao2.findById(2);
		tc.setClassName("教室三");
		dao2.update(tc);
	}
	@Test
	public void test6(){
		List<SeatInfo> t=dao3.seatlist(1);
		System.out.println(t);
	}
	@Test
	public void test7(){
		System.out.println(dao1.seatstulist(1));
	}
	@Test
	public void test8(){
		System.out.println(dao.findAll(0));
	}
	@Test
	public void test9(){
		System.out.println(dao.findIdbyjobName("讲师"));
	}
	@Test
	public void test10(){
		System.out.println(dao.listJob());
	}
	@Test
	public void test11(){
		User user=new User();
		user.setJobId(3);
		user.setPassword("1234");
		user.setUsername("haha");
		user.setPhone("1000000");
		dao.usersave(user);
	}
	@Test
	public void test12(){
		dao.delete(25);
	}
	@Test
	public void test13(){
		System.out.println(dao.usercount());
	}
	@Test
	public void test14(){
		SeatInfo seat=new SeatInfo();
		seat.setTcId(1);
		seat.setStudentId(14);
		dao3.removestu(seat);
	}
	@Test
	public void test15(){
		dao1.removestu(15);
	}
	@Test
	public void test16(){
		SeatInfo seat=new SeatInfo();
		seat.setTcId(1);
		seat.setStudentId(13);
		dao3.removestu(seat);
		dao1.removestu(13);
	}
	@Test
	public void test17(){
		dao2.removestu(1);
	}
	@Test
	public void test18(){
		SeatInfo seat=new SeatInfo();
		seat.setTcId(2);
		seat.setSerialNum(1);
		seat.setStudentId(1);
		dao3.bookingstu(seat);
		
	}
	@Test
	public void test19(){
		
		int id=dao.findIdbyjobName("讲师").getJobId();
		System.out.println(id);
		User user=new User();
		user.setJobId(id);
		user.setPassword("1234");
		user.setUsername("哈哈");
		user.setPhone("13580524942");
		dao.usersave(user);
	}
}
