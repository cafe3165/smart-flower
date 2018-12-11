package base.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import base.po.Device;
import base.po.Location;

public interface LocationRepository extends GraphRepository<Location>{
	List<Location> findByLocationName(String locationName);
	List<Location> findByLocationNameAndDescribe(String locationName,String describe);
}
