package base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.LocationRepository;
import base.po.Device;
import base.po.Location;

@Service
@Transactional
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	
	public long getNumberOfLocation() {
		return locationRepository.count();
	}
	
	public Location createLocation(Location l) {
		return locationRepository.save(l);
	}
	
	public Iterable<Location> getAllLocations() {
		return locationRepository.findAll();
	}
	
	public Location findLocationById(Long id) {
		return locationRepository.findOne(id);
	}

	public void deleteall(){
		locationRepository.deleteAll();
	}
	
	public List<Location> findByLocationName(String locationName){
		return locationRepository.findByLocationName(locationName);
		
	}
	public List<Location> findByLocationNameAndDescribe(String locationName,String describe){
		return locationRepository.findByLocationNameAndDescribe(locationName, describe);
	}
	
	
	
	
	
}
