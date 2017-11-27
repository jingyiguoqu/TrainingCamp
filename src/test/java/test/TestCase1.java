package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.dao.UserDAO;
import cn.tedu.entity.User;

public class TestCase1 {
	private UserDAO dao;
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
		dao = ac.getBean("userDAO",UserDAO.class);
		System.out.println(dao.getClass().getName());
		
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
}
