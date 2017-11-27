package cn.tedu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.annotations.MyBatisRepository;
import cn.tedu.entity.User;

@MyBatisRepository
@Repository("userDAO")
public interface UserDAO {
	public User findByUsername(String username);
	public List<User> findAll(int num);
	public User findById(int userId);
	public void update_pwd(User user);
	public User findIdbyjobName(String jobName);
	public User find();
	public void update(User user);
	public List<User> listJob();
	public void usersave(User user);
	public void delete(int userId);
	public int usercount();
}
