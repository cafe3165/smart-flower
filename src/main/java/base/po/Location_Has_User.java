package base.po;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="location_has_user")
public class Location_Has_User {
	@GraphId
	private Long id;
	
	@StartNode
	private Location location;
	
	@EndNode
	private User user;
	
	private String describe;
	private int number;
	
	public Location_Has_User() {

	}

	public Location_Has_User(Location location, User user) {
		this.location = location;
		this.user = user;
	}

	public Location_Has_User(Location location, User user, int number) {
		this.location = location;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
