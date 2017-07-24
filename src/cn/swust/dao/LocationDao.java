package cn.swust.dao;

import java.util.List;

import cn.swust.domain.Location;

public interface LocationDao {
	List<Location> findAll();
	int findCount();
	Location findByLocation(String locationname);
	Location findByLocationid(int location_id);
	boolean saveLocation(Location location);
	boolean deleteLocation(Location location);
}
