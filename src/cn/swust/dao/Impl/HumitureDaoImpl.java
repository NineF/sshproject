package cn.swust.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import cn.swust.dao.HumitureDao;
import cn.swust.domain.Humiture;
import cn.swust.domain.Humiture_1;
import cn.swust.util.StringUtil;

public class HumitureDaoImpl extends HibernateDaoSupport implements HumitureDao {

	@Override
	public List<Humiture> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Humiture.class);
		List<Humiture> list = (List<Humiture>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);

		return list;
	}

	@Override
	public boolean saveHumiture(Humiture humiture) {
		this.getHibernateTemplate().save(humiture);
		return true;
	}

	@Override
	public boolean deleteHumiture(Humiture humiture) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(humiture);
		return true;
	}

	@Override
	public List<Humiture> findAll() {
		List<Humiture> list = (List<Humiture>) this.getHibernateTemplate().find("from Humiture");

		return list;
	}

	@Override
	public List<Humiture> findByLocation(int location_id, int begin, int pageSize) {
		String hql = "from Humiture where location_id='" + location_id + "'";
		Query query = this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);

		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Humiture> findByTime(Date time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Humiture";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}

	@Override
	public int findCountByLocation(int location_id) {
		String hql = "select count(*) from Humiture where location_id='" + location_id + "'";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}

	@Override
	public int findCountByTime(Date time) {
		String hql = "select count(*) from Humiture where time='" + time + "'";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}

	@Override
	public Humiture findNewHumiture(int location_id) {
		String hql = "select * from Humiture where location_id ='" + location_id + "'  group by humitureId desc";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(hql);

		query.setFirstResult(0);
		query.setMaxResults(1);
		query.addEntity(Humiture.class);
		return (Humiture) query.list().get(0);
	}

	@Override
	public List<Humiture_1> findByLocationname(String locationname, int begin, int pageSize) {
		String name = "h_" + StringUtil.getPingYin(locationname);
		String hql = "from " + name;
		Query query = this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);

		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public int findCountfromLoc(String loc) {
		String name="h_"+StringUtil.getPingYin(loc);
		String hql = "select count(*) from "+name;
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}

}
