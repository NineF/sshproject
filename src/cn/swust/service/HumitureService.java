package cn.swust.service;

import java.util.Date;
import java.util.List;

import cn.swust.domain.Humiture;
import cn.swust.domain.Humiture_1;
import cn.swust.domain.PageBean;

public interface HumitureService {
	PageBean<Humiture> findByPage(int currPage);

	boolean saveHumiture(Humiture humiture);

	boolean deleteHumiture(Humiture humiture);


	PageBean<Humiture> findByLocation(int location_id,int currPage);

	PageBean<Humiture> findByTime(Date time,int currPage);
	
	Humiture findNewHumiture(int location_id);
	

	PageBean<Humiture_1> findByLocationname(String locationname,int currPage);
}
