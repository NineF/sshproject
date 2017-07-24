package cn.swust.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import cn.swust.dao.TokenDao;
import cn.swust.domain.TokenBean;
import cn.swust.domain.User;

public class TokenDaoImpl extends HibernateDaoSupport implements TokenDao {

	@Override
	public void deleteToken(Date time) {

	}

	@Override
	public int saveToken(TokenBean tokenBean) {
		int i=(int) this.getHibernateTemplate().save(tokenBean);
		return i;
	}


	@Override
	public void updataToken(String token, int userid) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		// session.beginTransaction();
		System.out.println("!!!!!!!!!!!!!!!!");
		Query query = session
				.createQuery("update TokenBean  set token = '" + token + "' where userid = '" + userid + "'");
		query.executeUpdate();
		// session.getTransaction().commit();
	}

}
