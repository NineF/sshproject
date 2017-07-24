package cn.swust.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.swust.domain.Location;
import cn.swust.service.LocationService;

public class LocationAction extends ActionSupport implements ModelDriven<Location> {

	private Map<String, Object> dataMap;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	private LocationService locationService;

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	private Location location = new Location();

	@Override
	public Location getModel() {
		return location;
	}

	public String locations() {
		List<Location> list = locationService.findAll();
		dataMap = new HashMap<>();
		dataMap.put("code", 200);
		dataMap.put("message", "返回成功");
		dataMap.put("result", list);
		return "locations";
	}

	public String addLocation() {
		dataMap=new HashMap<>();
		if(locationService.saveLocation(location)){
			dataMap.put("code", 200);
			dataMap.put("message", "添加成功");
			return "addsuccess";
		}else{
			dataMap.put("code", 500);
			dataMap.put("message", "添加失败");
			return "adderror";
		}
	}
}
