package cn.swust.service;

import java.util.List;

import cn.swust.domain.Location;

public interface LocationService {
	List<Location> findAll();

	int findCount();

	Location findByLocation(String locationname);
	Location findByLocationid(int location_id);

	boolean saveLocation(Location location);

	boolean deleteLocation(Location location);
}
