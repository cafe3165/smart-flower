package base.po;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="location_has_device")
public class Location_Has_Device {
	@GraphId
	private Long id;
	
	@StartNode
	private Location location;
	
	@EndNode
	private Device device;
	
	private String describe;
	private int number;
	
	
	
	public Location_Has_Device() {

	}
	
	public Location_Has_Device(Location location, Device device) {
		this.location = location;
		this.device = device;
	}
	

	public Location_Has_Device(Location location, Device device, int number) {
		this.location = location;
		this.device = device;
		this.number = number;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	
}
