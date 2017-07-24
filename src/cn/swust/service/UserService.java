package cn.swust.service;

import cn.swust.domain.User;

public interface UserService {
	User login(User user);
	int saveUser(User user);
	boolean updatepwd(User user,String userpassword);
	boolean deleteUser(User user);
	User findByid(int userid);
	User findUserByname(String username);
	User findUserByphone(String userphone);

}
