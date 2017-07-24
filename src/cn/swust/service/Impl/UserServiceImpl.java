package cn.swust.service.Impl;

import org.springframework.transaction.annotation.Transactional;

import cn.swust.dao.UserDao;
import cn.swust.domain.User;
import cn.swust.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(User user) {
		User existUser = this.userDao.findUserAndPassword(user);
		return existUser;
	}

	@Override
	public int saveUser(User user) {
		if (user.getUsername() != null && !user.getUsername().equals("")) {
			User existuser = this.findUserByname(user.getUsername());
			if (existuser != null) {
				return -1;
			}
		}

		if (user.getUserphone() != null && !user.getUserphone().equals("")) {
			User existuser = this.findUserByname(user.getUserphone());
			if (existuser != null) {
				return -1;
			}
		}
		return this.userDao.saveUser(user);
	}

	@Override
	public boolean updatepwd(User user,String userpassword) {
		user.setUserpassword(userpassword);
		return this.userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		return this.userDao.deleteUser(user);
	}

	@Override
	public User findByid(int userid) {
		return this.userDao.findByid(userid);
	}

	@Override
	public User findUserByname(String username) {
		return this.userDao.findUserByname(username);
	}

	@Override
	public User findUserByphone(String userphone) {
		return this.userDao.findUserByphone(userphone);
	}

}
