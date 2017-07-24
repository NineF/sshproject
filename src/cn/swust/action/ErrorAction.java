package cn.swust.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class ErrorAction extends ActionSupport {
	private Map<String, Object> dataMap;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public String notoken() {
		dataMap = new HashMap<>();
		dataMap.put("code", 400);
		dataMap.put("message", "没有token");
		return "notokenerror";
	}

	public String tokeninvlid() {
		dataMap = new HashMap<>();
		dataMap.put("code", 400);
		dataMap.put("message", "token失效");
		return "tokeninvlid";
	}
	
	public String tokentimeout() {
		dataMap = new HashMap<>();
		dataMap.put("code", 400);
		dataMap.put("message", "token过期");
		return "tokentimeout";
	}
}
