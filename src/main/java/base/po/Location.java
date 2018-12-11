package base.po;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Location {
	@GraphId
	private Long id;
	private String locationName;
	private String describe;
	
 	@RelatedTo(type="location_has_device")
 	Set<Device> device=new HashSet<Device>();
 	
 	@RelatedTo(type="location_has_user")
 	Set<User> user=new HashSet<User>();
 	
 	@RelatedTo(type="location_has_context")
 	Set<Context> context=new HashSet<Context>();
	
	public Location() {

	}	
	public Location(String locationName) {
		this.locationName = locationName;
	}
	public Location(String locationName, String describe) {
		this.locationName = locationName;
		this.describe = describe;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", locationName=" + locationName + ", describe=" + describe + "]";
	}
	
	
	
}
