package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.tedu.annotations.MyBatisRepository;
import cn.tedu.entity.StudentInfo;
import cn.tedu.entity.User;

@MyBatisRepository
@Repository("stuDAO")
public interface StuDAO {
	public void insertStu(StudentInfo stu);
	public StudentInfo seatstulist(int studentId);
	public void removestu(int studentId);
	public List<StudentInfo> unseatstu();
	public void bookingstu(int studentId);
	
	public List<StudentInfo> stuList(@Param("name")String name,@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	public int getRowCount(String name);
	public void updateStu(StudentInfo stu);
	public List<User> findUserByJobId();
	public void deleteStu(int studentId);
}
