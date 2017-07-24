package cn.swust.dao;

import cn.swust.domain.User;

public interface UserDao {
	User findUserAndPassword(User user);

	int saveUser(User user);

	boolean updateUser(User user);

	User findByid(int userid);

	boolean deleteUser(User user);

	User findUserByname(String username);

	User findUserByphone(String userphone);
}
