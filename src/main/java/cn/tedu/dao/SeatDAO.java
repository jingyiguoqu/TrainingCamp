package cn.tedu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.annotations.MyBatisRepository;
import cn.tedu.entity.SeatInfo;

@MyBatisRepository
@Repository("seatDAO")
public interface SeatDAO {
	public List<SeatInfo> seatlist(int tcId);
	public void removestu(SeatInfo seat);
	public void bookingstu(SeatInfo seat);
}
