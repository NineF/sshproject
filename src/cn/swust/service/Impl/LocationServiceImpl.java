package cn.swust.service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.swust.dao.LocationDao;
import cn.swust.domain.Location;
import cn.swust.service.LocationService;

@Transactional
public class LocationServiceImpl implements LocationService{
	
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Override
	public List<Location> findAll() {
		return locationDao.findAll();
	}

	@Override
	public int findCount() {
		return locationDao.findCount();
	}

	@Override
	public Location findByLocation(String locationname) {
		return locationDao.findByLocation(locationname);
	}

	@Override
	public boolean saveLocation(Location location) {
		
		if(location.getLocationname()!=null&&!location.getLocationname().equals("")){
			if(findByLocation(location.getLocationname())!=null){
				return false;
			}
		}
		return locationDao.saveLocation(location);
	}

	@Override
	public boolean deleteLocation(Location location) {
		return locationDao.deleteLocation(location);
	}

	@Override
	public Location findByLocationid(int location_id) {
		// TODO Auto-generated method stub
		return locationDao.findByLocationid(location_id);
	}

}
