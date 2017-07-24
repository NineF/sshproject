package cn.swust.dao;

import java.util.Date;
import java.util.List;

import cn.swust.domain.Humiture;
import cn.swust.domain.Humiture_1;

public interface HumitureDao {
	int findCount();
	

	int findCountfromLoc(String loc);
	
	List<Humiture> findByPage(int begin, int pageSize);

	boolean saveHumiture(Humiture humiture);

	boolean deleteHumiture(Humiture humiture);
	
	List<Humiture> findAll();
	
	List<Humiture> findByLocation(int location_id,int begin, int pageSize);
	
	List<Humiture> findByTime(Date time);
	
	int findCountByLocation(int location_id);
	
	int findCountByTime(Date time);
	
	Humiture findNewHumiture(int location_id);
	
	List<Humiture_1> findByLocationname(String locationname,int begin, int pageSize);
	
}
