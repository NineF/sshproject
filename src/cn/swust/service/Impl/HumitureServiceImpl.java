package cn.swust.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.swust.dao.HumitureDao;
import cn.swust.domain.Humiture;
import cn.swust.domain.Humiture_1;
import cn.swust.domain.PageBean;
import cn.swust.service.HumitureService;

@Transactional
public class HumitureServiceImpl implements HumitureService {
	private HumitureDao humitureDao;

	public void setHumitureDao(HumitureDao humitureDao) {
		this.humitureDao = humitureDao;
	}

	@Override
	public PageBean<Humiture> findByPage(int currPage) {
		PageBean<Humiture> pageBean = new PageBean<>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示的页数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalSize = humitureDao.findCount();
		pageBean.setTotalCount(totalSize);
		// 封装总的页数
		double tc = totalSize;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Humiture> list = humitureDao.findByPage(begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public boolean saveHumiture(Humiture humiture) {
		return humitureDao.saveHumiture(humiture);
	}

	@Override
	public boolean deleteHumiture(Humiture humiture) {
		return humitureDao.deleteHumiture(humiture);
	}

	@Override
	public PageBean<Humiture> findByLocation(int location_id,int currPage) {
		PageBean<Humiture> pageBean = new PageBean<>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示的页数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalSize = humitureDao.findCountByLocation(location_id);
		pageBean.setTotalCount(totalSize);
		// 封装总的页数
		double tc = totalSize;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Humiture> list = humitureDao.findByLocation(location_id, begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public PageBean<Humiture> findByTime(Date time,int currPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Humiture findNewHumiture(int location_id) {
		Humiture humiture=humitureDao.findNewHumiture(location_id);
		return humiture;
	}

	@Override
	public PageBean<Humiture_1> findByLocationname(String locationname, int currPage) {
		PageBean<Humiture_1> pageBean = new PageBean<>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示的页数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalSize = humitureDao.findCountfromLoc(locationname);
		pageBean.setTotalCount(totalSize);
		// 封装总的页数
		double tc = totalSize;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Humiture_1> list = humitureDao.findByLocationname(locationname, begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	

}
