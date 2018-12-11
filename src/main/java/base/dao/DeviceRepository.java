package base.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import base.po.Device;

public interface DeviceRepository extends GraphRepository<Device>{
	List<Device> findByDeviceName(String deviceName);
	List<Device> findByDeviceNameAndDescribe(String deviceName,String describe);
}
