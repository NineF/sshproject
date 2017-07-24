package cn.swust.dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import cn.swust.dao.LocationDao;
import cn.swust.domain.Location;

public class LocationDaoImpl extends HibernateDaoSupport implements LocationDao {

	@Override
	public List<Location> findAll() {
		List<Location> list = (List<Location>) this.getHibernateTemplate().find("from Location");
		return list;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Location";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}

	@Override
	public Location findByLocation(String locationname) {

		String hql = "from Location where locationname=?";
		List<Location> list = (List<Location>) this.getHibernateTemplate().find(hql,locationname);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public boolean saveLocation(Location location) {
		this.getHibernateTemplate().save(location);
		return true;
	}

	@Override
	public boolean deleteLocation(Location location) {
		this.getHibernateTemplate().delete(location);
		return true;
	}

	@Override
	public Location findByLocationid(int location_id) {
		String hql = "from Location where locationid=?";
		List<Location> list = (List<Location>) this.getHibernateTemplate().find(hql,location_id);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}



}
