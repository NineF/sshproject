package cn.swust.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import cn.swust.config.Constant;
import cn.swust.socket.jdbc.DBoperate;
import cn.swust.util.JwtUtil;
import io.jsonwebtoken.Claims;

public class LoginInterceptor implements Interceptor {

	@Override
	public void destroy() {
		System.out.println("interceptor destroy!");
	}

	@Override
	public void init() {
		System.out.println("interceptor init!");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("interceptor intercept!");
		ActionContext ctx = arg0.getInvocationContext();
		String action = ctx.getName();
		for (String ac : Constant.actions) {
			if (ac.equals(action))
				return arg0.invoke();
		}
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		String token = request.getHeader("token");
		if (token != null && !token.equals("")) {
			JwtUtil jwt = new JwtUtil();
			Claims c = null;
			try {
				c = jwt.parseJWT(token);
			} catch (Exception e) {
				return "tokeninvaild";
			}
			long nowMillis = System.currentTimeMillis();
			if (nowMillis > c.getExpiration().getTime()) {
				return "tokeninvaild";
			}
			// 验证token是否正确
			String iss = c.getIssuer();
			if (!iss.equals(Constant.JWT_ISS)) {
				return "tokeninvaild";
			}
			String sqlToken = DBoperate.getTokenfromUserId(Integer.parseInt(c.getId()));
			if (token.equals(sqlToken)) {
				return arg0.invoke();
			} else {
				return "tokentimeout";
			}
		}
		return "notoken";
	}

}
