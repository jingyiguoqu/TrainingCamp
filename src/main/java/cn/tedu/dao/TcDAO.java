package cn.tedu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.annotations.MyBatisRepository;
import cn.tedu.entity.TrainingCamp;
import cn.tedu.entity.User;

@MyBatisRepository
@Repository("tcDAO")
public interface TcDAO {
	public List<TrainingCamp> listTc();
	public void addTc(TrainingCamp tc);
	public TrainingCamp findById(int tcId);
	public TrainingCamp updateTc(int tcId);
	public void update(TrainingCamp tc);
	public void delete(int tcId);
	public List<User> findUser();
	public void removestu(int tcId);
	public void bookingstu(int tcId);
	public List<TrainingCamp> hislistTc();
}
