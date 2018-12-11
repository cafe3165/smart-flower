package base.po;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Device {
	
	@GraphId
	private Long id;
	private String deviceName;
	private String describe;
	
 	@RelatedTo(type="device_has_function")
 	Set<Function> function=new HashSet<Function>();

		
	public Device() {

	}
	
	
	public Device(String deviceName) {
		this.deviceName = deviceName;
	}


	public Device(String deviceName, String describe) {
		this.deviceName = deviceName;
		this.describe = describe;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Override
	public String toString() {
		return "Device [id=" + id + ", deviceName=" + deviceName + ", describe=" + describe + "]";
	}
	
	
	
	
}
