package cn.swust.dao;

import java.util.Date;

import cn.swust.domain.TokenBean;

public interface TokenDao {
	void deleteToken(Date time);
	int saveToken(TokenBean tokenBean);
	void updataToken(String token,int userid);
	
}
