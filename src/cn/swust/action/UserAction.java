package cn.swust.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swust.config.Constant;
import cn.swust.domain.TokenBean;
import cn.swust.domain.User;
import cn.swust.service.TokenService;
import cn.swust.service.UserService;
import cn.swust.util.JwtUtil;
import cn.swust.util.MD5Util;
import io.jsonwebtoken.Claims;

public class UserAction extends ActionSupport implements ModelDriven<User>, ServletRequestAware {

	private Map<String, Object> dataMap;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	private User user = new User();
	private TokenService tokenService;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public User getModel() {
		return user;
	}

	public String login() {

		String password = user.getUserpassword();
		user.setUserpassword(MD5Util.stringMD5(password));
		User existUser = this.userService.login(user);
		dataMap = new HashMap<>();
		if (existUser == null) {
			dataMap.put("code", 500);
			dataMap.put("message", "登录失败");
			return "loginerror";
		} else {
			// 放入一个是否操作成功的标识
			JwtUtil jwt = new JwtUtil();
			String token = "";
			try {
				token = jwt.createJWT(existUser.getUserid() + "", MD5Util.stringMD5(existUser.getUsername()),
						Constant.JWT_TTL);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			TokenBean tokenBean = new TokenBean();
//			tokenBean.setToken(token);
//			long nowMillis = System.currentTimeMillis();
//			Date now = new Date(nowMillis);
//			tokenBean.setTokentime(now);
//			tokenBean.setUser(existUser);
//			tokenService.saveToken(tokenBean);
			tokenService.updataToken(token, existUser.getUserid());
			System.out.println(existUser.getUserid());
			dataMap.put("code", 200);
			dataMap.put("message", "登录成功");
			dataMap.put("token", token);

			return "loginsuccess";
		}
	}

	public String regist() {
		dataMap = new HashMap<>();
		String password = user.getUserpassword();
		user.setUserpassword(MD5Util.stringMD5(password));
		int id = this.userService.saveUser(user);
		user.setUserid(id);
		if (id != -1) {

			TokenBean tokenBean = new TokenBean();
			tokenBean.setToken("");
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);
			tokenBean.setTokentime(now);
			tokenBean.setUser(user);
			tokenService.saveToken(tokenBean);
			
			dataMap.put("code", 200);
			dataMap.put("message", "注册成功!");
			return "registsuccess";
		} else {
			dataMap.put("code", 500);
			dataMap.put("message", "注册失败，用户名或电话号码已被注册!");
			return "registerror";
		}
	}

	public String update() {
		String token = ServletActionContext.getRequest().getHeader("token");
		JwtUtil jwt = new JwtUtil();
		Claims c = null;
		try {
			c = jwt.parseJWT(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(c.getId());
		String userid = c.getId();
		dataMap = new HashMap<>();
		User exietUser = userService.findByid(Integer.parseInt(userid));
		if (userService.updatepwd(exietUser, MD5Util.stringMD5(user.getUserpassword()))) {
			dataMap.put("code", 200);
			dataMap.put("message", "更新成功!");
			return "updatesuccess";
		} else {
			dataMap.put("code", 500);
			dataMap.put("message", "更新失败");

			return "updateerror";
		}
	}

	private HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}
