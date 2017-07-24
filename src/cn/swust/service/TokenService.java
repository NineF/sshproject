package cn.swust.service;

import java.util.Date;

import cn.swust.domain.TokenBean;

public interface TokenService {
	void deleteToken(Date time);
	int saveToken(TokenBean tokenBean);
	void updataToken(String token,int userid);
}
