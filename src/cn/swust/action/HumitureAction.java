package cn.swust.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swust.domain.Humiture;
import cn.swust.domain.Humiture_1;
import cn.swust.domain.Location;
import cn.swust.domain.PageBean;
import cn.swust.service.HumitureService;
import cn.swust.service.LocationService;

public class HumitureAction extends ActionSupport implements ModelDriven<Humiture> {

	private Map<String, Object> dataMap;

	private String currPage;

	private String location_id;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	private Humiture humiture = new Humiture();

	private HumitureService humitureService;
	private LocationService locationService;
	

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public void setHumitureService(HumitureService humitureService) {
		this.humitureService = humitureService;
	}

	@Override
	public Humiture getModel() {
		return humiture;
	}

	public String listAll() {
		currPage = ServletActionContext.getRequest().getParameter("currPage");
		PageBean<Humiture> pageBean = humitureService.findByPage(Integer.parseInt(currPage));
		dataMap = new HashMap<>();
		dataMap.put("code", 200);
		dataMap.put("message", "返回成功");
		dataMap.put("currPage", pageBean.getCurrPage());
		dataMap.put("totalPage", pageBean.getTotalPage());
		dataMap.put("result", pageBean.getList());

		return "listAll";
	}

	public String listLocation() {
		location_id = ServletActionContext.getRequest().getParameter("location_id");

		currPage = ServletActionContext.getRequest().getParameter("currPage");
		PageBean<Humiture> pageBean = humitureService.findByLocation(Integer.parseInt(location_id),
				Integer.parseInt(currPage));

		dataMap = new HashMap<>();
		dataMap.put("code", 200);
		dataMap.put("message", "返回成功");
		dataMap.put("currPage", pageBean.getCurrPage());
		dataMap.put("totalPage", pageBean.getTotalPage());
		dataMap.put("result", pageBean.getList());

		return "listLocation";
	}
	
	
	public String listLocation1() {
		location_id = ServletActionContext.getRequest().getParameter("location_id");

		currPage = ServletActionContext.getRequest().getParameter("currPage");
		Location l=locationService.findByLocationid(Integer.parseInt(location_id));
		
		PageBean<Humiture_1> pageBean = humitureService.findByLocationname(l.getLocationname(),
				Integer.parseInt(currPage));

		dataMap = new HashMap<>();
		dataMap.put("code", 200);
		dataMap.put("message", "返回成功");
		dataMap.put("currPage", pageBean.getCurrPage());
		dataMap.put("totalPage", pageBean.getTotalPage());
		dataMap.put("result", pageBean.getList());

		return "listLocation";
	}


	public String newHumiture() {
		location_id = ServletActionContext.getRequest().getParameter("location_id");
		Humiture humiture = humitureService.findNewHumiture(Integer.parseInt(location_id));
		dataMap = new HashMap<>();
		dataMap.put("code", 200);
		dataMap.put("message", "返回成功");
		dataMap.put("result", humiture);
		
		return "newHumiture";

	}

}
