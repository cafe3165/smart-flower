package base.dao;

import org.springframework.data.neo4j.repository.GraphRepository;

import base.po.Device_Has_Function;
import base.po.Location_Has_Device;

public interface Location_Has_Device_Repository extends GraphRepository<Location_Has_Device>{

}
