package cn.swust.dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import cn.swust.dao.UserDao;
import cn.swust.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User findUserAndPassword(User user) {
		String hql = "from User where username=? and userpassword=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, user.getUsername(),
				user.getUserpassword());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveUser(User user) {
		int id=(int) this.getHibernateTemplate().save(user);
		return id;
	}

	@Override
	public boolean updateUser(User user) {
		this.getHibernateTemplate().update(user);
		return true;
	}

	@Override
	public User findByid(int userid) {
		return this.getHibernateTemplate().get(User.class, userid);
	}

	@Override
	public boolean deleteUser(User user) {
		this.getHibernateTemplate().delete(user);
		return true;
	}

	@Override
	public User findUserByname(String username) {
		String hql = "from User where username=?";

		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public User findUserByphone(String userphone) {
		String hql = "from User where userphone=?";

		List<User> list = (List<User>) this.getHibernateTemplate().find(hql,userphone);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
