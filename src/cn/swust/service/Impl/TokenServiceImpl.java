package cn.swust.service.Impl;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import cn.swust.dao.TokenDao;
import cn.swust.domain.TokenBean;
import cn.swust.service.TokenService;

@Transactional
public class TokenServiceImpl implements TokenService {
	private TokenDao tokenDao;

	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

	@Override
	public void deleteToken(Date time) {
		tokenDao.deleteToken(time);
	}

	@Override
	public int saveToken(TokenBean tokenBean) {
		return tokenDao.saveToken(tokenBean);
	}

	@Override
	public void updataToken(String token, int userid) {
		tokenDao.updataToken(token, userid);
	}

}
