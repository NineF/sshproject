package cn.swust.domain;

import java.util.Date;

public class TokenBean {
	private int tokenid;
	private String token;
	private Date tokentime;
	private User user;

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTokenid() {
		return tokenid;
	}

	public void setTokenid(int tokenid) {
		this.tokenid = tokenid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokentime() {
		return tokentime;
	}

	public void setTokentime(Date tokentime) {
		this.tokentime = tokentime;
	}


}
