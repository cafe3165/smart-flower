package base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.DeviceRepository;
import base.dao.Device_Has_Function_Repository;
import base.dao.Location_Has_Device_Repository;
import base.po.Device;
import base.po.Device_Has_Function;
import base.po.Function;
import base.po.Location;
import base.po.Location_Has_Device;

@Service
@Transactional
public class DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	Location_Has_Device_Repository location_Has_Device_Repository;
	
	
	
	public long getNumberOfDevice() {
		return deviceRepository.count();
	}
	
	public Device createDevice(Device o) {
		return deviceRepository.save(o);
	}
	
	public Iterable<Device> getAllDevices() {
		return deviceRepository.findAll();
	}
	
	public Device findDeviceById(Long id) {
		return deviceRepository.findOne(id);
	}

	public void deleteall(){
		deviceRepository.deleteAll();
	}
	
	
	//根据设备名称查找相应的设备
	public List<Device> findByDeviceName(String deviceName){
		return deviceRepository.findByDeviceName(deviceName);
	}
	
	public void addDevice(Location location,Device device,int num){
		Location_Has_Device location_Has_Device=new Location_Has_Device();
		location_Has_Device.setDevice(device);
		location_Has_Device.setLocation(location);
		location_Has_Device.setNumber(num);
		location_Has_Device_Repository.save(location_Has_Device);
	}
	public void addDevice(Location location,Device device){
		Location_Has_Device location_Has_Device=new Location_Has_Device();
		location_Has_Device.setDevice(device);
		location_Has_Device.setLocation(location);
		location_Has_Device_Repository.save(location_Has_Device);
	}

	
	
	
}
